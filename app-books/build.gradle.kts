plugins {
    id("java")
    id ("io.quarkus") version "3.29.3"
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.programacion.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusVersion = "3.29.3"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation (enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion"))
    //CDI
    implementation ("io.quarkus:quarkus-arc:${quarkusVersion}")

    //REST
    implementation ("io.quarkus:quarkus-rest")
    implementation ("io.quarkus:quarkus-rest-jsonb")

    //DB

    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    implementation("org.modelmapper:modelmapper:3.2.6")

    //-- Cliente Rest
    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-rest-client-jsonb")
}

tasks.test {
    useJUnitPlatform()
}