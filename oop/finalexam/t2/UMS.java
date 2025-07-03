package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Main UMS class - University Management System
public class UMS {
    private List<Student> students;

    public UMS() {
        this.students = Collections.synchronizedList(new ArrayList<>());
    }

    // Method to add student
    public void addStudent(Student student) {
        if (student != null && !students.contains(student)) {
            students.add(student);
        }
    }

    // Method to remove student
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    // Method to get all students
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    // Required method: printStudentData
    public void printStudentData(Student student) {
        if (student == null) {
            System.out.println("Student is null");
            return;
        }

        System.out.println("========================================");
        System.out.println("Student Information:");
        System.out.println("Name: " + student.getName());
        System.out.println("Surname: " + student.getSurname());
        System.out.println("Country: " + student.getCountry());
        System.out.println("Info: " + student.getInfo());
        System.out.println("========================================");

        // Check if this is yourself (paata shvelidze from georgia)
        if (isMyself(student)) {
            System.out.println("Learning Courses for " + student.getName() + " " + student.getSurname() + " (Your Argus Courses):");
            List<LearningCourse> argusCourses = getArgusCourses();
            if (argusCourses.isEmpty()) {
                System.out.println("No courses found.");
            } else {
                for (int i = 0; i < argusCourses.size(); i++) {
                    System.out.println((i + 1) + ". " + argusCourses.get(i));
                    System.out.println();
                }
            }
        } else {
            System.out.println("Learning Courses for " + student.getName() + " " + student.getSurname() + ":");
            List<LearningCourse> courses = student.getLearningCourses();
            if (courses.isEmpty()) {
                System.out.println("No courses enrolled.");
            } else {
                for (int i = 0; i < courses.size(); i++) {
                    System.out.println((i + 1) + ". " + courses.get(i));
                    System.out.println();
                }
            }
        }
        System.out.println("========================================");
    }

    // Helper method to check if student is yourself
    private boolean isMyself(Student student) {
        return student != null &&
                "paata".equalsIgnoreCase(student.getName()) &&
                "shvelidze".equalsIgnoreCase(student.getSurname()) &&
                "georgia".equalsIgnoreCase(student.getCountry());
    }

    // Method to return Argus courses
    private List<LearningCourse> getArgusCourses() {
        List<LearningCourse> argusCourses = new ArrayList<>();

        argusCourses.add(new LearningCourse(
                "Object Oriented Programming",
                " cs 50 Introduction to Programming",
                "Java syntax and data structures, Procedural programming, Classes, Polymorphism, Encapsulation, inheritance,Packages,Working with the network,Work with files, Working with text data, Work with the terminal"
        ));

        argusCourses.add(new LearningCourse(
                "English Language Course C1-2",
                "",
                ""
        ));
        argusCourses.add(new LearningCourse(
                "Computer Organisation",
                " cs 50 Introduction to Computer Science",
                ", Representing and manipulating information, Machine-level representations of programs, Optimizing program performance,   The memory hierarchy"
        ));

        argusCourses.add(new LearningCourse(
                "Mathematical Foundation of Computing",
                "MATH 150 Calculus I, CS50 Introduction to Programming",
                "Mathematical Logic, Elements of Discrete Mathematics, Elements of Sets Theory, Elements of Graph Theory, Elements of Combinatorics,Elements of Digital Systems"
        ));

        argusCourses.add(new LearningCourse(
                "Calculus II",
                "MATH150 Calculus I",
                "Antiderivatives, Definite integrals,  Techniques and applications of integration, Improper integrals, infinite series "
        ));

        return argusCourses;
    }

    // Method to print all students
    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the university.");
            return;
        }

        System.out.println("All Students in UMS:");
        for (Student student : students) {
            System.out.println("- " + student);
        }
    }

    // Test method
    public static void main(String[] args) {
        UMS ums = new UMS();

        // Create students
        Student student1 = new Student("paata", "shvelidze", "georgia", "Computer Science major");
        Student student2 = new Student("Alice", "Smith", "USA", "Physics major");
        Student student3 = new Student("Bob", "Johnson", "Canada", "Mathematics major");

        // Add some courses to regular students
        student2.addLearningCourse(new LearningCourse(
                "Physics I",
                "High school physics, Calculus I",
                "Mechanics, Thermodynamics, Wave motion"
        ));

        student3.addLearningCourse(new LearningCourse(
                "Pure Mathematics",
                "Mathematical analysis, Abstract algebra basics",
                "Real analysis, Group theory, Ring theory"
        ));

        // Add students to UMS
        ums.addStudent(student1);
        ums.addStudent(student2);
        ums.addStudent(student3);

        // Test printStudentData method
        ums.printStudentData(student1); // Should show Argus courses
        ums.printStudentData(student2); // Should show regular courses
        ums.printStudentData(student3); // Should show regular courses
    }
}