plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.0"
    `maven-publish`
}

group = "world.estaria"
version = "1.0.1"

repositories {
    mavenCentral()

    // minecraft repositories
    maven("https://repo.papermc.io/repository/maven-public/")

    // estaria dependencies
    maven {
        name = "estaria"
        url = uri("https://repo.estaria.world/releases")
        credentials(PasswordCredentials::class.java)
    }
}

dependencies {
    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")
    compileOnly("world.avionik:minecraft-common:1.0.1")

    // estaria dependencies
    compileOnly("world.estaria:github-file-manager:1.2.0")
    compileOnly("world.estaria:proxy-manager-api:1.0.1")
    compileOnly("world.estaria:server-manager-api:1.0.3")

    // velocity dependencies
    compileOnly("com.velocitypowered:velocity-api:3.1.1")
    annotationProcessor("com.velocitypowered:velocity-api:3.1.1")
}