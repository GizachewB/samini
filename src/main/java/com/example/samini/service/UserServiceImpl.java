package com.example.samini.service;

import com.example.samini.domain.User;
import com.example.samini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Override
    public User registerNewUserAccount(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // encrypt
        return repository.save(newUser);
    }

}
