package com.tatastrive.app.dto;

import com.tatastrive.app.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {

    private Long id;  
    private String username;
    private String password;
    private String email;
    private Role role; // Enum: ADMIN, HOST, PLAYER
}
