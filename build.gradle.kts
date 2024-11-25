plugins {
    java
    kotlin("jvm") version "2.0.21"
    id("com.autonomousapps.dependency-analysis") version "2.5.0"
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

val junitVersion = "5.11.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
    testImplementation("com.github.f4b6a3:ulid-creator:5.2.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}
