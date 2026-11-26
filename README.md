# 🏥 AI-Powered Healthcare & Smart Patient Assistance System

## 📌 Project Overview

The **AI-Powered Healthcare & Smart Patient Assistance System** is a full-stack web application designed to simplify healthcare management by connecting patients, doctors, and healthcare services through a centralized platform.

The system provides patient management, doctor management, appointment scheduling, medical reports, medical history, and an AI-powered healthcare assistant.

It aims to improve healthcare accessibility, reduce manual work, and provide a better experience for patients and healthcare professionals.

## 🚀 Features

### 👤 User Management
- User registration and login.
- Secure authentication using JWT.
- Role-based access control.

### 🧑‍⚕️ Patient Management
- Add and manage patient details.
- View patient information.
- Update and delete patient records.

### 👨‍⚕️ Doctor Management
- Add and manage doctor profiles.
- View doctor information.
- Update and delete doctor records.

### 📅 Appointment Management
- Book appointments.
- View appointment details.
- Update and cancel appointments.
- Manage appointment status.

### 📋 Medical Reports
- Add and manage medical reports.
- View patient medical reports.
- Update and delete report records.

### 📖 Medical History
- Maintain patient medical history.
- View medical history records.
- Update and delete medical history.

### 🤖 AI Healthcare Assistant
- Integrated with Google Gemini API.
- Provides AI-powered healthcare-related assistance.
- Helps users understand general health information.

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- REST APIs
- Maven
- JWT Authentication

### Frontend
- React.js
- JavaScript
- HTML5
- CSS3
- Bootstrap
- Axios
- React Router
- Vite

### Database
- MySQL

### AI Integration
- Google Gemini API

### Tools
- Eclipse IDE
- Visual Studio Code
- MySQL Workbench
- Git
- GitHub

## 📂 Project Structure

```text
AI-PoweredHealthcare&SmartPatientAssistanceSystem/
│
├── healthcare-backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
│   ├── pom.xml
│   └── mvnw
│
├── healthcare-frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── services/
│   ├── package.json
│   └── vite.config.js
│
└── README.md
```

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/venkatakrishna999/AI-Powered-Healthcare-SmartPatientAssistanceSystem.git
```

### 2. Backend Setup

Navigate to the backend folder:

```bash
cd healthcare-backend
```

Configure your MySQL database and environment variables.

Set the required configuration values:
- Database URL
- Database username
- Database password
- Gemini API key
- JWT secret

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Backend runs at:

```text
http://localhost:9966
```

### 3. Frontend Setup

Open another terminal and navigate to the frontend folder:

```bash
cd healthcare-frontend
```

Install dependencies:

```bash
npm install
```

Start the React application:

```bash
npm run dev
```

Frontend runs at the Vite development URL displayed in the terminal.

## 🔐 Security

- JWT-based authentication.
- Protected application endpoints.
- Sensitive credentials should be stored in environment variables.
- API keys and database passwords must never be committed to GitHub.

## 🎯 Project Objective

The main objective of this project is to develop a centralized healthcare management platform that combines modern web technologies and artificial intelligence to simplify patient care and healthcare administration.

## 👨‍💻 Developer

**PAMARTHI VENKATAKRISHNA**

GitHub: https://github.com/venkatakrishna999

## 📄 License

This project is developed for educational and learning purposes.
