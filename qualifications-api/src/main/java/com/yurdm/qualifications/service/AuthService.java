package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.users.*;
import com.yurdm.qualifications.model.users.dto.AuthDTO;
import com.yurdm.qualifications.model.users.dto.RegisterDTO;
import com.yurdm.qualifications.model.users.exception.PasswordsMatchException;
import com.yurdm.qualifications.model.users.exception.UserNotFoundException;
import com.yurdm.qualifications.repository.UsersRepository;
import com.yurdm.qualifications.security.SecurityRole;
import com.yurdm.qualifications.util.AuthResponse;
import com.yurdm.qualifications.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private final CompanyService companyService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsersRepository repository;

    @Autowired
    public AuthService(CompanyService companyService, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UsersRepository repository) {
        this.companyService = companyService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    public ResponseEntity<AuthResponse> register(RegisterDTO dto) {
        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            throw new PasswordsMatchException();
        }

//        TODO: make user validation

        User user;
        if (dto.getRole().equals(SecurityRole.COMPANY)) {
            user = new Employee();
            Company company = companyService.createCompany(dto.getCompany());
            ((Employee) user).setCompany(company);
        } else if (dto.getRole().equals(SecurityRole.ADMIN)) {
            // TODO: deny registration
            user = new Admin();
        } else if (dto.getRole().equals(SecurityRole.LECTURER)) {
            user = new Lecturer();
        } else {
            user = new Student();
        }

        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setPatronymic(dto.getPatronymic());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user = userService.createUser(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
        Date expires = jwtUtil.getExpireDate(token);

        return ResponseEntity.ok(new AuthResponse(token, expires.getTime()));
    }

    public ResponseEntity<AuthResponse> authenticate(AuthDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        var user = repository.findByEmail(dto.getEmail()).orElseThrow(UserNotFoundException::new);
        String token = jwtUtil.generateToken(user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
        Date expires = jwtUtil.getExpireDate(token);
        return ResponseEntity.ok(new AuthResponse(token, expires.getTime()));
    }
}
