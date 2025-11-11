package com.example.Service;

import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public String getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public String getUserInfo() {
        int count = userRepository.getUserCount();
        List<String> users = userRepository.findAllUsers();
        return String.format("Total users: %d. Users: %s", count, String.join(", ", users));
    }
}
