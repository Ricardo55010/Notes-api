package com.example.notes_api.Repositories;

import com.example.notes_api.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByName(String name);
    @Query("SELECT u FROM User u")
    Page<User> findByNamePaginated(Pageable pageable);
}
