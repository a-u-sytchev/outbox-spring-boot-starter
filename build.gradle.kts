plugins {
    val kotlinVersion = "2.0.20"

    `maven-publish`
    kotlin("jvm") version kotlinVersion
}

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

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.1")

    implementation("org.flywaydb:flyway-core:11.1.1")

    implementation("org.springframework.kafka:spring-kafka:3.3.1")

    implementation("org.springframework.boot:spring-boot-starter:3.4.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}