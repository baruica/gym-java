plugins {
    java
    kotlin("jvm") version "1.9.22"
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

val junitVersion = "5.10.1"

dependencies {
    implementation("com.github.f4b6a3:ulid-creator:5.2.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
