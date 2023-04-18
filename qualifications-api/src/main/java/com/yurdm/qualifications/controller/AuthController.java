package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.users.dto.AuthDTO;
import com.yurdm.qualifications.model.users.dto.RegisterDTO;
import com.yurdm.qualifications.service.AuthService;
import com.yurdm.qualifications.util.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDTO formData) {
        return authService.register(formData);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthDTO formData) {
        return authService.authenticate(formData);
    }
}
