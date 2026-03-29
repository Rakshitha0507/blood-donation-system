package com.example.demo;

import com.example.demo.model.Donor;
import com.example.demo.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/donor/auth")
@CrossOrigin(origins = "*")
public class DonorAuthController {

    @Autowired
    private DonorRepository donorRepository;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Donor loginRequest) {

        Optional<Donor> donorOpt = donorRepository.findByUsername(loginRequest.getUsername());

        Map<String, String> response = new HashMap<>();

        if (donorOpt.isPresent()) {
            Donor donor = donorOpt.get();

            if (donor.getPassword().equals(loginRequest.getPassword())) {
                response.put("token", "dummy-token"); // simple for now
                response.put("username", donor.getUsername());
                return response;
            }
        }

        response.put("error", "Invalid credentials");
        return response;
    }
}