package kbtu.university.service;

import kbtu.university.exceptions.AuthenticationException;
import kbtu.university.model.User;

public class AuthService {

    public User login(User user, String login, String password) throws AuthenticationException {
        if (!user.getLogin().equals(login) || !user.getPassword().equals(password)) {
            throw new AuthenticationException("Wrong login or password");
        }
        return user;
    }
}