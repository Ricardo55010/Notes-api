package com.example.notes_api.Services;

import com.example.notes_api.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Long postUser(UserDTO userDTO) throws Exception;

    List<UserDTO> getAllUsers();
    String updateUser(UserDTO userDTO);
    String deleteUser(Long id);
    UserDTO getUserByName(String name) throws Exception;
    UserDTO getUserById(Long id);
}
