package org.example.bookstore.controller;

import org.example.bookstore.dto.request.AuthRequest;
import org.example.bookstore.dto.request.CreateUserRequest;
import org.example.bookstore.dto.response.UserResponse;
import org.example.bookstore.entity.User;
import org.example.bookstore.security.AuthenticationService;
import org.example.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;

        this.authenticationService = authenticationService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "hello world! this is dmr";
    }

    @PostMapping("/addNewUser")
    public UserResponse addUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        return authenticationService.generateToken(request);
    }




}