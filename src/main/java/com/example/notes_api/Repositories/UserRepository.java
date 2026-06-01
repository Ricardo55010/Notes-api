package com.example.notes_api.Repositories;

import com.example.notes_api.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
