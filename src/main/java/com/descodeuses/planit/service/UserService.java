package com.descodeuses.planit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.SignupRequest;
import com.descodeuses.planit.entity.DCUser;
import com.descodeuses.planit.repository.UserRepository;

@Service
public class UserService {

@Autowired
private UserRepository userRepository; 



public void registerUser(SignupRequest request) {
    DCUser user = new DCUser(); 
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setUsername(request.getUsername());
    user.setGenre(request.getGenre());
    user.setPassword(request.getPassword());
    user.setRole("ROLE_USER");

    userRepository.save(user);
}

public List<DCUser> getAllUser() {
    return userRepository.findAll(); 
}

}
