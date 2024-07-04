package com.webtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webtracker.entity.User;
import com.webtracker.service.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    @GetMapping
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @GetMapping("/{id}")
	    public User getUserById(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }

	    @PostMapping
	    public User createUser(@RequestBody User user) {
	        return userService.createUser(user);
	    }

	    @PutMapping("/{id}")
	    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
	        return userService.updateUser(id, userDetails);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	    }

	@GetMapping("/login")
	public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password, @RequestParam Double latitude, @RequestParam Double longitude) {
		User user = userService.login(username, password, latitude, longitude);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam Long userId, @RequestParam Double latitude, @RequestParam Double longitude) {
		User user = userService.getUserById(userId);
		userService.logout(user, latitude, longitude);
		return ResponseEntity.ok("Logged out successfully");
	}
}
