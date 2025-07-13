package com.redcarepharmacy;

import com.redcarepharmacy.model.GitHubRepo;
import com.redcarepharmacy.service.GitHubRepoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GitHubRepoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GitHubRepoService service;

    @Test
    void testFetchAndScoreRepos() {
        // Given
        Map<String, Object> fakeRepo = new HashMap<>();
        fakeRepo.put("name", "test-repo");
        fakeRepo.put("stargazers_count", 100);
        fakeRepo.put("forks_count", 50);
        fakeRepo.put("updated_at", "2025-07-10T12:34:56Z");

        Map<String, Object> response = new HashMap<>();
        response.put("items", List.of(fakeRepo));

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Map.class)))
                .thenReturn(response);

        // When
        List<GitHubRepo> result = service.fetchAndScoreRepos("Java", "2024-01-01", 1, 10);

        // Then
        assertEquals(1, result.size());
        GitHubRepo repo = result.get(0);
        assertEquals("test-repo", repo.getName());
        assertEquals(100, repo.getScore());  // Assuming score is initially set to stars
        assertEquals(50, repo.getForksCount());
        assertEquals(LocalDate.of(2025, 7, 10).atStartOfDay(), repo.getUpdatedAt());

    }
}
