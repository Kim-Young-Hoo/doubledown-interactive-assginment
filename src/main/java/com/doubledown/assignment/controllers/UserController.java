package com.doubledown.assignment.controllers;
import com.doubledown.assignment.models.Keyword;
import com.doubledown.assignment.models.NewsMedia;
import com.doubledown.assignment.models.NewsMediaType;
import com.doubledown.assignment.models.User;
import com.doubledown.assignment.repositories.KeywordRepository;
import com.doubledown.assignment.repositories.NewsMediaRepository;
import com.doubledown.assignment.repositories.UserRepository;
import com.doubledown.assignment.requests.LoginRequest;
import com.doubledown.assignment.requests.NewsMediaRequest;
import com.doubledown.assignment.requests.SignupRequest;
import com.doubledown.assignment.responses.JwtResponse;
import com.doubledown.assignment.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsMediaRepository newsMediaRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
