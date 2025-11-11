package com.example.Repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<String> users = new ArrayList<>();

    public UserRepository() {
        users.add("John Doe");
        users.add("Jane Smith");
        users.add("Bob Johnson");
    }

    public List<String> findAllUsers() {
        return new ArrayList<>(users);
    }

    public String findUserById(int id) {
        if (id >= 0 && id < users.size()) {
            return users.get(id);
        }
        return "User not found";
    }

    public int getUserCount() {
        return users.size();
    }
}
