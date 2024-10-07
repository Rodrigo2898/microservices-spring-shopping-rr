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

//    @Bean
//    public RouterFunction<ServerResponse> productServiceRoute() {
//        return route("product_service")
//                .route(RequestPredicates.path("/api/product"),
//                        HandlerFunctions.http("http://localhost:8080"))
//                .build();
//    }

    @Bean
    public RouteLocator productServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service", r-> r.path("/api/product")
                        .uri(productServiceUrl)).build();
    }
}
