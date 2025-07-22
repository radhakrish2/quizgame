package com.tatastrive.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.UserDTO;

@Service
public interface UserService {
	UserDTO createUser(UserDTO userDTO);
	Optional<UserDTO> getUserById(Long id);
	List<UserDTO> getAllUsers();
	void deleteUser(Long id);
	UserDTO updateUser(Long id, UserDTO userDTO);
}