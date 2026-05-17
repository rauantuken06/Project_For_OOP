package kbtu.university.model;

import java.io.Serializable;
import java.time.LocalDate;


public class RecommendationLetter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Teacher teacher;
    private String text;
    private LocalDate createdDate;

    public RecommendationLetter(Student student, Teacher teacher, String text) {
        setStudent(student);
        setTeacher(teacher);
        setText(text);
        this.createdDate = LocalDate.now();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }
        this.teacher = teacher;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void printLetter() {
        System.out.println("Recommendation Letter");
        System.out.println("Date " + createdDate);
        System.out.println("Student " + student.getFullName());
        System.out.println("Teacher " + teacher.getFullName());
        System.out.println();
        System.out.println(text);
    }

    @Override
    public String toString() {
        return "RecommendationLetter{" +
                "student=" + student.getFullName() +
                ", teacher=" + teacher.getFullName() +
                ", createdDate=" + createdDate +
                '}';
    }
}
