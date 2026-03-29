# API Gateway with Rate Limiting 🚀

## 📌 Project Overview
This project implements an API Gateway using Spring Boot with Redis-based Token Bucket Rate Limiting.

## 🚀 Features
- Spring Boot REST API
- Redis Integration
- Token Bucket Algorithm
- Per-user request limiting
- Logging for monitoring

## 🛠 Tech Stack
- Java
- Spring Boot
- Redis

## ▶️ How to Run
1. Start Redis:
   docker run -p 6379:6379 redis

2. Run the application:
   ./mvnw spring-boot:run

## 📡 API Testing
GET http://localhost:8080/api/data  
Header: user=bindu

## 📊 Example Response
- ✅ Success
- ❌ Too Many Requests (429)
