package com.example.api_users.controller.Imp;

import com.example.api_users.models.UserModel;
import com.example.api_users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<UserModel> getUserById(@RequestHeader("userIdRequest") String userId) {
        System.out.println("userIdRequest: " + userId);
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    @GetMapping("/users/all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
