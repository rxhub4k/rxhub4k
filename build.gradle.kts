plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.dorongold.task-tree") version "1.5"
    id("com.apollographql.apollo").version("2.2.1")
}

group = "com.nevinsjr.rxhub4k"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.7.2")
    testImplementation("io.projectreactor:reactor-test:3.3.7.RELEASE")
    implementation("com.apollographql.apollo:apollo-runtime:2.2.1")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.2.1")
    implementation("com.apollographql.apollo:apollo-rx3-support:2.2.1")
    implementation("io.projectreactor:reactor-core:3.3.7.RELEASE")
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

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
