package kbtu.university.model;

import kbtu.university.enums.School;
import kbtu.university.enums.UserRole;

public class Employee extends User {
    private static final long serialVersionUID = 1L;

    protected String employeeId;
    protected School school;
    protected double salary;

    public Employee() {
        super();
        this.role = UserRole.EMPLOYEE;
    }

    public Employee(String id, String login, String password, String fullName, String email,
                    String employeeId, School school, double salary) {
        super(id, login, password, fullName, email, UserRole.EMPLOYEE);
        this.employeeId = employeeId;
        this.school = school;
        setSalary(salary);
    }

    public void sendMessage(Employee receiver, String message) {
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver cannot be null");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }

        System.out.println("Message from " + this.fullName + " to " + receiver.getFullName() + ": " + message);
    }

    public void sendComplaint(String complainText) {
        if (complainText == null || complainText.isBlank()) {
            throw new IllegalArgumentException("Complaint cannot be empty.");
        }

        System.out.println("Complaint from" + this.fullName + ": " + complainText);
    }

    @Override
    public String getUserInfo() {
        return "Employee: " + fullName + ", school: " + school + ", employeeId: " + employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        }
        this.employeeId = employeeId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be empty.");
        }
        this.school = school;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                ", salary=" + salary +
                '}';

    }
}
