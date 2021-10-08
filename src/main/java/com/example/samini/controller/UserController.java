package com.example.samini.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
