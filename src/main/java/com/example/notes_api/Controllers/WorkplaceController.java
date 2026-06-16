package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.WorkplaceDTO;
import com.example.notes_api.Mappers.WorkplaceMapper;
import com.example.notes_api.Services.WorkPlaceService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workplace/")
public class WorkplaceController {
    private final WorkPlaceService workplaceService;

    public WorkplaceController(WorkPlaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @PutMapping("/add-user")
    ResponseEntity<String> addUserToWorkplace(@RequestParam Long userId, @RequestParam Long workplaceId){
        return ResponseEntity.ok(workplaceService.addUserToWorkplace(userId, workplaceId));
    }
    @DeleteMapping("/remove-user")
    ResponseEntity<String> removeUserFromWorkplace(@RequestParam Long userId, @RequestParam Long workplaceId){
        return ResponseEntity.ok(workplaceService.removeUserFromWorkplace(userId, workplaceId));
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteWorkplace(@PathVariable Long id){
        return ResponseEntity.ok(workplaceService.deleteWorkplace(id));
    }
    @PatchMapping("/update")
    ResponseEntity<String> updateWorkplace(@RequestParam Long id, @RequestParam String name, @RequestParam String address, @RequestParam String description){
        return ResponseEntity.ok(workplaceService.updateWorkplace(id, name, address, description));
    }
    @PostMapping("/add-note")
    ResponseEntity<String> addNoteToWorkplace(@RequestParam Long noteId, @RequestParam Long workplaceId){
        return ResponseEntity.ok(workplaceService.addNoteToWorkplace(noteId, workplaceId));
    }
    @PutMapping("/remove-note")
    ResponseEntity<String> removeNoteFromWorkplace(@RequestParam Long noteId, @RequestParam Long workplaceId){
        return ResponseEntity.ok(workplaceService.removeNoteFromWorkplace(noteId, workplaceId));
    }
    @PostMapping("/create")
    ResponseEntity<String> createWorkplace(@RequestParam String name, @RequestParam String address, @RequestParam String description){
        return ResponseEntity.ok(workplaceService.createWorkplace(name, address, description));
    }
    @GetMapping("/get/{id}")
    ResponseEntity<WorkplaceDTO> getWorkplaceById(@PathVariable Long id){
        return ResponseEntity.ok(workplaceService.getWorkplaceById(id));
    }


}
