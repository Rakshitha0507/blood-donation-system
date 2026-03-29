package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Donor;
import com.example.demo.service.DonorService;

@RestController
@RequestMapping("/donor")
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorService donorService;

    // API to register a donor
    @PostMapping("/register")
    public Donor registerDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    // API to get all donors
    @GetMapping("/all")
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    // FIXED: Added explicit variable name
    @GetMapping("/match/{bloodGroup}")
    public List<Donor> matchDonors(
            @PathVariable("bloodGroup") String bloodGroup) {

        return donorService.findDonorsByBloodGroup(bloodGroup);
    }

    // FIXED: Added explicit variable names
    @GetMapping("/match/{bloodGroup}/{location}")
    public List<Donor> matchDonorsByLocation(
            @PathVariable("bloodGroup") String bloodGroup,
            @PathVariable("location") String location) {

        return donorService.findDonorsByBloodGroupAndLocation(bloodGroup, location);
    }

    // FIXED: Added explicit request param names
    @GetMapping("/search")
    public List<Donor> searchDonors(
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("location") String location) {

        return donorService.findDonorsByBloodGroupAndLocation(bloodGroup, location);
    }
}