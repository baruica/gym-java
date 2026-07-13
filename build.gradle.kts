plugins {
    java
    kotlin("jvm") version "2.4.0"
    id("com.autonomousapps.dependency-analysis") version "3.16.1"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "me.baruica"
version = "1.0-SNAPSHOT"
description = "The gym"

repositories {
    mavenCentral()
}

val junitVersion = "6.1.2"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.4.0")
    testImplementation("com.github.f4b6a3:ulid-creator:5.2.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}
