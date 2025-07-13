package com.redcarepharmacy.service;


import com.redcarepharmacy.model.GitHubRepo;
import com.redcarepharmacy.util.ScoreCalculator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class GitHubRepoService {

    private final RestTemplate restTemplate;
    private static final int MAX_PAGES = 10;

    public GitHubRepoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * The function queries github for the repos according to their language, creation date. As github
     * only offers 1000 repos per call, we call for these 1k repos and then sort them locally then we filter
     * for the requested repos per page;
     * @param language
     * @param createdAfter
     * @param page
     * @param perPage
     * @return
     */
    public List<GitHubRepo> fetchAndScoreRepos(String language, String createdAfter, int page, int perPage) {
        List<GitHubRepo> results = new ArrayList<>();
        int lastCount = -1;
        for (int currentPage = 1; currentPage <= MAX_PAGES; currentPage++) {
            if(lastCount == -1 || lastCount == 100) {
                String url = String.format(
                        "https://api.github.com/search/repositories?q=language:%s+created:>%s&sort=stars&order=desc&per_page=100&page=%d",
                        language, createdAfter, currentPage
                );
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);
                List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
                if (items == null || items.isEmpty()) break;

                for (Map<String, Object> item : items) {
                    GitHubRepo repo = parseGitHubRepo(item);
                    ScoreCalculator.calculate(repo);
                    results.add(repo);
                }
                lastCount = items.size();
            }else {
                break;
            }

        }
        results.sort(Comparator.comparingDouble(GitHubRepo::getScore).reversed());
        int startIndex = (page-1)*perPage;
        int endIndex = startIndex+ Math.min(perPage, results.size()-startIndex);
        return results.subList(startIndex, endIndex);
    }

    private GitHubRepo parseGitHubRepo(Map<String, Object> item) {
        GitHubRepo repo = new GitHubRepo();
        repo.setName((String) item.get("name"));
        repo.setScore((Integer)item.get("stargazers_count"));
        repo.setForksCount((Integer) item.get("forks_count"));
        String updatedAtStr = (String) item.get("updated_at");
        repo.setUpdatedAt(LocalDate.parse(updatedAtStr.substring(0, 10)).atStartOfDay());
        return repo;
    }
}
