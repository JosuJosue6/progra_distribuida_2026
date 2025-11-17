plugins {
    id("java")
    id("war")
}

group = "com.programcion.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}