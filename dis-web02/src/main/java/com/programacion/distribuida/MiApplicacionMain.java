package com.programacion.distribuida;

import io.helidon.http.media.jsonb.JsonbSupport;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

import java.time.LocalDateTime;
import java.util.Map;

public class MiApplicacionMain {

    static JsonBuilderFactory factory = Json.createBuilderFactory(Map.of());

    static void handleHola1(ServerRequest req, ServerResponse resp) {
        var name = req.path().pathParameters().get("name");

        JsonObject response = factory.createObjectBuilder()
                .add("name", "Hello" + name + " ")
                .add("FECHA HORA: ", LocalDateTime.now().toString())
                .build();

        resp.send(response);
    }

    static void handleHola2(ServerRequest req, ServerResponse resp) {
        var name = req.path().pathParameters().get("name");

        Persona p =  new Persona();
        p.setName("HOli");
        p.setFechaHora(LocalDateTime.now().toString());

        resp.send(p);
    }

    public static void main(String[] args) {
        WebServer.builder()
                .port(8080)
                .mediaContext(it ->it
                        .mediaSupportsDiscoverServices(true)
                        .addMediaSupport(JsonbSupport.create())
                        .addMediaSupport(JsonbSupport.create())
                )
                .routing(it -> it
                        .get("/hola1/{name}",MiApplicacionMain::handleHola1)
                        .get("/hola2/{name}",MiApplicacionMain::handleHola2)
                )
                .build()
                .start();
    }
}
