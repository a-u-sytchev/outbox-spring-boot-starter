plugins {
    val kotlinVersion = "2.0.20"

    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    `maven-publish`
}

group = "support"
version = "1.0.5"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("github") {
            from(components["java"])
            groupId = "ru.a-u-sytchev"
            artifactId = "outbox-spring-boot-starter"
            version = "1.0.5"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/a-u-sytchev/outbox-spring-boot-starter")

            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("PAT_TOKEN")
            }
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.4")
    }
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.1")

    implementation("org.flywaydb:flyway-core:11.1.1")

    implementation("org.springframework.kafka:spring-kafka:3.3.1")

    implementation("org.springframework.boot:spring-boot-starter:3.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}
tasks.jar {
    enabled = true
}