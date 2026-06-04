package com.example.notes_api.Services;

import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Exceptions.UserNotFoundException;
import com.example.notes_api.Mappers.UserMapper;
import com.example.notes_api.Models.User;
import com.example.notes_api.Repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userRepository.findByNamePaginated(pageable).map(UserMapper::mapUserToDTO);
        return users;
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
        return "User deleted";
    }

    public String updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        userRepository.save(UserMapper.mapDTOToUser(userDTO));
        return "User updated";
    }

    public Long postUser(UserDTO userDTO) throws Exception {
        User user = UserMapper.mapDTOToUser(userDTO);
        if (userRepository.findByName(user.getName()) != null)
            throw new Exception("User already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.mapUserToDTO(user);
    }

    public UserDTO getUserByName(String name) throws Exception {

        UserDTO userDTO = UserMapper.mapUserToDTO(userRepository.findByName(name));
        if (!userRepository.existsById(userDTO.getId()))
            throw new Exception("");
        return userDTO;
    }

    public UserDetails loadUserByUsername(String username) {
        try {
            User account = userRepository.findByName(username);
            if (account == null) throw new UsernameNotFoundException("User not found");
            return org.springframework.security.core.userdetails.User
                    .withUsername(account.getName())
                    .password(account.getPassword())
                    .roles(account.getRole())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}