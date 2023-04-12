# SpecialTitleYouWant

[![](https://shields.io/github/downloads/MrXiaoM/SpecialTitleYouWant/total)](https://github.com/MrXiaoM/SpecialTitleYouWant/releases) [![](https://img.shields.io/badge/mirai--console-2.11-blue)](https://github.com/mamoe/mirai) [![](https://img.shields.io/badge/MiraiForum-post-yellow)](https://mirai.mamoe.net/topic/1270![])

设置任何你想要的群头衔!

## 特性

* 可以自定义设置头衔的命令
* 可以自定义大部分提示信息
* 可以设置违禁词
* 违禁词可用正则表达式

## 安装

到 [Releases](https://github.com/MrXiaoM//SpecialTitleYouWant) 下载插件并放入 plugins 文件夹进行安装

> 2.11 或以上下载 SpecialTitleYouWant-*.mirai2.jar
> 
> 2.11 以下下载 SpecialTitleYouWant-legacy-*.mirai.jar

安装完毕后，编辑配置文件 (路径: `config/top.mrxiaom.SpecialTitleYouWant/config.yml`，在旧版mirai的路径是`config/自定义头衔/config.yml`)

在 enableGroups 中加入要使用该插件的群号，如

```yaml
enableGroups:
  - 114514
  - 1919810
```

在控制台执行 `/stuw reload` 重载配置文件即可

> ***为什么不用 console 的內建权限系统?***  
> 高情商: 据我了解大多数小白都不了解什么是“被许可人”之类对他们来说有点复杂的东西，那我不如返璞归真，使用配置文件储存需要启用的群。  
> 低情商: 反正需求不高，懒得学权限系统怎么用。

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

> 这是我第一次用kotlin写的一个完整程序，代码很💩，新人不要参考（

## 捐助

前往 [爱发电](https://afdian.net/a/mrxiaom) 捐助我。
