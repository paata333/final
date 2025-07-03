package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Student class (package-private)
class Student {
    private String name;
    private String surname;
    private String country;
    private String info;
    private List<LearningCourse> learningCourses;

    public Student(String name, String surname, String country, String info) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.info = info;
        this.learningCourses = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getInfo() {
        return info;
    }

    public List<LearningCourse> getLearningCourses() {
        return learningCourses;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLearningCourses(List<LearningCourse> learningCourses) {
        this.learningCourses = learningCourses;
    }

    // Method to add a learning course
    public void addLearningCourse(LearningCourse course) {
        if (course != null && !learningCourses.contains(course)) {
            learningCourses.add(course);
        }
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + country + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(country, student.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, country);
    }
}
