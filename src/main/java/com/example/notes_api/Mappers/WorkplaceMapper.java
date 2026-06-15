package com.example.notes_api.Mappers;

import com.example.notes_api.DTO.WorkplaceDTO;
import com.example.notes_api.Models.Workplace;

public class WorkplaceMapper {

    public static WorkplaceDTO workplaceToWorkplaceDTO(Workplace workplace) {
        return new WorkplaceDTO(workplace.getName(), workplace.getAddress(), workplace.getDescription(), workplace.getUsers(), workplace.getNotes());
    }

    public static Workplace workplaceDTOToWorkplace(WorkplaceDTO workplaceDTO) {
        return new Workplace(workplaceDTO.getName(), workplaceDTO.getAddress(), workplaceDTO.getDescription(), workplaceDTO.getUsers(), workplaceDTO.getNotes());
    }
}
