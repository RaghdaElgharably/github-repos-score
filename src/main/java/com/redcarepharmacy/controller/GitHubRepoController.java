package com.redcarepharmacy.controller;


import com.redcarepharmacy.service.GitHubRepoService;
import com.redcarepharmacy.model.GitHubRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repos")
public class GitHubRepoController {

    private final GitHubRepoService service;

    public GitHubRepoController(GitHubRepoService service) {
        this.service = service;
    }

    @GetMapping("/score")
    public List<GitHubRepo> getScoredRepos(
            @RequestParam("language") String language,
            @RequestParam("createdAfter")  String createdAfter,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "30") int perPage) {
        return service.fetchAndScoreRepos(language, createdAfter, page, perPage);
    }
}

