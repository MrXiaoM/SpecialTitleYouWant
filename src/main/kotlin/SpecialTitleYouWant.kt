package top.mrxiaom

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info

object SpecialTitleYouWant : KotlinPlugin(
    JvmPluginDescription(
        id = "top.mrxiaom.SpecialTitleYouWant",
        name = "自定义头衔",
        version = "1.0-SNAPSHOT",
    ) {
        author("MrXiaoM")
    }
) {
    override fun onEnable() {
        reloadConfig()
        GlobalEventChannel.registerListenerHost(CommandSpecialTitle)
        CommandManager.registerCommand(CommandReload)
        logger.info { "Plugin loaded" }

    }

    fun reloadConfig() {
        ConfigSpecialTitle.reload()
    }
}