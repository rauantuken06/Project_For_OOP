package kbtu.university.model;

import kbtu.university.enums.DegreeLevel;
import kbtu.university.enums.School;
import kbtu.university.enums.UserRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student extends User implements Comparable<Student> {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private School school;
    private DegreeLevel degreeLevel;
    private int yearOfStudy;
    private double gpa;
    private int totalCredits;
    private int failedCoursesCount;

    private final List<Course> courses;
    private Researcher researchSupervisor;

    public Student() {
        super();
        this.role = UserRole.STUDENT;
        this.degreeLevel = DegreeLevel.BACHELOR;
        this.courses = new ArrayList<>();
    }

    public Student(String id, String login, String password, String fullName, String email,
                   String studentId, School school, DegreeLevel degreeLevel, int yearOfStudy) {
        super(id, login, password, fullName, email, UserRole.STUDENT);
        this.studentId = studentId;
        this.school = school;
        this.degreeLevel = degreeLevel;
        setYearOfStudy(yearOfStudy);
        this.gpa = 0.0;
        this.totalCredits = 0;
        this.failedCoursesCount = 0;
        this.courses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (courses.contains(course)) {
            throw new IllegalArgumentException("Student is already registered for this course.");
        }

        if (this.totalCredits + course.getCredits() > 21) {
            throw new IllegalArgumentException("Student cannot register for more than 21 credits.");
        }

        courses.add(course);
        totalCredits += course.getCredits();
    }

    public void dropCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (courses.remove(course)) {
            totalCredits -= course.getCredits();
        }
    }

    public void addFailedCourse() {
        failedCoursesCount++;

        if (failedCoursesCount > 3) {
            System.out.println("Warning: student has failed more than 3 times.");
        }
    }

    public boolean canContinueStudy() {
        return failedCoursesCount <= 3;
    }

    public String getTranscript() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(fullName).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("GPA: ").append(gpa).append("\n");
        sb.append("Courses:\n");

        for (Course course : courses) {
            sb.append("- ").append(course.getCode())
                    .append(" ")
                    .append(course.getName())
                    .append(" | credits: ")
                    .append(course.getCredits())
                    .append("\n");
        }

        return sb.toString();
    }

    public void rateTeacher(Teacher teacher, int rating) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be from 1 to 5.");
        }

        teacher.addRating(rating);
    }

    @Override
    public String getUserInfo() {
        return "Student: " + fullName +
                ", studentId: " + studentId +
                ", degree: " + degreeLevel +
                ", year: " + yearOfStudy +
                ", GPA: " + gpa;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.gpa, this.gpa);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be null.");
        }
        this.school = school;
    }

    public DegreeLevel getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(DegreeLevel degreeLevel) {
        if (degreeLevel == null) {
            throw new IllegalArgumentException("Degree level cannot be null.");
        }
        this.degreeLevel = degreeLevel;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < 1 || yearOfStudy > 4) {
            throw new IllegalArgumentException("Bachelor student's year must be from 1 to 4.");
        }
        this.yearOfStudy = yearOfStudy;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.gpa = gpa;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public int getFailedCoursesCount() {
        return failedCoursesCount;
    }

    public void setFailedCoursesCount(int failedCoursesCount) {
        if (failedCoursesCount < 0) {
            throw new IllegalArgumentException("Failed courses count cannot be negative.");
        }
        this.failedCoursesCount = failedCoursesCount;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public Researcher getResearchSupervisor() {
        return researchSupervisor;
    }

    public void setResearchSupervisor(Researcher researchSupervisor) {
        if (yearOfStudy != 4) {
            throw new IllegalArgumentException("Only 4th year students can have a research supervisor.");
        }

        if (researchSupervisor == null) {
            throw new IllegalArgumentException("Research supervisor cannot be null.");
        }

        if (researchSupervisor.getHIndex() < 3) {
            throw new IllegalArgumentException("Research supervisor must have h-index at least 3.");
        }

        this.researchSupervisor = researchSupervisor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                ", degreeLevel=" + degreeLevel +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", totalCredits=" + totalCredits +
                '}';
    }
}
