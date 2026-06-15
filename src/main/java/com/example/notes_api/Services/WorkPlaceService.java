package com.example.notes_api.Services;

import com.example.notes_api.DTO.WorkplaceDTO;
import com.example.notes_api.Models.Workplace;

public interface WorkPlaceService {
    String addUserToWorkplace(Long userId, Long workplaceId);
    String removeUserFromWorkplace(Long userId, Long workplaceId);
    String deleteWorkplace(Long id);
    String updateWorkplace(Long id, String name, String address, String description);
    String addNoteToWorkplace(Long noteId, Long workplaceId);
    String removeNoteFromWorkplace(Long noteId, Long workplaceId);
    String createWorkplace(String name, String address, String description);
    WorkplaceDTO getWorkplaceById(Long id);
}
