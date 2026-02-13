package com.example.UserRegistrationApi.controller;

import com.example.UserRegistrationApi.entity.User;
import com.example.UserRegistrationApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

     @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user)
     {
         User saveUser=userService.registerUser(user);
         return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
     }


     @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
     {
         return ResponseEntity.ok(userService.getAllUsers());
     }

     @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
     {
         return ResponseEntity.ok(userService.getUserById(id));
     }

     @PutMapping("/{id}")
     public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User user)
     {
return ResponseEntity.ok(userService.updateUser(id,user));
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
     {
         userService.deleteUser(id);
         return ResponseEntity.ok("User delete successfully");

     }
}
