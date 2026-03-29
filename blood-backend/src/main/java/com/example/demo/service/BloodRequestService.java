package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BloodRequest;
import com.example.demo.model.BloodRequestResponse;
import com.example.demo.model.Donor;
import com.example.demo.repository.BloodRequestRepository;
import com.example.demo.repository.DonorRepository;

@Service
public class BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private EmailService emailService;

    // Save blood request
    public BloodRequestResponse saveRequest(BloodRequest request) {

        if (request.getBloodGroup() != null) {
            request.setBloodGroup(request.getBloodGroup().replace(" ", "+"));
        }

        // Default status
        if (request.getStatus() == null || request.getStatus().isEmpty()) {
            request.setStatus("PENDING");
        }

        // Save request
        BloodRequest savedRequest = bloodRequestRepository.save(request);

        // Find matching donors
        List<Donor> donors = donorRepository
                .findByBloodGroupIgnoreCaseAndLocationIgnoreCase(
                        request.getBloodGroup(),
                        request.getLocation()
                );

        // Send email to each donor
        int emailsSent = 0;

        for (Donor donor : donors) {
            String subject = "Urgent Blood Request";

            String message = "Dear " + donor.getName() + ",\n\n"
                    + "There is an urgent blood request.\n\n"
                    + "Patient Name: " + request.getPatientName() + "\n"
                    + "Blood Group: " + request.getBloodGroup() + "\n"
                    + "Location: " + request.getLocation() + "\n"
                    + "Hospital: " + request.getHospital() + "\n\n"
                    + "Please respond if you are available.\n\n"
                    + "Thank you!";

            boolean success = emailService.sendEmail(donor.getEmail(), subject, message);

            if (success) {
                emailsSent++;
            }
        }

        String resultMsg = "Blood request saved successfully. "
                + emailsSent + " out of "
                + donors.size()
                + " donors notified.";

        return new BloodRequestResponse(savedRequest, donors, resultMsg);
    }

    // Get all blood requests
    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    // NEW METHOD: Update status
    public BloodRequest updateStatus(Long id, String status) {

        BloodRequest request = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(status);

        return bloodRequestRepository.save(request);
    }
}