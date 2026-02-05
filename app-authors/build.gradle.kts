plugins {
    id("java")
    id ("io.quarkus") version "3.29.3"
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.programacion.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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

    //Flyway
    implementation("io.quarkus:quarkus-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")

    //Service Discovery
    implementation("io.quarkus:quarkus-smallrye-stork")
    implementation("io.smallrye.stork:stork-service-discovery-consul")
    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-consul-client")

    //DOcker
    implementation("io.quarkus:quarkus-container-image-docker")

    //telemetria
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation("io.quarkus:quarkus-jackson")
}

tasks.test {
    useJUnitPlatform()
}