plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("com.github.johnrengelman.shadow").version("7.1.2")
}

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

fun DependencyHandlerScope.implCollection(vararg implementations: String) {
    for (impl in implementations) {
        implementation(impl)
    }
}

fun DependencyHandlerScope.compileCollection(vararg implementations: String) {
    for (impl in implementations) {
        compileOnly(impl)
    }
}

val kotlinVersion = "1.8.10"
val exposedVersion = "0.41.1"
val coroutinesVersion = "1.6.4"

val paperVersion = "1.19.3-R0.1-SNAPSHOT"

dependencies {
    implCollection(
        "org.graalvm.truffle:truffle-api:22.3.1",
        "org.jetbrains.exposed:exposed-core:$exposedVersion",
        "org.jetbrains.exposed:exposed-jdbc:$exposedVersion"
    )

    compileCollection(
        "io.papermc.paper:paper-api:$paperVersion",
        "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion",
        "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0",
        "org.tukaani:xz:1.9",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion",
        "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion",
    )

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

group = "dev.lucadev"
version = "0.0.1"
description = "Meow"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["kotlin"])
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileKotlin { kotlinOptions.jvmTarget = "17" }

tasks.shadowJar {
    archiveFileName.set("LucaFoods-${project.version}.jar")
}

tasks.jar.configure {
    finalizedBy(tasks["shadowJar"])
}
