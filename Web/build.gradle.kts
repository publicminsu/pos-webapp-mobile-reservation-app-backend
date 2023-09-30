import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    java
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.hknuSC"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20-RC")

    implementation("org.springframework.boot:spring-boot-starter-mail:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.0.4")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
    implementation("org.postgresql:postgresql:42.5.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")

    implementation("org.springframework.boot:spring-boot-starter-security:3.0.4")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
