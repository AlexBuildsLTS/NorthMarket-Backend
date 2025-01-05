package com.northmarket.service;

import com.northmarket.dto.RegisterRequest;
import com.northmarket.model.Role;
import com.northmarket.model.User;
import com.northmarket.repository.UserRepository;
import com.northmarket.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setName(req.getName());

        Set<Role> roles = new HashSet<>();
        roles.add(Role.BUYER);
        if (req.isSeller()) {
            roles.add(Role.SELLER);
        }
        u.setRoles(roles);

        return userRepository.save(u);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal up) {
            return userRepository.findById(up.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        throw new RuntimeException("No user logged in");
    }

    @Transactional
    public User rateUser(Long userId, Double rating) {
        User target = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        target.setRatingCount(target.getRatingCount() + 1);
        double newAvg = (target.getRating() * (target.getRatingCount() - 1) + rating) / target.getRatingCount();
        target.setRating(newAvg);
        return userRepository.save(target);
    }

    @Transactional
    public User updateProfile(User updated) {
        User current = getCurrentUser();
        current.setName(updated.getName());
        return userRepository.save(current);
    }
}
