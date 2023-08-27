package com.example.auth.features.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;


    @GetMapping
    public ResponseEntity getAll(){
        var allUsers = repository.findAll();

        return ResponseEntity.ok(allUsers);
    }
}
