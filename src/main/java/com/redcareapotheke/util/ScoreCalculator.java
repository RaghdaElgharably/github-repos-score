package com.redcareapotheke.util;

import com.redcareapotheke.model.GitHubRepo;

import java.time.Duration;
import java.time.ZonedDateTime;
public class ScoreCalculator {

    /**
     * the function calculates the score of repos. the equation is stars * 2 + forks + freshness
     * @param repo
     * @return
     */
    public static double calculate(GitHubRepo repo) {
        int stars = repo.getStargazersCount();
        int forks = repo.getForksCount();
        long daysSinceUpdate = Duration.between(repo.getUpdatedAt(), ZonedDateTime.now()).toDays();

        double freshness = Math.max(1, 365.0 / (daysSinceUpdate + 1));  // More recent = higher score

        return stars * 2 + forks + freshness;
    }
}

