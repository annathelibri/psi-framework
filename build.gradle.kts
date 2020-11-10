import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Aru!DB
plugins {
    kotlin("jvm") version "1.3.72"
    maven
    `maven-publish`
    id("com.github.ben-manes.versions") version "0.21.0"
    id("com.jfrog.bintray") version "1.8.4"
}

group = "net.notjustanna.psi"
version = "3.0"

//Repositories and Dependencies
repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/notjustanna/maven") }
    maven { url = uri("https://dl.bintray.com/notjustanna/kotlin") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("net.dv8tion:JDA:4.2.0_214") {
        exclude(module = "opus-java")
    }
    implementation("club.minnced:jda-reactor:1.2.0")
    compile("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.0")
    compile ("com.jagrosh:jda-utilities-commons:3.0.5")
    compile("io.github.classgraph:classgraph:4.8.47")
    compile("org.kodein.di:kodein-di-generic-jvm:6.5.5")
    compile("net.notjustanna.libs:kodein-jit-bindings:2.2")

    // Open-Source Libraries
    compile("net.notjustanna.libs:resources:1.0")
    compile ("com.grack:nanojson:1.6")

    // Logging
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("io.github.microutils:kotlin-logging:1.7.9")

    testCompile("org.junit.jupiter:junit-jupiter-api:5.6.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val sourceJar = task("sourceJar", Jar::class) {
    dependsOn(tasks["classes"])
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    publications.create("mavenJava", MavenPublication::class.java) {
        groupId = project.group.toString()
        artifactId = project.name
        version = project.version.toString()

        from(components["java"])
        artifact(sourceJar)
    }
}

fun findProperty(s: String) = project.findProperty(s) as String?
bintray {
    user = findProperty("bintrayUsername")
    key = findProperty("bintrayApiKey")
    publish = true
    setPublications("mavenJava")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "aru"
        name = project.name
        userOrg = "notjustanna"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/notjustanna/psi.git"
    })
}

tasks.withType<BintrayUploadTask> {
    dependsOn("build", "publishToMavenLocal")
}

project.run {
    file("src/main/kotlin/net/notjustanna/psi/exported/exported.kt").run {
        parentFile.mkdirs()
        createNewFile()
        writeText(
            """
@file:JvmName("PsiExported")
@file:Suppress("unused")

/*
 * file "exported.kt". DO NOT EDIT MANUALLY. THIS FILE IS GENERATED BY GRADLE.
 */

package net.notjustanna.psi.exported

/**
 * Psi Version
 */
const val psi_version = "$version"
""".trim()
        )
    }
}