package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Mappers.UserMapper;
import com.example.notes_api.Models.User;
import com.example.notes_api.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> postUser(UserDTO userDTO){
        User user = UserMapper.mapDTOToUser(userDTO);
        String message = userService.postUser(userDTO);
        return ResponseEntity.ok(message);
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
}
