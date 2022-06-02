# SpecialTitleYouWant

[![](https://shields.io/github/downloads/MrXiaoM/SpecialTitleYouWant/total)](https://github.com/MrXiaoM//SpecialTitleYouWant) ![](https://img.shields.io/badge/mirai--console-2.11-blue)

设置任何你想要的群头衔!

## 安装

到 [Releases](https://github.com/MrXiaoM//SpecialTitleYouWant) 下载插件并放入 plugins 文件夹进行安装

> 2.11 或以上下载 SpecialTitleYouWant-x.x.x.jar
> 
> 2.11 以下下载 SpecialTitleYouWant-x.x.x-legacy.jar

安装完毕后，编辑配置文件 (`config/top.mrxiaom.SpecialTitleYouWant/config.yml`)

在 enableGroup 中加入要使用该插件的群号，如

```yaml
enableGroup:
  - 114514
  - 1919810
```

在控制台执行 `/stuw reload` 重载配置文件即可

## 用法

机器人***必须是群主***!!!

```text
@机器人 我要头衔 头衔名称
```

命令可以在配置文件的`cmd`那里设置，在输入命令时可以不打空格

## 编译

```shell
/gradlew buildPlugin buildPluginLegacy
```