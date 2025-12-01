package com.example.service.NewsService;

import com.example.dto.NewsResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Data
@Service
public class WebClientService {

    private final WebClient webClient;

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.country}")
    private String country;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

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
