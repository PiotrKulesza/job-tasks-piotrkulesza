# GitHub Repository Fetcher (Quarkus + Mutiny)

## 🚀 Introduction

This project is a **Quarkus-based REST API** that retrieves a list of repositories from GitHub for a given username. The API is built using **Quarkus, Mutiny (reactive programming), and GitHub API (org.kohsuke.github)**.

## 🛠 Technologies Used

- **Quarkus** (RESTEasy Reactive, Mutiny)
- **GitHub API (org.kohsuke.github)**
- **JUnit 5 & RestAssured** (for testing)
- **Gradle** (for dependency management)

## 📌 Features

- Fetch all repositories for a given GitHub username.
- Retrieve branch details for each repository, including **branch name** and **last commit SHA**.
- Return **404 Not Found** when the user does not exist.
- Handle API failures gracefully with proper error responses.

## 📖 API Endpoints

### ✅ Fetch Repositories for a User
```http
GET /github/{username}
```
#### 🔹 Response (200 OK)
```json
[
  {
    "repositoryName": "example-repo",
    "ownerLogin": "octocat",
    "branches": [
      { "name": "main", "lastCommitSha": "abc123" },
      { "name": "dev", "lastCommitSha": "def456" }
    ]
  }
]
```

#### ❌ User Not Found (404)
```json
{
  "message": "User 'nonexistent-user' not found."
}
```

## 🚦 Running the Project
### 🔹 Prerequisites
- Java 17+
- Gradle

### 🔹 Running Locally
```sh
git clone https://github.com/PiotrKulesza/job-tasks-piotrkulesza.git
cd job-tasks-piotrkulesza
./gradlew quarkusDev
```

## 🧪 Running Tests
Run the tests using:
```sh
./gradlew test
```
### ✅ Test Cases
| # | Description | Expected Result |
|---|-------------------------------|----------------|
| 1 | User exists with repositories | `200 OK` + repo list |
| 2 | User does not exist | `404 Not Found` |
| 3 | User exists but has no repositories | `200 OK` + empty list |
| 4 | Response time should be < 2s | Pass |
| 5 | User's repo should have 'main' branch | `branches` contains `main` |
| 6 | Repo exists but has no branches | `branches` is `[]` |


---

Author:Piotr Kulesza

