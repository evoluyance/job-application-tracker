# 📋 Job Tracker

**Job Tracker** is a Spring Boot web application that allows users to create, view, and manage their own job applications ("Job Requests").  
Each user can only access their own data, protected via **JWT authentication**.

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Security (JWT)
- PostgreSQL
- Hibernate (JPA)
- Lombok
- Maven

---

## 🚀 Key Features

✅ User registration and login  
✅ Endpoint protection using JWT tokens  
✅ Full CRUD operations for job applications  
✅ DTO layers, request validation, and structured logging  
✅ Secure configuration via environment variables

---

## ⚙️ Environment Configuration

To run the application locally, you need to create a `.env` file in the project root with the following variable:

```env
JWT_SECRET=your-secret-key-here
