package com.example.notes_api.Services;

import com.example.notes_api.DTO.WorkplaceDTO;
import com.example.notes_api.Mappers.WorkplaceMapper;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.User;
import com.example.notes_api.Models.Workplace;
import com.example.notes_api.Repositories.NoteRepository;
import com.example.notes_api.Repositories.UserRepository;
import com.example.notes_api.Repositories.WorkPlaceRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WorkPlaceImpl implements WorkPlaceService{
    private final WorkPlaceRepository workPlaceRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    public WorkPlaceImpl(WorkPlaceRepository workPlaceRepository, NoteRepository noteRepository, UserRepository userRepository) {
        this.workPlaceRepository = workPlaceRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }
    @Override
    public String addUserToWorkplace(Long userId, Long workplaceId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Workplace workplace = workPlaceRepository.findById(workplaceId).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workplace.getUsers().add(user);
        workPlaceRepository.save(workplace);
        return "User added to workplace";
    }
    @Override
    public String removeUserFromWorkplace(Long userId, Long workplaceId) {
        Workplace workplace = workPlaceRepository.findById(userId).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workplace.getUsers().remove(userId);
        workPlaceRepository.save(workplace);
        return "User removed from workplace";
    }

    @Override
    public String addNoteToWorkplace(Long noteId, Long workplaceId) {
        Notes note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        Workplace workplace = workPlaceRepository.findById(workplaceId).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workplace.getNotes().add(note);
        workPlaceRepository.save(workplace);
        return "Note added to workplace";
    }

    @Override
    public String removeNoteFromWorkplace(Long noteId, Long workplaceId) {
        Workplace workplace = workPlaceRepository.findById(workplaceId).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workplace.getNotes().remove(noteId);
        workPlaceRepository.save(workplace);
        return "Note removed from workplace";
    }

    @Override
    public String deleteWorkplace(Long id) {
        Workplace workplace = workPlaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workPlaceRepository.deleteById(id);
        return "Workplace deleted";
    }

    @Override
    public String createWorkplace(String name, String address, String description) {
        Workplace workplace = new Workplace();
        workplace.setName(name);
        workplace.setAddress(address);
        workplace.setDescription(description);
        //
        var principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(principal.getName());
        workplace.getUsers().add(user);
        //
        workPlaceRepository.save(workplace);
        return "Workplace created";
    }

    @Override
    public String updateWorkplace(Long id, String name, String address, String description) {
        Workplace workplace = workPlaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Workplace not found"));
        workplace.setName(name);
        workplace.setAddress(address);
        workplace.setDescription(description);
        workPlaceRepository.save(workplace);
        return "Workplace updated";
    }

    @Override
    public WorkplaceDTO getWorkplaceById(Long id) {
        Workplace workplace = workPlaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Workplace not found"));
        WorkplaceDTO workplaceDTO = WorkplaceMapper.workplaceToWorkplaceDTO(workplace);
        return workplaceDTO;
    }
}
