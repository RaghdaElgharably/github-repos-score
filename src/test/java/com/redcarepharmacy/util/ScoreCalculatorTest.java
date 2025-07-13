package com.redcarepharmacy.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.redcarepharmacy.model.GitHubRepo;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    @Test
    public void testCalculate() {
        // Arrange
        GitHubRepo repo = new GitHubRepo();
        repo.setStargazersCount(10);
        repo.setForksCount(5);
        repo.setUpdatedAt(LocalDateTime.now().minusDays(30));  // updated 30 days ago

        // Act
        double score = ScoreCalculator.calculate(repo);

        // Expected freshness: 365 / (30 + 1) = 365 / 31 = 11.77 (approx)
        // Score = stars * 2 + forks + freshness
        //       = 10*2 + 5 + 11.77 = 20 + 5 + 11.77 = 36.77 approx

        double expectedFreshness = 365.0 / 31;
        double expectedScore = 10 * 2 + 5 + expectedFreshness;

        // Assert (with a small delta for floating point precision)
        assertEquals(expectedScore, score, 0.0001);
    }
}
