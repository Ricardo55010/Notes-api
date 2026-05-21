package com.example.notes_api.Services;

import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Mappers.UserMapper;
import com.example.notes_api.Models.User;
import com.example.notes_api.Repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = userRepository.findAll().stream().map(UserMapper::mapUserToDTO).toList();
        return users;
    }

    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User deleted";
    }

    public String updateUser(UserDTO userDTO){
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        if(user == null){
            return "User not found";
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        userRepository.save(UserMapper.mapDTOToUser(userDTO));
        return "User updated";
    }

    public String postUser(UserDTO userDTO){
        User user = UserMapper.mapDTOToUser(userDTO);
        userRepository.save(user);
        return "User created";
    }
}
