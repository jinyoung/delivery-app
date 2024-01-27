package com.example.usermanagement.controller;

import com.example.usermanagement.domain.User;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.service.command.RegisterUserCommand;
import com.example.usermanagement.service.command.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserCommand command) {
        User user = userService.registerUser(command);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody UpdateUserCommand command) {
        command.setUserId(userId);
        User user = userService.updateUser(command);
        return ResponseEntity.ok(user);
    }
}