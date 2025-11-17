plugins {
    id("java")
}

group = "com.programacion.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

tasks.test {
    useJUnitPlatform()
}