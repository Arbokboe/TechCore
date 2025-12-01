package com.example.controller;

import com.example.dto.NewsResponse;
import com.example.service.newsService.NewsService;
import com.example.service.newsService.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class NewsController {

    private final NewsService newsService;
    private final WebClientService WebClientService;

    @GetMapping("/news")
    public NewsResponse getNewsWithRestTemplate() {
        return newsService.getTopNews();
    }

    @GetMapping("/webclient")
    public Mono<NewsResponse> getNewsWithWebClient() {
        return WebClientService.getTopNews();
    }

}
