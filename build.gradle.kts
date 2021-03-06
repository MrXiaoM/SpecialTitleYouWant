plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.11.1"
}

group = "top.mrxiaom"
version = "1.0.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
