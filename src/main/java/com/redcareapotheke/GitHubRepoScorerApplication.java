package com.redcarepharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GitHubRepoScorerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitHubRepoScorerApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .defaultHeader("User-Agent", "github-scorer-app")
                .build();
    }
}

