package com.tatastrive.app.auth;

import com.tatastrive.app.entity.Role;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String email;
    private String password;
    private Role role;
}
