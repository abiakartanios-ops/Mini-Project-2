package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static final List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("admin", "123456"));
        userList.add(new User("Ralph", "123456"));
        userList.add(new User("hussein","123456"));
    }

    public static boolean validate(String username, String password) {
        for (User u : userList) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}