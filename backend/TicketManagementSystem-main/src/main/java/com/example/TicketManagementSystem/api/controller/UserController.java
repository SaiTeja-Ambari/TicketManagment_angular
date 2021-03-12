package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.EnUserType;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import com.example.TicketManagementSystem.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        List<User> users= userRepository.findAll();
        for (User u: users) {
            if (u.getEmail().equals(user.getEmail())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return new ResponseEntity<User>(u,HttpStatus.OK);
                } else {
                    return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
                }
            }
        }
        return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/member")
    public List<User> getAllMembers() {
        List<User> users = userRepository.findAll();
        List<User> members = new ArrayList<>();
        for(User user: users) {
            if (user.getType().equals(EnUserType.MEMBER)) {
                members.add(user);
            }
        }
        return members;
    }
}
