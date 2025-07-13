# GitHub Repo Score

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

### Setup

1. Clone the repository

```bash
git clone git@github.com:RaghdaElgharably/github-repos-score.git
cd github-repo-score
```

### To build the project:
```
mvn clean install
```

## Assumptions:
- The url of the entry point of the application: 
```
http://localhost:8080/api/repos/score?language=Java&createdAfter=2024-01-01&page=1&perPage=100
```
- The url will never result in an empty results so no error handling was done for the lack of time.
- The app queries github for every possible request but as we are limited by github's rate limiter and max page, I assumed that's the right behavior.
  - as github only allows 1000, those are the records that the app scores and ranks.
- The score equation is: score = stars * 2.0 + forks + freshnessScore;