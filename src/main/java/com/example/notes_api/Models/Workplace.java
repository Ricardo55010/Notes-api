package com.example.notes_api.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workplace")
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workplace_id")
    private Long workplace_id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @ManyToMany
    @JoinTable(name = "workplace_user", joinColumns = @JoinColumn(name = "workplace_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<User> users;
    @OneToMany
    private List<Notes> notes;

    public Workplace(){


    }

    public Workplace(String name, String description, String address, List<User> users, List<Notes> notes) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.users = users;
        this.notes = notes;
    }


    public Long getId() {
        return workplace_id;
    }
    public void setId(Long workplace_id) {
        this.workplace_id = workplace_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
}
