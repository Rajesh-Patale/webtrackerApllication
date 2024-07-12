package com.webtracker.service;

import com.webtracker.entity.Role;
import com.webtracker.entity.User;
import com.webtracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Pattern SAFE_TEXT_PATTERN = Pattern.compile("^[a-zA-Z0-9._\\-@\\s]*$");
//    private static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public User findByEmail(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        validateUserData(user);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully: {}", savedUser.getUsername());
        return savedUser;
    }

    public User saveAdmin(User user) {
        try {
            validateUserData(user);
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            logger.info("Admin User saved successfully: {}", savedUser.getUsername());
            return savedUser;
        } catch (ValidationException e) {
            logger.error("Validation error : {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while saving the admin user: ", e);
            throw new RuntimeException("An error occurred while saving the admin user.");
        }
    }

    public User fetchUserByEmailAndPassword(String email, String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User saveLogOutTime(User user) {
        user.setLogouttime(user.getLogouttime());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("User not found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    private void validateUserData(@NotNull User userData) {
        if (userData.getName() == null || userData.getName().isEmpty() || !SAFE_TEXT_PATTERN.matcher(userData.getName()).matches()) {
            throw new ValidationException("Invalid name.");
        }

        if (userData.getUsername() == null || userData.getUsername().isEmpty() || !SAFE_TEXT_PATTERN.matcher(userData.getUsername()).matches()) {
            throw new ValidationException("Invalid username.");
        }

        if (userData.getEmail() == null || userData.getEmail().isEmpty() || !EMAIL_PATTERN.matcher(userData.getEmail()).matches()) {
            throw new ValidationException("Invalid email.");
        }

        if (userData.getPassword() == null || userData.getPassword().isEmpty() || userData.getPassword().length() < 8) {
            throw new ValidationException("Invalid password. Password must be at least 8 characters long.");
        }
    }
}
