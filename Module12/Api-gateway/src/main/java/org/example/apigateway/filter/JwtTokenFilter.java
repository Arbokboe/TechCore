package org.example.apigateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apigateway.security.JwtTokenProvider;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenFilter implements GatewayFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = jwtTokenProvider.resolveToken(exchange.getRequest());
        String path = exchange.getRequest().getPath().value();

        if (path.startsWith("/api/auth/")) {
            log.debug("Skipping JWT check for auth endpoint");
            return chain.filter(exchange);
        }
        // Можно сделать в проде Service to Service аутентификацию
        // String serviceToken = jwtTokenProvider.generateServiceToken();

        if (token != null && jwtTokenProvider.validateToken(token)) {
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", jwtTokenProvider.getUserIdFromToken(token))
                    .header("X-User-Username", jwtTokenProvider.getUsernameFromToken(token))
                    .header("X-User-Roles", jwtTokenProvider.getRolesFromToken(token))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } else {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}