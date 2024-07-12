package com.webtracker.controller;


import com.webtracker.entity.Location;
import com.webtracker.entity.User;
import com.webtracker.service.LocationService;
import com.webtracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{
        if (!isValidEmail(user.getEmail())) {
            return new ResponseEntity<>("enter valid userId", HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() != null || !"".equals(user.getEmail())) {
             User userObj = userService.findByEmail(user.getEmail());
            if (userObj!=null) {
                throw new Exception("User with " + user.getEmail() + " already exists !!!");
            }
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/user/addAdmin")
    public ResponseEntity<User> addAdmin(@RequestBody User userDetails) {
        logger.info("Adding admin user with details: {}", userDetails);
        try {
            // Save the admin details
            User savedAdmin = userService.saveAdmin(userDetails);
            return ResponseEntity.ok(savedAdmin);
        } catch (Exception e) {
            logger.error("Error adding admin user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> searchUser(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/getAllUser")
    public ResponseEntity<List<User>> searchAllUser() throws Exception
    {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("/user/loginUser")
    public User loginUser(@RequestBody User user) throws Exception
    {
        String currEmail = user.getEmail();

        String currPassword = user.getPassword();

        User userObj = null;
        if(currEmail != null && currPassword != null)
        {
            userObj = userService.fetchUserByEmailAndPassword(currEmail, currPassword);
            userObj.setTimestamp(user.getTimestamp());
            userObj.setLocation(user.getLocation());
            userService.saveUser(userObj);

        }
        if(userObj == null)
        {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }
        return userObj;
    }

    @PostMapping("/user/saveLocation")
    public String saveLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return "Location saved successfully!";
    }

    @GetMapping("/user/getLocation/{userEmail}")
    public Location getLocation(@PathVariable String userEmail){
        System.out.println(locationService.getLocation(userEmail));
        return locationService.getLocation(userEmail);
    }

    @PostMapping("/user/logoutUser")
    public User logOutUser(@RequestBody User user) throws Exception
    {
         User usr=userService.findByEmail(user.getEmail());
        if (usr !=null)
        {
            usr.setLogouttime(user.getLogouttime());
        }
        userService.saveUser(usr);
        return usr;
    }

}
