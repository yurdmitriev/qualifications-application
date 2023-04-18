package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.users.User;
import com.yurdm.qualifications.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository repository;

    @Autowired
    public UserService(UsersRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User createUser(User user) {
        Optional<User> existing = repository.findByEmail(user.getEmail());

        if (existing.isPresent()) {
            throw new RuntimeException("User already exists");
//            TODO: throw exception
        } else {
            return repository.save(user);
        }
    }
}
