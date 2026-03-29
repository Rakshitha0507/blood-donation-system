package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String bloodGroup;
    private String hospital;
    private String location;

    // NEW FIELD
    private String status; // PENDING, ACCEPTED, REJECTED

    public BloodRequest() {
    }

    // AUTO SET DEFAULT STATUS
    @PrePersist
    public void prePersist() {
        if (this.status == null || this.status.isEmpty()) {
            this.status = "PENDING";
        }
    }

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getHospital() {
        return hospital;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}