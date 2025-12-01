package com.example.service.newsService;

import com.example.dto.NewsResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class NewsService {

    private final RestTemplate restTemplate;

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String apiUrl;

    @Value("${news.api.country}")
    private String country;


    public NewsResponse getTopNews() {
        String url = String.format("%s?&country=%s&apiKey=%s", apiUrl, country, apiKey);
        return restTemplate.getForObject(url, NewsResponse.class);
    }
}
