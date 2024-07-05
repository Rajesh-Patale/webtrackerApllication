package com.webtracker.controller;

import com.webtracker.entity.User;
import com.webtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User userOpt = userService.findByEmail(email);
        if (userOpt!=null) {
            if (userOpt.getPassword().equals(password)) {
                return "Login successful as ";
            }
        }
        return "Invalid email or password";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
