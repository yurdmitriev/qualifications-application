package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.users.Student;
import com.yurdm.qualifications.model.users.User;
import com.yurdm.qualifications.security.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user")
    public User getUserInfo() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
