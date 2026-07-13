# 🚀 Employee Management System - Spring Boot

A modern, full-stack Employee Management System built with **Spring Boot 3.4**, **Spring Security**, **Spring Data JPA**, and a beautiful animated frontend using **Thymeleaf** and **Chart.js**.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

---

## ✨ Features

### 🔐 Authentication & Security
- **Secure Login** with Spring Security & BCrypt password encoding
- **Role-based access control** (ADMIN, MANAGER, USER)
- **Session management** with logout functionality
- **3 Demo accounts** pre-configured (admin/manager/user)

### 👥 Employee Management (CRUD)
- **Create** new employees with validation
- **Read** employee data with pagination & sorting
- **Update** employee information
- **Delete** employees (soft delete)
- **Search** by name, ID, or department
- **Filter** by department and designation

### 📊 Dashboard & Analytics
- **Animated statistics cards** with real-time data
- **Interactive charts** (Bar, Line, Doughnut, Radar, Bubble)
- **Department & Designation distribution**
- **Salary analysis & trends**
- **Employee growth tracking**

### 🎨 UI/UX
- **Beautiful animated login page** with particle effects
- **Collapsible sidebar** navigation
- **Responsive design** for all screen sizes
- **Toast notifications** for user feedback
- **Loading animations** & smooth transitions
- **Keyboard shortcuts** (Ctrl+N for new employee)

### 🏗️ Architecture
- **Layered architecture** (Controller → Service → Repository)
- **RESTful API** endpoints
- **DTO validation** with Bean Validation
- **Database abstraction** (MySQL or H2)
- **WebSocket support** ready for real-time updates

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Spring Boot 3.4, Spring Security, Spring Data JPA, Spring WebSocket |
| **Frontend** | Thymeleaf, HTML5, CSS3, Vanilla JavaScript, Chart.js 4.4 |
| **Database** | MySQL 8+ (Production) / H2 (Development) |
| **Build Tool** | Maven |
| **Java Version** | Java 21 (LTS) |

---

## 📁 Project Structure

```
employee-management/
├── src/main/java/com/empmanagement/
│   ├── EmployeeManagementApplication.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   ├── DataInitializer.java
│   │   └── WebSocketConfig.java
│   ├── controller/
│   │   ├── PageController.java
│   │   └── EmployeeRestController.java
│   ├── model/
│   │   ├── Employee.java
│   │   └── User.java
│   ├── repository/
│   │   ├── EmployeeRepository.java
│   │   └── UserRepository.java
│   └── service/
│       ├── EmployeeService.java
│       └── UserService.java
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html
│   │   ├── dashboard.html
│   │   ├── employees.html
│   │   ├── reports.html
│   │   └── profile.html
│   ├── application.properties
│   ├── application-dev.properties
│   └── data.sql
└── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- MySQL 8.0+ (optional, for production)

### 1. Clone & Navigate
```bash
cd employee-management-springboot
```

### 2. Configure Database

**Option A: H2 Database (Development - Default)**
No configuration needed! The app uses H2 in-memory database by default with pre-loaded sample data.

**Option B: MySQL (Production)**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build & Run
```bash
# Using Maven Wrapper (if available)
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run

# Or build JAR and run
mvn clean package
java -jar target/employee-management-1.0.0.jar
```

### 4. Access the Application
- **Application URL**: http://localhost:8080
- **H2 Console** (dev only): http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:employee_db`
  - Username: `sa`
  - Password: *(empty)*

---

## 🔑 Demo Accounts

| Role | Username | Password | Access |
|------|----------|----------|--------|
| **Admin** | `admin` | `admin` | Full access (CRUD + Admin) |
| **Manager** | `manager` | `manager` | CRUD operations |
| **User** | `user` | `user` | View only |

> ⚠️ **Note**: Passwords are automatically BCrypt-hashed on first run.

---

## 📡 API Endpoints

### Employee API
| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| GET | `/api/employees` | List all employees (paginated) | Any |
| GET | `/api/employees/{id}` | Get employee by ID | Any |
| POST | `/api/employees` | Create new employee | ADMIN, MANAGER |
| PUT | `/api/employees/{id}` | Update employee | ADMIN, MANAGER |
| DELETE | `/api/employees/{id}` | Delete employee | ADMIN only |
| GET | `/api/employees/search?keyword={q}` | Search employees | Any |
| GET | `/api/employees/stats` | Dashboard statistics | Any |

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/login` | Login page |
| POST | `/login` | Login submission |
| GET | `/logout` | Logout |

---

## 🎯 Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `Ctrl + N` | Open "Add Employee" modal |
| `Escape` | Close any modal |
| `Enter` | Submit search / login |

---

## 🎨 Customization

### Changing Colors
Edit CSS variables in any HTML file:
```css
:root {
    --accent: #00d4ff;        /* Primary accent color */
    --accent-dark: #0099ff;   /* Darker accent */
    --success: #00ff88;       /* Success color */
    --danger: #ff6b6b;        /* Error/Danger color */
}
```

### Adding New Departments/Designations
Edit the enums in `Employee.java`:
```java
public enum Department {
    IT, HR, FINANCE, MARKETING, OPERATIONS, SALES, ADMIN
}

public enum Designation {
    INTERN, ASSOCIATE, SENIOR, LEAD, MANAGER, DIRECTOR, VP, CEO
}
```

---

## 🔒 Security Features

- BCrypt password hashing (strength 10)
- CSRF protection disabled for API endpoints
- Session timeout: 30 minutes
- Role-based URL access control
- Concurrent session control (1 session per user)
- Secure logout with session invalidation

---

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080
# Kill it
kill -9 <PID>
# Or change port in application.properties
server.port=8081
```

### Database Connection Issues
```bash
# For MySQL - ensure database exists
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS employee_db;"
```

### Maven Build Issues
```bash
# Clean and rebuild
mvn clean install -U
```

---

## 📈 Future Enhancements

- [ ] Email notifications for employee actions
- [ ] Excel/CSV import/export
- [ ] Advanced reporting with date ranges
- [ ] Employee photo upload
- [ ] Real-time updates via WebSocket
- [ ] Mobile app (React Native/Flutter)
- [ ] Docker containerization
- [ ] Kubernetes deployment

---

## 📝 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

Converted from Java Swing to Modern Spring Boot Web Application.

**Happy Coding! 🎉**
