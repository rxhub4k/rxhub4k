plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.dorongold.task-tree") version "1.5"
    id("com.apollographql.apollo").version("2.2.0")
}

group = "com.nevinsjr.rxhub4k"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")
    implementation("com.apollographql.apollo:apollo-runtime:2.2.0")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.2.0")
    implementation("com.apollographql.apollo:apollo-rx3-support:2.2.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

apollo {
    generateKotlinModels.set(true)
}

ktlint {
    filter {
        exclude { element -> element.file.path.contains("generated/") }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
