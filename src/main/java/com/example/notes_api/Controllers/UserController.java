package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.AccessDTO;
import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Mappers.UserMapper;
import com.example.notes_api.Models.User;
import com.example.notes_api.Services.UserService;
import com.example.notes_api.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping
    public ResponseEntity<Long> postUser(UserDTO userDTO) throws Exception{
        User user = UserMapper.mapDTOToUser(userDTO);
        Long userId = userService.postUser(userDTO);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(UserDTO userDTO){
        String message = userService.updateUser(userDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public AccessDTO login(@RequestParam String username, @RequestParam String password) throws Exception {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            AccessDTO access = new AccessDTO();
            access.setJwt(jwtUtil.generateToken(username));
            access.setUser(userService.getUserByName(username));
            return access;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }


    @GetMapping("/refresh")
    public UserDTO refresh() throws  Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return  userService.getUserByName(username);
    }

}
