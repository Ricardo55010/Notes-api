package com.example.notes_api.Repositories;

import com.example.notes_api.Models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository  extends JpaRepository<Notes, Long> {
}
