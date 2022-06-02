package top.mrxiaom

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

object CommandReload : SimpleCommand(
    owner = SpecialTitleYouWant,
    primaryName = "specialtitleyouwant",
    secondaryNames = arrayOf("stuw"),
    description = "重载自定义头衔插件"
) {
    @Handler
    suspend fun CommandSender.handle(operation: String) {
        if (operation.equals("reload", true)) {
            SpecialTitleYouWant.reloadConfig()
            sendMessage(ConfigSpecialTitle.msgReload)
        }
    }

    @Handler
    suspend fun CommandSender.handle() {
        var s = ""
        for ((i, a) in ConfigSpecialTitle.msgHelp.withIndex()) {
            s += a + if (i < ConfigSpecialTitle.msgHelp.size - 1) "\n" else ""
        }
        sendMessage(s)
    }
}