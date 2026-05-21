package com.example.notes_api.Services;

import com.example.notes_api.DTO.UserDTO;

import java.util.List;

public interface UserService {
    String postUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
    String updateUser(UserDTO userDTO);
    String deleteUser(Long id);
}
