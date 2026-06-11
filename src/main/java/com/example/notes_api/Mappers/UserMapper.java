package com.example.notes_api.Mappers;

import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Models.User;

public class UserMapper {
    public static UserDTO mapUserToDTO(User user){
        return new UserDTO(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getRole());
    }

    public static User mapDTOToUser(UserDTO userDTO){
        return new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
    }
}
