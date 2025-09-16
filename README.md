# Student Database App (Java + JDBC + MySQL)

## Overview
A simple console-based Java application that uses JDBC to manage student records in a MySQL database. Supports add, list, view, update, and delete operations.

## Project structure
```
student-management/
  ├─ README.md
  ├─ sql/
  │  └─ create_database.sql
  └─ src/
     └─ com/
        └─ studentdb/
           ├─ DBConnection.java
           ├─ Student.java
           ├─ StudentDAO.java
           └─ Main.java
```

## Requirements
- Java 8 or later
- IntelliJ IDEA - IDE for Java development
- MySQL server
- JDBC (Connector/J) - Java Database Connectivity

## Setup - Create the database and table
1. Start your MySQL server and open a MySQL client (mysql CLI, MySQL Workbench, phpMyAdmin, etc.).
2. Run the SQL script located at `sql/create_database.sql`:
   ```bash
   mysql -u root -p < sql/create_database.sql
   ```
The SQL creates a database `student_db` and a `students` table (with AUTO_INCREMENT starting at 19200).
```sql
-- Create database
CREATE DATABASE student_db;
USE student_db;

-- Create students table
CREATE TABLE students (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         age INT,
                         stream VARCHAR(100)
) AUTO_INCREMENT=19200;
```
## Edit DB connection
Open `src/com/studentdb/DBConnection.java` and update these values to match your MySQL credentials:

private static final String USER = "root";
private static final String PASSWORD = "your_password";


## Build & Run
1. Download the MySQL Connector/J jar (e.g., `mysql-connector-java-8.0.33.jar`) and place it in the project root.
2. Compile:
   ```bash
   javac -cp .;mysql-connector-java-8.0.33.jar src/com/studentdb/*.java -d out
   ```
3. Run Main.java
4. Use the console menu to manage students. (Add Student, View Students, Update Student, Delete Student, Exit)

## Assumptions / Notes
- The database name used is `student_dbn` and table name is `students`.
- The `students` table has columns: `id, name, email, age, stream`.
- Student IDs are auto-generated starting from 19200.
- Email column is UNIQUE and will cause an error if a duplicate email is inserted.
- The program handles invalid input and non-existing IDs gracefully.
- The app is a console app meant for learning and demo purposes.
