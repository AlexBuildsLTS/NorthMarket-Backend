package com.northmarket.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private boolean seller;  // If user wants to be a seller
}
