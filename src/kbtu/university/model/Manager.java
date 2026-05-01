package kbtu.university.model;

import kbtu.university.enums.ManagerType;
import kbtu.university.enums.School;
import kbtu.university.enums.UserRole;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manager extends Employee {
    private static final long serialVersionUID = 1L;

    private ManagerType managerType;

    public Manager() {
        super();
        this.role = UserRole.MANAGER;
    }

    public Manager(String id, String login, String password, String fullName, String email, String employeeId,
                   School school, double salary, ManagerType managerType) {
        super(id, login, password, fullName, email, employeeId, school, salary);
        this.role = UserRole.MANAGER;
        this.managerType = managerType;
    }

    public void approveStudentRegistration(Student student, Course course) {
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student and course cannot be null.");
        }

        System.out.println("Manager " + fullName + " approved registration of " +
                student.getFullName() + " for course " + course.getName());
    }

    public void assignCourseToTeacher(Course course, Teacher teacher) {
        if (course == null || teacher == null) {
            throw new IllegalArgumentException("Course and teacher cannot be null.");
        }

        course.addInstructor(teacher);
        teacher.assignCourse(course);
    }

    public void addCourseForRegistration(List<Course> availableCourses, Course course) {
        if (availableCourses == null) {
            throw new IllegalArgumentException("Available courses list cannot be null.");
        }

        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if(!availableCourses.contains(course)) {
            availableCourses.add(course);
        }
    }

    public List<Student> getStudentSortedAlphabetically(List<Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null.");
        }

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(Comparator.comparing(Student::getFullName));
        return sorted;
    }

    public List<Teacher> getTeacherSortedAlphabetically(List<Teacher> teachers) {
        if (teachers == null) {
            throw new IllegalArgumentException("Teachers list cannot be null.");
        }

        List<Teacher> sorted = new ArrayList<>(teachers);
        sorted.sort(Comparator.comparing(Teacher::getFullName));
        return sorted;
    }

    public void createAcademicPerformanceReport(List<Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null.");
        }

        if (students.isEmpty()) {
            System.out.println("No students for report.");
            return;
        }

        double sum = 0.0;

        for (Student student : students) {
            sum += student.getGpa();
        }

        double averageGpa = sum / students.size();

        System.out.println("Academic Performance Report.");
        System.out.println("Students count: " + students.size());
        System.out.println("Average GPA: " + averageGpa);
    }

    public void manageNews(String newsText) {
        if (newsText == null || newsText.isBlank()) {
            throw new IllegalArgumentException("News text cannot be empty.");
        }

        System.out.println("Manager " + fullName + " published news: " + newsText);
    }

    @Override
    public String getUserInfo() {
        return "Manager: " + fullName +
                ", type: " + managerType +
                ", school: " + school;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        if (managerType == null) {
            throw new IllegalArgumentException("Manager type cannot be null.");
        }
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                ", managerType=" + managerType +
                '}';
    }
}
