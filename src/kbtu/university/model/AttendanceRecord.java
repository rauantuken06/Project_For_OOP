package kbtu.university.model;

import kbtu.university.enums.AttendanceStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AttendanceRecord implements Serializable {
    private static final long serialVersionUID = 1;

    private Student student;
    private Course course;
    private Lesson lesson;
    private LocalDate date;
    private AttendanceStatus status;

    public AttendanceRecord(Student student, Course course, Lesson lesson, LocalDate date, AttendanceStatus status) {
        setStudent(student);
        setCourse(course);
        setLesson(lesson);
        setDate(date);
        setStatus(status);
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.course = course;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        this.lesson = lesson;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof AttendanceRecord that)) return false;
        return Objects.equals(student, that.student) &&
                Objects.equals(course, that.course) &&
                Objects.equals(lesson, that.lesson) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, lesson, date);
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "student=" + student.getFullName() +
                ", course=" + course.getCode() +
                ", lesson=" + lesson.getType() +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
