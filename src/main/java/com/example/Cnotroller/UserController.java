package com.example.Cnotroller;

import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<String> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/info")
    public String getUserInfo() {
        return userService.getUserInfo();
    }
}
