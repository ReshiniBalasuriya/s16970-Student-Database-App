package com.studentdb;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 5) {
            try {
                System.out.println("\n--- Student Database Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        try {
                            System.out.print("Name: ");
                            String name = sc.nextLine();

                            System.out.print("Age: ");
                            int age = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Email: ");
                            String email = sc.nextLine();

                            System.out.print("Stream: ");
                            String stream = sc.nextLine();

                            dao.addStudent(new Student(name, age, email, stream));
                        } catch (InputMismatchException e) {
                            System.out.println("⚠ Invalid input for Age. Please enter a number.");
                            sc.nextLine(); // clear wrong input
                        }
                    }
                    case 2 -> {
                        List<Student> students = dao.getAllStudents();
                        if (students.isEmpty()) {
                            System.out.println("⚠ No students found.");
                        } else {
                            System.out.println("\nID | Name | Age | Email | Stream");
                            for (Student s : students) {
                                System.out.println(s);
                            }
                        }
                    }
                    case 3 -> {
                        try {
                            System.out.print("Enter ID to update: ");
                            int idU = sc.nextInt();
                            sc.nextLine();

                            System.out.print("New Name: ");
                            String nameU = sc.nextLine();

                            System.out.print("New Age: ");
                            int ageU = sc.nextInt();
                            sc.nextLine();

                            System.out.print("New Email: ");
                            String emailU = sc.nextLine();

                            System.out.print("New Stream: ");
                            String streamU = sc.nextLine();

                            dao.updateStudent(new Student(idU, nameU, ageU, emailU, streamU));
                        } catch (InputMismatchException e) {
                            System.out.println("⚠ Invalid input. Please try again.");
                            sc.nextLine();
                        }
                    }
                    case 4 -> {
                        try {
                            System.out.print("Enter ID to delete: ");
                            int idD = sc.nextInt();
                            sc.nextLine();
                            dao.deleteStudent(idD);
                        } catch (InputMismatchException e) {
                            System.out.println("⚠ Invalid ID format. Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    case 5 -> System.out.println("Exiting program...");
                    default -> System.out.println("⚠ Invalid choice! Please select a number between 1 and 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ Please enter a valid number for menu choice.");
                sc.nextLine(); // clear invalid input
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        sc.close();
    }
}