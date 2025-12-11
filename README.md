# 📘 MVC CRUD Application using JSP, Servlet, and JDBC (PostgreSQL)

A clean and modular **MVC web application** built using **JSP, Servlets, JDBC, and PostgreSQL**.  
This project demonstrates a professional backend structure with proper MVC layering, centralized error handling, Bootstrap UI, and complete CRUD operations.

GitHub Repository: **https://github.com/sureshagrawal/crudapp**

---

## 🚀 Features

### ✔ Core CRUD Operations
- Add a new student  
- List all students  
- Edit/update student  
- Delete student  
- Success notifications after each operation  
- Validation (client-side + server-side)

### ✔ MVC Architecture
- **Model:** `Student.java`  
- **DAO Layer:**  
  - `StudentDAO.java`  
  - `StudentDAOImpl.java`  
  - `DAOException.java` *(inside exception package)*  
- **Controller:** `StudentServlet.java`  
- **View:** JSP pages (`student-list.jsp`, `student-form.jsp`, `error.jsp`)

### ✔ UI / UX Enhancements
- Bootstrap-based responsive design  
- Modern table layout  
- Validation error messages  
- Confirmation on delete  
- Form data retention on validation failure  

### ✔ Error Handling
- Centralized `error.jsp`  
- Custom `DAOException` inside `exception` package  
- Root cause details shown on development mode  

---

## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| Frontend | JSP, HTML5, Bootstrap 5 |
| Controller | Servlets (Jakarta EE 10) |
| Backend | Java 21+ |
| Database | PostgreSQL |
| DB Access | JDBC (Prepared Statements) |
| Server | Apache Tomcat 11 |
| Build Tool | Maven |

---

## 📂 Project Structure

```
crudapp/
│
├── src/main/java/com/nsgacademy/crudmvc/
│   ├── model/
│   │   └── Student.java
│   │
│   ├── dao/
│   │   ├── StudentDAO.java
│   │   ├── StudentDAOImpl.java
│   │
│   ├── exception/
│   │   └── DAOException.java
│   │
│   ├── utils/
│   │   └── JDBCUtils.java
│   │
│   └── web/
│       └── StudentServlet.java
│
└── src/main/webapp/
    ├── student-list.jsp
    ├── student-form.jsp
    ├── error.jsp
    └── WEB-INF/
        └── web.xml (optional)
```

---

## 🗄️ Database Setup (PostgreSQL)

Create database:

```sql
CREATE DATABASE cruddb;
```

Create table:

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mobile VARCHAR(15) NOT NULL
);
```

Update **JDBCUtils.java**:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/cruddb";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password";
```

---

## ⚙️ How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/sureshagrawal/crudapp.git
```

### 2️⃣ Import into your IDE
- IntelliJ IDEA / Eclipse / VS Code  
- Open as **Maven Project**

### 3️⃣ Configure PostgreSQL
- Ensure DB `cruddb` exists  
- Run the table creation SQL  

### 4️⃣ Configure Tomcat
- Add Tomcat 11  
- Deploy the WAR or exploded artifact  

### 5️⃣ Access the application

```
http://localhost:8080/crudapp/
```

---

## 🧪 Validation Features

### Client-Side
- Required fields  
- Email format validation  
- Mobile number regex (10 digits)  
- Name pattern restrictions  

### Server-Side
- All input validated again on the backend  
- Errors displayed inline on form  
- User input retained after validation failure  

---

## 🐞 Error Handling

- All database errors wrapped in `DAOException`  
- `StudentServlet` catches and forwards to `error.jsp`  
- Error page displays:
  - Error message  
  - Exception stack trace  
  - Root cause for debugging  

---

## 🔮 Future Enhancements (Planned)

- Pagination  
- Sorting  
- Searching  
- Student photo upload  
- Gender + DOB + Age calculation  
- Import (CSV/Excel)  
- Export (CSV/Excel/PDF)  
- Login system & roles (User / Admin)  
- Audit logs  
- Deployment configuration  

---

## 📸 Screenshots (Add Later)

### ▶ Student List Page  
<img width="1920" height="1080" alt="student-list" src="https://github.com/user-attachments/assets/349dc6fe-8e94-4b40-933a-5c322345f849" />


### ▶ Add/Edit Form and Delete 

<img width="1920" height="1080" alt="delete" src="https://github.com/user-attachments/assets/0b2cd727-d45c-4fc8-9379-bcb74487febe" />
<img width="1920" height="1080" alt="edit" src="https://github.com/user-attachments/assets/eee0f14f-569b-4e25-bf13-9331d0ce09b5" />
<img width="1920" height="1080" alt="add" src="https://github.com/user-attachments/assets/39a31df2-9492-452d-9515-344599bc28de" />
<img width="1920" height="1080" alt="after_delete" src="https://github.com/user-attachments/assets/3ffbdf50-d632-4610-91f8-9a2ce5e6023d" />

### ▶ Error Page  
<img width="1920" height="1080" alt="error" src="https://github.com/user-attachments/assets/08de0629-d507-4fca-9a93-f5b4949b4b59" />


---

## 🤝 Contributing

Pull requests and feature suggestions are welcome!  

---

## 📄 License

This project is open-source and licensed under the **MIT License**.

---

## 🙏 Acknowledgement

Developed as a modular and extendable CRUD foundation before adding advanced features such as import/export, authentication, and more.
