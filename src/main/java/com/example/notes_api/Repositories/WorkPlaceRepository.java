package com.example.notes_api.Repositories;

import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.Workplace;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPlaceRepository extends JpaRepository<Workplace, Long> {
}
