package com.redcarepharmacy.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class GitHubRepo {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int stargazersCount;

    @Getter
    @Setter
    private int forksCount;

    @Getter
    @Setter
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    private double score;

    @Getter
    @Setter
    private String language;

    public GitHubRepo(String name, int stars, int forks, LocalDateTime updatedAt) {
        this.name = name;
        this.stargazersCount = stars;
        this.forksCount = forks;
        this.updatedAt = updatedAt;
    }
    public GitHubRepo(){

    }
}

