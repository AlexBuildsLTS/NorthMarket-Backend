package com.northmarket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    private String name;

    // Roles stored in separate table user_roles
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="user_roles", joinColumns=@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Set<Role> roles = new HashSet<>();

    private double rating=0.0;
    private int ratingCount=0;
    private boolean verified=false;
    private LocalDateTime createdAt= LocalDateTime.now();

    // For convenience if you want isSeller checks
    public boolean isSeller() {
        return roles.contains(Role.SELLER);
    }
}
