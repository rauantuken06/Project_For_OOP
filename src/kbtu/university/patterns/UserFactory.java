package kbtu.university.patterns;

import kbtu.university.model.*;

public class UserFactory {

    public static User createUser(String role) {
        if (role.equalsIgnoreCase("student")) {
            return new Student();
        }
        if (role.equalsIgnoreCase("teacher")) {
            return new Teacher();
        }
        if (role.equalsIgnoreCase("admin")) {
            return new Admin();
        }
        if (role.equalsIgnoreCase("manager")) {
            return new Manager();
        }
        return null;
    }
}