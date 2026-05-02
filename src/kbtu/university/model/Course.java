package kbtu.university.model;

import kbtu.university.enums.CourseType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Course implements Serializable, Comparable<Course>, Cloneable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String code;
    private String name;
    private int credits;
    private CourseType type;
    private String targetMajor;
    private List<Teacher> instructors;
    private List<Student> students;
    private List<Lesson> lessons;

    public Course() {
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Course(int id, String code, String name, int credits, CourseType type, String targetMajor) {
        this();
        setId(id);
        setCode(code);
        setName(name);
        setCredits(credits);
        setType(type);
        setTargetMajor(targetMajor);
    }

    public Course(String code, String name, int credits, CourseType type, String targetMajor) {
        this(0, code, name, credits, type, targetMajor);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        students.remove(student);
    }

    public void addInstructor(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }
        if (!instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    public void removeInstructor(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }
        instructors.remove(teacher);
    }

    public void addLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        if (!lessons.contains(lesson)) {
            lessons.add(lesson);
        }
    }

    public void removeLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        lessons.remove(lesson);
    }

    public boolean hasInstructor(Teacher teacher) {
        return instructors.contains(teacher);
    }

    public boolean hasStudent(Student student) {
        return students.contains(student);
    }

    @Override
    public int compareTo(Course other) {
        if (other == null) {
            return 1;
        }
        return this.code.compareToIgnoreCase(other.code);
    }

    @Override
    public Course clone() {
        try {
            Course copy = (Course) super.clone();
            copy.instructors = new ArrayList<>(this.instructors);
            copy.students = new ArrayList<>(this.students);
            copy.lessons = new ArrayList<>(this.lessons);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Course id cannot be negative.");
        }
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Course code cannot be empty.");
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits <= 0 || credits > 10) {
            throw new IllegalArgumentException("Course credits must be from 1 to 10.");
        }
        this.credits = credits;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        if (type == null) {
            throw new IllegalArgumentException("Course type cannot be null.");
        }
        this.type = type;
    }

    public String getTargetMajor() {
        return targetMajor;
    }

    public void setTargetMajor(String targetMajor) {
        if (targetMajor == null || targetMajor.isBlank()) {
            throw new IllegalArgumentException("Target major cannot be empty.");
        }
        this.targetMajor = targetMajor;
    }

    public List<Teacher> getInstructors() {
        return Collections.unmodifiableList(instructors);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", type=" + type +
                ", targetMajor='" + targetMajor + '\'' +
                ", instructorsCount=" + instructors.size() +
                ", studentsCount=" + students.size() +
                ", lessonsCount=" + lessons.size() +
                '}';
    }
}
