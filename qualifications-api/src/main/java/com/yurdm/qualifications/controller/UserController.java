package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.users.Student;
import com.yurdm.qualifications.model.users.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/users/")
public class UserController {
    @GetMapping("test")
    public User getTestUser() {
        var user = new Student();

        user.setId(1);
        user.setFirstName("Петро");
        user.setLastName("Петров");
        user.setEmail("petro@mail.ua");
        user.setCourse(2);
        user.setCompetencies(new ArrayList<>());

        return user;
    }
}
