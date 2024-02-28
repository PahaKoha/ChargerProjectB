package com.backend.PowerUp.dto;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
