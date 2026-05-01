package kbtu.university.model;

import kbtu.university.enums.School;
import kbtu.university.enums.TeacherTitle;
import kbtu.university.enums.UserRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teacher extends Employee {
    private static final long serialVersionUID = 1L;

    private TeacherTitle title;
    private final List<Course> courses;
    private final List<Integer> ratings;

    private ResearchProfile researchProfile;

    public Teacher() {
        super();
        this.role = UserRole.TEACHER;
        this.courses = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public Teacher(String id, String login, String password, String fullName, String email,
                   String employeeId, School school, double salary, TeacherTitle title) {
        super(id, login, password, fullName, email, employeeId, school, salary);
        this.role = UserRole.TEACHER;
        this.title = title;
        this.courses = new ArrayList<>();
        this.ratings = new ArrayList<>();

        if (title == TeacherTitle.PROFESSOR) {
            this.researchProfile = new ResearchProfile(3);
        }
    }

    public void assignCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void putMark(Student student, Course course, Mark mark) {
        if (student == null || course == null || mark == null) {
            throw new IllegalArgumentException("Student, course and mark cannot be null.");
        }

        if (!courses.contains(course)) {
            throw new IllegalArgumentException("Teacher is not assigned to this course.");
        }

        System.out.println("Teacher " + fullName + " put mark " + mark.getTotal() +
                " to student " + student.getFullName() +
                " for course " + course.getName());
    }

    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be from 1 to 5.");
        }

        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }

        return (double) sum / ratings.size();
    }

    public boolean isProfessor() {
        return title == TeacherTitle.PROFESSOR;
    }

    public boolean isResearcher() {
        return researchProfile != null;
    }

    public void makeResearcher(int hIndex) {
        if (hIndex < 0) {
            throw new IllegalArgumentException("H-index cannot be negative.");
        }

        this.researchProfile = new ResearchProfile(hIndex);
    }

    @Override
    public String getUserInfo() {
        return "Teacher: " + fullName +
                ", title: " + title +
                ", school: " + school +
                ", researcher: " + isResearcher();
    }

    public TeacherTitle getTitle() {
        return title;
    }

    public void setTitle(TeacherTitle title) {
        if (title == null) {
            throw new IllegalArgumentException("Teacher title cannot be null.");
        }

        this.title = title;

        if (title == TeacherTitle.PROFESSOR && researchProfile == null) {
            this.researchProfile = new ResearchProfile(3);
        }
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public List<Integer> getRatings() {
        return Collections.unmodifiableList(ratings);
    }

    public ResearchProfile getResearchProfile() {
        return researchProfile;
    }

    public void setResearchProfile(ResearchProfile researchProfile) {
        this.researchProfile = researchProfile;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                ", title=" + title +
                ", averageRating=" + getAverageRating() +
                ", researcher=" + isResearcher() +
                '}';
    }
}
