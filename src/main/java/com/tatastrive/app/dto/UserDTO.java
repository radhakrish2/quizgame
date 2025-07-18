package com.tatastrive.app.dto;

import com.tatastrive.app.entity.Role;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

   
    private String username;

   
    private String email;

    private Role role; // Enum: ADMIN, HOST, PLAYER
}
