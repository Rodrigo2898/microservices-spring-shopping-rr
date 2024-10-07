package com.rr.microservice.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Value("${product.service.url}")
    private String productServiceUrl;
    @Value("${order.service.url}")
    private String orderServiceUrl;
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public RouteLocator productServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service", r-> r.path("/api/product")
                        .uri(productServiceUrl)).build();
    }

    @Bean
    public RouteLocator orderServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order_service", r -> r.path("/api/order")
                        .uri(orderServiceUrl)).build();
    }

    @Bean
    public RouteLocator inventoryServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inventory_service", r -> r.path("/api/inventory")
                        .uri(inventoryServiceUrl)).build();
    }
}
