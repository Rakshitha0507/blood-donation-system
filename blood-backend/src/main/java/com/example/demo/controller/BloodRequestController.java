package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.BloodRequest;
import com.example.demo.model.BloodRequestResponse;
import com.example.demo.service.BloodRequestService;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "*")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    // Create request
    @PostMapping("/create")
    public BloodRequestResponse createRequest(@RequestBody BloodRequest request) {
        return bloodRequestService.saveRequest(request);
    }

    // Get all requests
    @GetMapping("/all")
    public List<BloodRequest> getAllRequests() {
        return bloodRequestService.getAllRequests();
    }

    // NEW: Update status (ACCEPT / REJECT)
    @PutMapping("/update-status/{id}")
    public BloodRequest updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return bloodRequestService.updateStatus(id, status);
    }
}