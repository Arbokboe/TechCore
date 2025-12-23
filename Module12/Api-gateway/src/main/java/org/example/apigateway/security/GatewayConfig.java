package org.example.apigateway.security;

import org.example.apigateway.filter.JwtTokenFilter;
import org.example.apigateway.filter.RateLimiterFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtTokenFilter jwtTokenFilter, RateLimiterFilter rateLimiterFilter) {
        return builder.routes()
                .route("user-service-all", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f
                                .filter(rateLimiterFilter))
                        .uri("lb://USER-SERVICE"))

                .route("book-service-all", r -> r
                        .path("/api/book/**")
                        .filters(f -> f
                                .filter(jwtTokenFilter))
                        .uri("lb://BOOK-SERVICE"))

                .route("book-service-rewrite", r -> r
                        .path("/library/v1/book/**")
                        .filters(f -> f
                                .rewritePath("/library/v1/book/(?<segment>.*)", "/api/book/${segment}")
                                .filter(jwtTokenFilter)
                        )
                        .uri("lb://BOOK-SERVICE"))
                .build();
    }
}
