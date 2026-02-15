package com.airline.Airline.controllers;


import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.AuthRequest;
import com.airline.Airline.security.JwtUtil;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;

@RestController
@RequestMapping("/public")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result generateToken(@RequestBody AuthRequest authRequest) {
    	
        try {
            // Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

            // Generate JWT token
            String token = jwtUtil.generateToken(authentication.getName());

            return new Result(true,StatusCode.SUCCESS,"Login Successfull",token);
        } catch (AuthenticationException e) {
            // Authentication failed
            return new Result(false,StatusCode.UNAUTHORIZED,"Invalid username or password");
        }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}










