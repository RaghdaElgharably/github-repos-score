package com.redcarepharmacy.controller;

import com.redcarepharmacy.model.GitHubRepo;
import com.redcarepharmacy.service.GitHubRepoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(GitHubRepoController.class)
class GitHubRepoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubRepoService gitHubRepoService;

    @Test
    void testGetRepos() throws Exception {
        GitHubRepo repo = new GitHubRepo();
        repo.setName("mock-repo");
        repo.setScore(123.0);
        repo.setForksCount(42);
        repo.setUpdatedAt(LocalDate.of(2025, 7, 10).atStartOfDay());

        List<GitHubRepo> mockRepos = List.of(repo);

        Mockito.when(gitHubRepoService.fetchAndScoreRepos("Java", "2024-01-01", 1, 10))
                .thenReturn(mockRepos);

        mockMvc.perform(get("/api/repos/score")
                        .param("language", "Java")
                        .param("createdAfter", "2024-01-01")
                        .param("page", "1")
                        .param("perPage", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("mock-repo"))
                .andExpect(jsonPath("$[0].score").value(123.0))
                .andExpect(jsonPath("$[0].forksCount").value(42));
    }
}