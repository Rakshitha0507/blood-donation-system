package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Donor;
import com.example.demo.repository.DonorRepository;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save donor (WITH PASSWORD ENCRYPTION)
    public Donor saveDonor(Donor donor) {

        // 🔐 encode password
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));

        return donorRepository.save(donor);
    }

    // Get all donors
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public List<Donor> findDonorsByBloodGroup(String bloodGroup) {
        if (bloodGroup != null) {
            bloodGroup = bloodGroup.replace(" ", "+");
        }
        return donorRepository.findByBloodGroupIgnoreCase(bloodGroup);
    }

    public List<Donor> findDonorsByBloodGroupAndLocation(String bloodGroup, String location) {
        if (bloodGroup != null) {
            bloodGroup = bloodGroup.replace(" ", "+");
        }
        return donorRepository.findByBloodGroupIgnoreCaseAndLocationIgnoreCase(bloodGroup, location);
    }
}