package com.example.cardealershipclone1;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static final List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("tanios", "tanios123"));  // Tanios's account
        userList.add(new User("ralph", "123456"));       // Ralph's account
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