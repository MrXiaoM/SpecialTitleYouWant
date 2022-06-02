package top.mrxiaom

import net.mamoe.mirai.contact.NormalMember
import net.mamoe.mirai.contact.PermissionDeniedException
import net.mamoe.mirai.contact.isOwner
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * 为什么不用 Console 内建命令系统? 我拿不到 MessageSource
 */
object CommandSpecialTitle : SimpleListenerHost() {
    @EventHandler
    suspend fun onGroupMessage(event: GroupMessageEvent) {
        if (!ConfigSpecialTitle.enableGroups.contains(event.group.id)) return
        val msg = event.message
        msg.dropWhile { it is MessageSource }
        val at = msg[0]
        if (at !is At || at.target != event.bot.id) return
        msg.drop(0)
        var s = msg.contentToString()
        // 硬核移除多余空格
        while (s.startsWith(" ")) s = s.substring(1)
        val cmd = ConfigSpecialTitle.cmd
        for (c in cmd) {
            if (s.startsWith(c)) {
                var tempTitle = s.substring(c.length)
                // 只移除第一个空格（如果有）
                //
                if (tempTitle.startsWith(" ")) tempTitle = tempTitle.substring(1)
                handle(event.source, tempTitle)
                break
            }
        }
    }

    private suspend fun handle(source: OnlineMessageSource.Incoming.FromGroup, title: String) {
        val oldTitle = source.sender.specialTitle
        // 群主检查
        if (!source.group.botAsMember.permission.isOwner()) {
            sendMessage(source, ConfigSpecialTitle.msgNotOwner.replace("%old%", oldTitle).replace("%title%", title))
            return
        }
        val member = source.sender
        if (member !is NormalMember) {
            sendMessage(
                source,
                ConfigSpecialTitle.msgNotNormalMember.replace("%old%", oldTitle).replace("%title%", title)
            )
            return
        }
        // 长度检查
        val min = ConfigSpecialTitle.minLength
        if (min > 0 && title.length < min) {
            sendMessage(source, ConfigSpecialTitle.msgTooShort.replace("%old%", oldTitle).replace("%title%", title))
            return
        }
        val max = ConfigSpecialTitle.maxLength
        if (max > 0 && title.length > max) {
            sendMessage(source, ConfigSpecialTitle.msgTooLong.replace("%old%", oldTitle).replace("%title%", title))
            return
        }
        // 违禁词检查
        if (checkIllegalWords(title) || checkIllegalPatterns(title)) {
            sendMessage(source, ConfigSpecialTitle.msgIllegal.replace("%old%", oldTitle).replace("%title%", title))
            return
        }
        // 设置头衔
        try {
            member.specialTitle = title
            sendMessage(source, ConfigSpecialTitle.msgSet.replace("%old%", oldTitle).replace("%title%", title))
        } catch (e: PermissionDeniedException) {
            sendMessage(source, ConfigSpecialTitle.msgNotOwner.replace("%old%", oldTitle).replace("%title%", title))
            return
        }
    }

    private suspend fun sendMessage(source: OnlineMessageSource.Incoming.FromGroup, s: String) {
        var str = s
        val msg = MessageChainBuilder()
        if (str.contains("%quote%")) {
            str = str.replace("%quote%", "")
            msg.add(QuoteReply(source))
        }
        if (!str.contains("%at%")) msg.add(str)
        else {
            val split = str.split("%at%")
            for ((i, a) in split.withIndex()) {
                msg.add(a)
                if (i < split.size - 1) msg.add(At(source.sender.id))
            }
        }
        source.group.sendMessage(msg.build())
    }

    /**
     * 检查是否含有违禁词
     * @return true包含违禁词，false不含违禁词
     */
    fun checkIllegalWords(str: String): Boolean {
        for (s in ConfigSpecialTitle.illegalWords) {
            if (str.contains(s, true)) return true
        }
        return false
    }

    /**
     * 正则匹配是否含有违禁词
     * @return true包含违禁词，false不含违禁词
     */
    fun checkIllegalPatterns(str: String): Boolean {
        for (s in ConfigSpecialTitle.illegalPatterns) {
            try {
                if (Pattern.matches(s, str)) return true
            } catch (e: PatternSyntaxException) {
                SpecialTitleYouWant.logger.warning("正则表达式错误 $s", e)
            }
        }
        return false
    }
}