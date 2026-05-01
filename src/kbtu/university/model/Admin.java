package kbtu.university.model;

import kbtu.university.enums.School;
import kbtu.university.enums.UserRole;

import java.util.List;

public class Admin extends Employee {
    private static final long serialVersionUID = 1L;

    public Admin() {
        super();
        this.role = UserRole.ADMIN;
    }

    public Admin(String id, String login, String password, String fullName, String email,
                 String employeeId, School school, double salary) {
        super(id, login, password, fullName, email, employeeId, school, salary);
        this.role = UserRole.ADMIN;
    }

    public void addUser(List<User> users, User user) {
        if (users == null) {
            throw new IllegalArgumentException("Users list cannot be null.");
        }

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (users.contains(user)) {
            throw new IllegalArgumentException("User already exists.");
        }

        users.add(user);
    }

    public void removeUser(List<User> users, User user) {
        if (users == null) {
            throw new IllegalArgumentException("Users list cannot be null.");
        }

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        users.remove(user);
    }

    public void updateUserEmail(User user, String newEmail) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        user.setEmail(newEmail);
    }

    public void blockUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        user.deactivate();
    }

    public void unblockUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        user.activate();
    }

    public void viewLogFiles() {
        System.out.println("Admin " + fullName + " is viewing system log files.");
    }

    @Override
    public String getUserInfo() {
        return "Admin: " + fullName + ", employeeId: " + employeeId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                '}';
    }
}
