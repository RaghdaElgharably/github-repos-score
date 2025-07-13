# GitHub Repo Scorer

A Java application that fetches GitHub repositories based on language and creation date, and assigns a popularity score using stars, forks, and recency of updates.

## Features

- Query GitHub public repositories via GitHub Search API
- Filter repositories by programming language and creation date
- Calculate a popularity score combining stars, forks, and update recency
- Support pagination of results

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- GitHub Personal Access Token (optional, for higher rate limits)

### Setup

1. Clone the repository

```bash
git clone https://github.com/yourusername/github-repo-scorer.git
cd github-repo-scorer
```
## Build and Run
export GITHUB_TOKEN=your_personal_access_token

### To build the project:
```
mvn clean install
```

## Assumptions:
- An example for the entry point to the application: 
```
http://localhost:8080/api/repos/score?language=Java&createdAfter=2024-01-01&page=1&perPage=100
```
- The app assumes that the url will never result in an empty results so no error handling was done for the lack of time.
- The app queries github for every request but as we are limited by github's rate limiter and max page, I assumed that's the right behavior
- The score equation is: double score = stars * 2.0 + forks + freshnessScore;