# JavaFX Login, Signup, Forgot Password, and Change Password System

This is a JavaFX desktop application built with NetBeans that provides user authentication features including **Signup**, **Login**, **Forgot Password**, and **Change Password** using JDBC and MySQL.

---

## ✅ Features

- User Signup with input validation
- Secure Login system with database authentication
- Forgot Password using email and security questions
- Change Password if you forget it after validation
- UI designed using JavaFX and FXML

---

## 🛠️ Technologies Used

- Java (JDK 17+)
- JavaFX SDK
- MySQL
- JDBC (Java Database Connectivity)
- NetBeans IDE
- Scene Builder (optional, for UI design)

---

## 🗃️ Project Structure

```
├── src/
│   ├── controllers/
│   │   ├── LoginController.java
│   │   ├── SignupController.java
│   │   ├── ForgotPasswordController.java
│   │   └── ChangePasswordController.java
│   ├── db/
│   │   └── DatabaseConnection.java
│   ├── models/
│   │   └── User.java
│   ├── utils/
│   │   └── ValidationUtils.java
│   └── Main.java
├── resources/
│   ├── login.fxml
│   ├── signup.fxml
│   ├── forgot_password.fxml
│   └── change_password.fxml
└── README.md
```

---

## 🛢️ MySQL Database Setup

1. Open MySQL and run:

```sql
CREATE DATABASE USERS_DB;

USE USERS_DB;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    security_question VARCHAR(255),
    security_answer VARCHAR(255),
    date DATE DEFAULT CURRENT_DATE,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

```

2. Update your database connection in `DatabaseConnection.java`:

```java
String url = "jdbc:mysql://localhost:3306/USERS_DB";
String user = "root";
String password = "your_password";
```

---

## 🚀 How to Run

1. Open the project in NetBeans.
2. Ensure JavaFX is configured properly in project settings.
3. Add the JavaFX SDK and VM options if needed.
4. Run the `Main.java` file.

---

## 🧠 Tips

- Passwords can be hashed using libraries like BCrypt for extra security.
- You can extend user features like profile photo, roles, phone numbers, etc.
- UI styling can be done via CSS.

---

## 📄 License

This project is free and open-source for learning purposes.
