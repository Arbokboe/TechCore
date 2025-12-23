package org.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimiterFilter implements GatewayFilter {

    private final Map<String, Integer> counters = new HashMap<>();
    private final Map<String, Long> resetTimes = new HashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        if (!path.equals("/api/auth/login")) {
            return chain.filter(exchange);
        }

        String ip = exchange.getRequest().getRemoteAddress() != null ?
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() :
                "unknown";

        long now = System.currentTimeMillis();

        Long resetTime = resetTimes.get(ip);
        if (resetTime == null || now - resetTime > 60000) {
            counters.put(ip, 1);
            resetTimes.put(ip, now);
            return chain.filter(exchange);
        }

        Integer count = counters.get(ip);
        if (count == null || count < 5) {
            counters.put(ip, (count == null ? 1 : count + 1));
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }
}
