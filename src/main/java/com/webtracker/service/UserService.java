package com.webtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtracker.entity.User;
import com.webtracker.repository.UserRepository;

@Service
public class UserService {
	
	  @Autowired
	    private UserRepository userRepository;

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public User getUserById(Long id) {
	        return userRepository.findById(id).orElse(null);
	    }

	    public User createUser(User user) {
	        return userRepository.save(user);
	    }

	    public User updateUser(Long id, User userDetails) {
	        User user = getUserById(id);
	        if (user != null) {
	            user.setUsername(userDetails.getUsername());
	            user.setPassword(userDetails.getPassword());
	            return userRepository.save(user);
	        }
	        return null;
	    }

	    public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }

}
