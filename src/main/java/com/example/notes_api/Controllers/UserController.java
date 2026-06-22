package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.AccessDTO;
import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Mappers.UserMapper;
import com.example.notes_api.Models.User;
import com.example.notes_api.Services.UserService;
import com.example.notes_api.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    @PostMapping("create-user")
    public ResponseEntity<Long> postUser(@Valid @RequestBody UserDTO userDTO) throws Exception{
        Long userId = userService.postUser(userDTO);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("update-user")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDTO){
        String message = userService.updateUser(userDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("get-allusers")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "1") int size,
    @RequestParam(defaultValue = "name") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessDTO> login(@RequestParam String username, @RequestParam String password) throws Exception {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            AccessDTO access = new AccessDTO();
            access.setJwt(jwtUtil.generateToken(username));
            access.setUser(userService.getUserByName(username));
            return ResponseEntity.ok(access);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }


    @GetMapping("/refresh")
    public ResponseEntity<UserDTO> refresh() throws  Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return  ResponseEntity.ok(userService.getUserByName(username));
    }

}
