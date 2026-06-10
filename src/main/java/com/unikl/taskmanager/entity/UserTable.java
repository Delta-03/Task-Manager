package com.unikl.taskmanager.entity;

import jakarta.persistence.*;

@Entity
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnumeration role;

    // --- Getters and Setters (Required for Spring Boot to read/write data) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public RoleEnumeration getRole() { return role; }
    public void setRole(RoleEnumeration role) { this.role = role; }
}