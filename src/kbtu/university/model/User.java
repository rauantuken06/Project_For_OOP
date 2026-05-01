package kbtu.university.model;

import kbtu.university.enums.UserRole;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable{
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String login;
    protected String password;
    protected String fullName;
    protected String email;
    protected UserRole role;
    protected boolean active;

    public User() {
        this.active = true;
    }

    public User (String id, String login, String password, String fullName, String email, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.active = true;
    }

    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    public abstract String getUserInfo();

    public void changePassword(String oldPassword, String newPassword) {
        if (!checkPassword(oldPassword)) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        if (newPassword == null || newPassword.length() < 4) {
            throw new IllegalArgumentException("New password must contain at least 4 characters.");
        }

        this.password = newPassword;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public boolean canLogin() {
        return active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be empty.");
        }
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login cannot be empty.");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must contain at least 4 characters.");
        }
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email is invalid.");
        }
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    protected void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }
}
