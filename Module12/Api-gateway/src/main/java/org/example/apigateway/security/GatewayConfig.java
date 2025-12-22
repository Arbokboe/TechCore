package org.example.apigateway.security;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtTokenFilter jwtTokenFilter) {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/api/auth/**")
                        .uri("lb://USER-SERVICE"))

                .route("book-service", r -> r
                        .path("/api/book/**")
                        .filters(f -> f
                                .filter(jwtTokenFilter))
                        .uri("lb://BOOK-SERVICE"))
                .build();
    }
}
