package com.webtracker.service;

import java.time.LocalDateTime;
import java.util.List;

import com.webtracker.entity.Location;
import com.webtracker.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtracker.entity.User;
import com.webtracker.repository.UserRepository;

@Service
public class UserService {
	
	  @Autowired
	    private UserRepository userRepository;

	@Autowired(required = true)
	private LocationRepository locationRepository;

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

	public User login(String username, String password, Double latitude, Double longitude) {
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			saveLocation(user, latitude, longitude);
			return user;
		}
		throw new RuntimeException("Invalid login");
	}

	public void logout(User user, Double latitude, Double longitude) {
		saveLocation(user, latitude, longitude);
	}

	private void saveLocation(User user, Double latitude, Double longitude) {
		Location location = new Location();
		location.setUser(user);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setTimestamp(LocalDateTime.now());
		locationRepository.save(location);
	}
}
