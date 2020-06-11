plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.dorongold.task-tree") version "1.5"
}

group = "com.nevinsjr.rxhub4k"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.projectreactor", "reactor-core", "3.3.6.RELEASE")
    implementation("io.projectreactor.kotlin", "reactor-kotlin-extensions", "1.0.2.RELEASE")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.11.0")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
