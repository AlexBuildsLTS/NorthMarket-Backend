package com.northmarket.controller;

import com.northmarket.model.User;
import com.northmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/{userId}/rate")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<User> rateUser(@PathVariable Long userId, @RequestParam Double rating) {
        return ResponseEntity.ok(userService.rateUser(userId, rating));
    }

    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateProfile(@RequestBody User u) {
        return ResponseEntity.ok(userService.updateProfile(u));
    }
}
