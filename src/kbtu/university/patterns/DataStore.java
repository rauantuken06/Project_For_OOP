package kbtu.university.patterns;

import java.util.ArrayList;
import java.util.List;
import kbtu.university.model.User;

public class DataStore {

    private static DataStore instance;

    private List<User> users = new ArrayList<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void addUser(User u) {
        users.add(u);
    }

    public List<User> getUsers() {
        return users;
    }
}