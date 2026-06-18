# Secure Task Management System

## Project Description
The Secure Task Management System is a Java-based web application built with the Spring Boot framework. It is designed to demonstrate secure software development practices (DevSecOps) by providing users with a safe, isolated environment to create, store, and manage personal task payloads. The application utilizes a Model-View-Controller (MVC) architecture and an H2 in-memory database for rapid, secure data handling.

## Security Features Summary
This application implements strict security controls to mitigate common web vulnerabilities and align with industry standards:
* **Identity & Access Management:** User passwords are cryptographically hashed using BCrypt before being stored in the database.
* **Role-Based Access Control (RBAC):** All authenticated accounts are strictly assigned a `ROLE_USER` designation to restrict access to unauthorized endpoints.
* **BOLA / IDOR Mitigation:** Object-Level Authorization is enforced on the backend. The system mathematically verifies that the currently authenticated session principal matches the database record owner before rendering any task data, preventing cross-account data theft.
* **Audit Logging:** Security events (such as user registration) are automatically logged with timestamps and IP addresses.

## Dependencies
* Java 17+
* Spring Boot Starter Web
* Spring Boot Starter Security
* Spring Boot Starter Data JPA
* Thymeleaf (Templating Engine)
* H2 Database (In-Memory)
* Maven (Build Automation)

## Installation Steps
1. Clone the repository to your local machine using Git:
   `git clone https://github.com/Delta-03/Task-Manager.git`
2. Navigate into the project directory:
   `cd Task-Manager`
3. Ensure you have Java Development Kit (JDK) 17 or higher installed.

## How to Run the App
1. Open the project in your preferred IDE (e.g., IntelliJ IDEA) and run the main application file, OR run the following Maven command in your terminal:
   `mvn spring-boot:run`
2. Once the server starts, open a web browser and navigate to:
   `http://localhost:8080/register`
3. Create a new account, log in, and begin securely managing tasks. To reset the database, simply stop and restart the server.

## System Screenshots
*<img width="1918" height="1197" alt="Screenshot 2026-06-11 223704" src="https://github.com/user-attachments/assets/6a5e8f74-8adb-4c3b-937f-05d8bbf63c47" />*
![Web Application Dashboard](<img width="1918" height="1197" alt="Screenshot 2026-06-11 223704" src="https://github.com/user-attachments/assets/c18f71f8-4d14-476b-b4aa-4863eeac3b21" />)

*<img width="1918" height="1197" alt="Screenshot 2026-06-12 000732" src="https://github.com/user-attachments/assets/8bb8a8b7-e9cf-4bf6-a3b0-46d3cb018bd9" />*
![Burp Suite 500 Error Defense](<img width="1918" height="1197" alt="Screenshot 2026-06-12 000732" src="https://github.com/user-attachments/assets/ffebcfe5-3739-4c2a-9f0d-713ce96dd0f7" />)
