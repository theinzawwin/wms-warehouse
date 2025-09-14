package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreateUserInput;
import com.pearl.warehouse.model.User;
import com.pearl.warehouse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserInput request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}