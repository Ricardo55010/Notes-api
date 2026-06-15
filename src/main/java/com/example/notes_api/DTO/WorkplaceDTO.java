package com.example.notes_api.DTO;

import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.User;

import java.util.List;

public class WorkplaceDTO {
    String name;
    String address;
    String description;
    List<User> users;
    List<Notes> notes;

    public WorkplaceDTO() {}

    public WorkplaceDTO(String name, String address,String description, List<User> users, List<Notes> notes) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.users = users;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<Notes> getNotes() {
        return notes;
    }
    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
