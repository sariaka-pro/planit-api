package com.descodeuses.planit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.service.UserService;
import com.descodeuses.planit.entity.DCUser;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<DCUser> getAllUsers() {
        return userService.getAllUser(); 
    }

    
}
