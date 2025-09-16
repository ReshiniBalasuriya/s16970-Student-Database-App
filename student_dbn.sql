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