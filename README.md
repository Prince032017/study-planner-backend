# 🚀 AI Study Planner Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![REST API](https://img.shields.io/badge/API-REST-success)
![Groq AI](https://img.shields.io/badge/AI-Groq-purple)

An AI-powered Study Planner backend built with **Spring Boot** and **Groq LLM**. The application generates personalized week-by-week study plans based on a student's exam, available preparation time, study schedule, and subject strengths/weaknesses.

This project is designed with production-ready development practices, including environment variable management, REST APIs, Maven-based builds, and deployment readiness for Railway.

---

# 📌 Features

* 🤖 AI-powered personalized study plan generation using Groq LLM
* 📅 Week-by-week preparation roadmap
* ⏳ Automatic calculation of remaining days and weeks until the exam
* 📚 Subject-wise study allocation
* 🎯 Prioritization based on weak and strong subjects
* 🔄 Revision planning
* 📝 Previous Year Question (PYQ) recommendations
* ⚠️ Recovery suggestions for missed study sessions
* 🚨 Warning signs and study recommendations
* 🌐 RESTful API architecture
* 🔒 Secure API key management using environment variables

---

# 🛠 Tech Stack

| Technology                 | Purpose                  |
| -------------------------- | ------------------------ |
| Java 21                    | Programming Language     |
| Spring Boot 4.1.0          | Backend Framework        |
| Spring Web                 | REST APIs                |
| Spring WebFlux (WebClient) | Calling Groq API         |
| Maven                      | Dependency Management    |
| Groq API                   | AI Study Plan Generation |

---

# 📂 Project Structure

```text
study-planner-backend
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.Jee.study_planner
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── model
│   │   │       ├── service
│   │   │       └── StudyPlannerApplication.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# ⚙️ Prerequisites

Before running the project, make sure you have:

* Java 21
* IntelliJ IDEA (Community or Ultimate)
* Git
* GitHub Account
* Groq API Key

---

# 🔐 Environment Variables

The project uses environment variables instead of hardcoded secrets.

Configure the following variables:

| Variable     | Description                                       |
| ------------ | ------------------------------------------------- |
| GROQ_API_KEY | Your Groq API Key                                 |
| GROQ_API_URL | `https://api.groq.com/openai/v1/chat/completions` |

Example `application.properties`:

```properties
spring.application.name=study-planner

groq.api.key=${GROQ_API_KEY}
groq.api.url=${GROQ_API_URL}

server.port=${PORT:8080}
```

---

# ▶️ Running the Project

Clone the repository:

```bash
git clone https://github.com/Prince032017/study-planner-backend.git
```

Navigate to the project:

```bash
cd study-planner-backend
```

Build the project:

```bash
./mvnw clean package
```

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

---

# 📡 API

## Generate Study Plan

**POST**

```http
/api/study-plan/generate
```

---

### Sample Request

```json
{
  "examName": "JEE Main",
  "examDate": "2026-12-31",
  "dailyStudyHours": 6,
  "strongSubject": "Chemistry",
  "weakSubject": "Mathematics",
  "currentPreparationLevel": "Intermediate"
}
```

---

### Sample Response

```json
{
  "success": true,
  "examName": "JEE Main",
  "weeksRemaining": 23,
  "daysRemaining": 158,
  "plan": "Generated AI Study Plan..."
}
```

---

# 🏗 Architecture

```text
React Frontend
        │
        ▼
Spring Boot REST API
        │
        ▼
GroqService
        │
        ▼
Groq LLM API
        │
        ▼
Generated Study Plan
```

---

# 🔒 Security

* API keys are never committed to GitHub.
* Sensitive values are managed using environment variables.
* Production-ready configuration for Railway deployment.

---

# ☁ Deployment

This project is designed for deployment on Railway.

Deployment steps:

1. Push the project to GitHub.
2. Connect the repository to Railway.
3. Configure:

   * `GROQ_API_KEY`
   * `GROQ_API_URL`
4. Deploy.
5. Test the live endpoint.

---

# 🚀 Future Enhancements

* User Authentication (JWT)
* PostgreSQL Integration
* Study Plan Persistence
* Progress Tracking Dashboard
* Calendar Integration
* Email Notifications
* PDF Export
* AI Chat Assistant
* Swagger/OpenAPI Documentation
* Docker Support
* CI/CD Pipeline using GitHub Actions

---

# 👨‍💻 Author

**Prince Tyagi**

GitHub: https://github.com/Prince032017

---

## ⭐ If you found this project useful, consider giving it a star.
