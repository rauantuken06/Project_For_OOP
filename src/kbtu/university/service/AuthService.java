package kbtu.university.service;

import kbtu.university.exceptions.AuthenticationException;
import kbtu.university.model.User;

import java.util.List;

public class AuthService {
    private List<User> users;

    public AuthService() {
    }

    public AuthService(List<User> users) {
        this.users = users;
    }

    public User login(String login, String password) throws AuthenticationException {
        if (users == null) {
            throw new AuthenticationException("Users list is not initialized.");
        }

        for (User user : users) {
            if (user.getLogin().equals(login) && user.checkPassword(password)) {
                if (!user.canLogin()) {
                    throw new AuthenticationException("User account is blocked.");
                }

                return user;
            }
        }

        throw new AuthenticationException("Invalid login or password.");
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}