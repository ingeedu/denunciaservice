
plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    kotlin("plugin.jpa") version "2.3.21"

    id("org.springframework.boot") version "4.1.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.denuncias"
version = "0.0.1-SNAPSHOT"
description = "denunciaservice"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // API REST con Spring MVC
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // Persistencia con PostgreSQL y JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Validacion de datos
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // JSON con Jackson 3 y soporte Kotlin
    implementation("org.springframework.boot:spring-boot-jackson")

    // Reflexion de Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Driver de PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // Pruebas
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",
            "-Xannotation-default-target=param-property"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
