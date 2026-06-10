package com.unikl.taskmanager.entity;

import jakarta.persistence.*;

@Entity
public class TaskTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Keep it simple as 'id' to match other entities

    private String title;
    private String description;
    private String status;

    @ManyToOne
    private UserTable owner;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public UserTable getOwner() { return owner; }
    public void setOwner(UserTable owner) { this.owner = owner; }
}