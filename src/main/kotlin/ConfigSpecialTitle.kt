package top.mrxiaom

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object ConfigSpecialTitle : AutoSavePluginConfig("config") {
    /**
     * 启用该插件的群
     */
    val enableGroups by value(emptyList<Long>())

    /**
     * 头衔违禁词(不分大小写)
     */
    val illegalWords by value(emptyList<String>())

    /**
     * 头衔违禁词正则表达式
     */
    val illegalPatterns by value(emptyList<String>())

    /**
     * 最小长度，-1为不限制
     */
    val minLength by value(-1)

    /**
     * 最大长度，-1为不限制，但即使不限制，依然会截断头衔长度
     */
    val maxLength by value(-1)

    /**
     * 修改头衔命令
     */
    val cmd by value(arrayOf("我要头衔", "设置头衔"))

    /**
     * 消息，若包括%quote%将会回复用户
     * %at%将会替换为@用户
     * %old%将会替换成旧头衔
     * %title%将会替换成新头衔
     */
    val msgTooLong by value("%quote%你输入的头衔过长")
    val msgTooShort by value("%quote%你输入的头衔过短")
    val msgSet by value("%quote%已设置头衔!")
    val msgIllegal by value("%quote%你的头衔包含违禁词，不许设置!")
    val msgNotOwner by value("%quote%机器人不是群主，无法设置头衔!")
    val msgNotNormalMember by value("%quote%你不是普通群员(比如开启了匿名)，无法设置头衔!")

    val msgReload by value("配置文件已重载")
    val msgHelp by value(arrayOf("自定义头衔 帮助", "/stuw reload - 重载配置文件"))
}