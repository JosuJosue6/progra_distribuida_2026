package com.programacion.distribuida.config;

import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsulConfig {

    public ConsulRegistrationCustomizer customizer() {
        return registration -> {
            var tags = registration.getService().getTags();

            tags.add("traefik.enable=true");
            tags.add("traefik.http.routers.customers.rule=PathPrefix(`/app-customers`)");
            tags.add("traefik.http.middlewares.customers-stripprefix.stripPrefix.prefixes=/app-customers");
            tags.add("traefik.http.routers.customers.middlewares=customers-stripprefix");

        };
    }
}
