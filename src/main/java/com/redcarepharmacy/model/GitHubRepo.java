package com.redcarepharmacy.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
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

}

