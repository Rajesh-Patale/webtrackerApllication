package com.webtracker.controller;


import com.webtracker.Util.JwtResponse;
import com.webtracker.Util.RefreshToken;
import com.webtracker.Util.RefreshTokenRequest;
import com.webtracker.entity.User;
import com.webtracker.security.JwtHelper;
import com.webtracker.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtHelper helper;

    final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody User request){
        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .refreshTokenId(refreshToken.getRefreshTokenId())
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try{
            authenticationManager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @PostMapping("/refresh")
    public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request){
        RefreshToken verifyRefreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        User userData = verifyRefreshToken.getUserData();
        String token = this.helper.generateToken(userData);
        return JwtResponse.builder().refreshTokenId(verifyRefreshToken.getRefreshTokenId())
                            .token(token)
                            .username(userData.getUsername())
                            .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Invalid credentials !!!";
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(Principal principal){
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
