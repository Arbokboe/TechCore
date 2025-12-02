package com.example.web.service;

import com.example.web.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class WebClientService {

    private final WebClient webClient;

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.country}")
    private String country;

    public Mono<NewsResponse> getTopNews() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("country", country)
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsResponse.class);
    }
}
