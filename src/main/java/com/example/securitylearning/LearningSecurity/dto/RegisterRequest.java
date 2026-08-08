package com.example.securitylearning.LearningSecurity.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
