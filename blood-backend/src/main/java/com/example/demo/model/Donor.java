package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bloodGroup;
    private String location;
    private String phone;
    private String email;

    private String username;
    private String password;

    public Donor() {}

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getBloodGroup() { return bloodGroup; }

    public String getLocation() { return location; }

    public String getPhone() { return phone; }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public void setLocation(String location) { this.location = location; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setEmail(String email) { this.email = email; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }
}