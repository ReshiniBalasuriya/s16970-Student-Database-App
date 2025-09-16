package com.studentdb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Adding a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, email, stream) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getStream());
            stmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("stream")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // Updating student information
    public void updateStudent(Student student) {
        String checkSql = "SELECT COUNT(*) FROM students WHERE id=?";
        String sql = "UPDATE students SET name=?, age=?, email=?, stream=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, student.getId());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                System.out.println("⚠ No student found with ID " + student.getId());
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, student.getName());
                stmt.setInt(2, student.getAge());
                stmt.setString(3, student.getEmail());
                stmt.setString(4, student.getStream());
                stmt.setInt(5, student.getId());
                stmt.executeUpdate();
                System.out.println("Student updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Deleting student information
    public void deleteStudent(int id) {
        String checkSql = "SELECT COUNT(*) FROM students WHERE id=?";
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                System.out.println("⚠ No student found with ID " + id);
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Student deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}