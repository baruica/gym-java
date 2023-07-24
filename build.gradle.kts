plugins {
    java
    kotlin("jvm") version "1.9.0"
}

group = "me.baruica"
version = "1.0-SNAPSHOT"
description = "The gym"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.0"

dependencies {
    implementation("com.github.f4b6a3:ulid-creator:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
