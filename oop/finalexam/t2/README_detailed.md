# University Management System (UMS)

## 📚 Description

This Java-based University Management System (UMS) is designed as part of the `oop.finalexam.t2` package. The program models a small university environment where you can manage student information and their associated learning courses. 

The system supports adding and removing students, enrolling them in specific courses, and printing detailed student information. It includes special logic to return **predefined Argus course data** for the student **Paata Shvelidze from Georgia**.

---

## 📦 Package Structure

All classes must belong to the following package:

```
package oop.finalexam.t2;
```

Folder structure:

```
src/
└── oop/
    └── finalexam/
        └── t2/
            ├── UMS.java
            ├── Student.java
            └── LearningCourse.java
```

Each class file is responsible for a different component of the system and should be placed in its proper directory as shown.

---

## ✅ Features

- 📋 **Add Students**: Ability to add new students to the system.
- 📘 **Enroll in Courses**: Assign learning courses to each student.
- 🧾 **Print Student Details**: Detailed output of student information and their courses.
- 👤 **Self-Recognition**: Recognizes if the student is "Paata Shvelidze from Georgia" and displays **Argus course list**.
- 🔐 **Thread-Safe Operations**: The student list is synchronized for safe concurrent access.

---

## 🧑‍🎓 Class Overview

### 🏫 `LearningCourse`
A class representing a university learning course. It contains:
- `title`: Name of the course
- `acceptancePrerequisites`: Required knowledge or courses to take it
- `majorTopics`: Core topics covered in the course

Includes full getter and setter methods, and a `toString()` for formatted output.

### 🧍 `Student`
A class to represent each student, containing:
- `name`, `surname`, `country`, `info`: Student details
- `learningCourses`: A list of `LearningCourse` instances

Also includes:
- `equals()` and `hashCode()` for uniqueness
- Method to add a course
- Method to set full list of courses

### 🧠 `UMS` (University Management System)
Main controller class for managing students. Includes:
- `addStudent(Student)` to register a new student
- `removeStudent(Student)` to remove an existing student
- `getStudents()` to fetch all students
- `printStudentData(Student)` to display full data for a student
- `printAllStudents()` to list all students

Contains internal logic to check for Paata Shvelidze and display **Argus-specific courses**.

---

## 💡 Sample Output

Here’s what the output looks like for "Paata Shvelidze":

```
========================================
Student Information:
Name: paata
Surname: shvelidze
Country: georgia
Info: Computer Science major
========================================
Learning Courses for paata shvelidze (Your Argus Courses):
1. Course: Object Oriented Programming
   Prerequisites: cs 50 Introduction to Programming
   Major Topics: Java syntax and data structures, Procedural programming, Classes, Polymorphism, Encapsulation, inheritance, Packages, Working with the network, Work with files, Working with text data, Work with the terminal

...
========================================
```

For other students, their personally assigned courses will be printed.

---

## 🧩 UML Diagram

Basic class relationship diagram:

```
+---------------------+
|        UMS          |
+---------------------+
| - students: List    |
+---------------------+
| + addStudent()      |
| + removeStudent()   |
| + printStudentData()|
| + printAllStudents()|
+---------------------+
           |
           v
+---------------------+
|      Student         |
+---------------------+
| - name               |
| - surname            |
| - country            |
| - info               |
| - learningCourses    |
+---------------------+
| + addLearningCourse()|
| + getLearningCourses()|
| + equals(), hashCode()|
+---------------------+
           |
           v
+-----------------------------+
|      LearningCourse         |
+-----------------------------+
| - title                     |
| - acceptancePrerequisites   |
| - majorTopics               |
+-----------------------------+
```
(![Screenshot 2025-07-03 114221.png](Screenshot%202025-07-03%20114221.png))

📝 Tip: Create this diagram using Google Docs or draw.io and link or embed it in your repository.

---

## 📄 Javadoc

To generate the official Java documentation:

```bash
cd src
javadoc -d doc -private oop/finalexam/t2/*.java
```

This command will generate HTML documentation in the `doc/` directory including all class and method descriptions.

---

## 📦 Submission Guidelines

✅ Make sure your submission includes:
- Java source files inside the correct `oop/finalexam/t2/` folder
- This `README.md` file
- The UML diagram (either as image or shared Google Docs link)
- Optional: Javadoc in the `/doc` folder

🚫 Do **not** include:
- `.class` files
- `.idea/`, `.vscode/`, `target/`, `bin/` folders
- `.DS_Store` or any OS/IDE-generated files

---

## 👤 Author

**Paata Shvelidze**  
Final Task Submission – OOP, Summer 2025