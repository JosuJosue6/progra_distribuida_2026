plugins {
    id("java")
    //id("application")
    id("io.freefair.lombok") version "9.1.0"
    id("com.gradleup.shadow") version "9.2.0"//agarra todas las dependencias y las guarda en un solo archivo


}

group = "org.example"
version = "all"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.helidon.microprofile.server/helidon-microprofile-server
    implementation("io.helidon.microprofile.server:helidon-microprofile-server:4.3.2")
    implementation("io.helidon.http.media:helidon-http-media-jsonp:4.3.2")
    implementation("io.helidon.http.media:helidon-http-media-jsonb:4.3.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.programacion.distribuida.MiApplicacionMain"
    }
}