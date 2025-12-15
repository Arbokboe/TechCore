package com.example.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsResponse {
    private String status;
    private Integer totalResults;
    private List<Article> articles;

    @Data
    public static class Article {
        private Source source;
        private String author;
        private String title;
        private String description;
        private String url;

        @Data
        public static class Source {
            private String id;
            private String name;
        }
    }
}