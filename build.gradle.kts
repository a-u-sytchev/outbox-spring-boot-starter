plugins {
    val kotlinVersion = "2.0.20"

    `maven-publish`
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "3.3.3"
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
}
apply(plugin = "io.spring.dependency-management")

group = "support"
version = "1.0.0"
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
            version = "1.0.0"
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

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.flywaydb:flyway-core:9.16.0")

    implementation("org.springframework.kafka:spring-kafka")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}