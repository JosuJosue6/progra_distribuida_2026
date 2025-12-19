package com.programacion.distribuida.books;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BooksLifecycle {

    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "127.0.0.1")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    Integer consulPort;

    @Inject
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    Integer appPort;

    String serviceId;

    public void init(@Observes StartupEvent event, Vertx vertx) {
        System.out.println("******************BooksLifecycle.init()");

        try {
            ConsulClientOptions options = new ConsulClientOptions()
                    .setHost(consulHost)
                    .setPort(consulPort);

            ConsulClient consuClient = ConsulClient.create(vertx, options);

            serviceId = UUID.randomUUID().toString();
            var ipAddress = InetAddress.getLocalHost().getHostAddress();

            var urlCheck = String.format("http://%s:%d/ping", ipAddress, appPort);
            var checkOptions = new CheckOptions()
                    .setHttp(urlCheck)
                    .setInterval("10s")
                    .setDeregisterAfter("10s");

            var tags = List.of(
                    "traefik.enable=true",
                    "traefik.http.routers.books.rule=PathPrefix(`/app-books`)",
                    "traefik.http.middlewares.books-stripprefix.stripPrefix.prefixes=/app-books",
                    "traefik.http.routers.books.middlewares=books-stripprefix"
            );


            ServiceOptions serviceOptions = new ServiceOptions()
                    .setName("app-books")
                    .setId(serviceId)
                    .setAddress(ipAddress)
                    .setPort(appPort)
                    .setCheckOptions(checkOptions)
                    .setTags(tags);

            consuClient.registerService(serviceOptions)
                    .onSuccess(it->{
                        System.out.println("Books service registered in Consul");
                    })
                    .onFailure(ex->{
                        ex.printStackTrace();
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(@Observes ShutdownEvent event, Vertx vertx) {
        System.out.println("BooksLifecycle.stop()");

        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);

        ConsulClient consuClient = ConsulClient.create(vertx, options);

        consuClient.deregisterService(serviceId)
                .onSuccess(it->{
                    System.out.println("Books service deregistered in Consul");
                })
                .onFailure(ex->{
                    ex.printStackTrace();
                });
    }
}
