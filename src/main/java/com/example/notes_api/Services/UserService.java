package com.example.notes_api.Services;

import com.example.notes_api.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Long postUser(UserDTO userDTO) throws Exception;

    Page<UserDTO> getAllUsers(Pageable pageable);
    String updateUser(UserDTO userDTO);
    String deleteUser(Long id);
    UserDTO getUserByName(String name) throws Exception;
    UserDTO getUserById(Long id);
}
