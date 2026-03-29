package com.example.demo.model;

import java.util.List;

public class BloodRequestResponse {
    private BloodRequest bloodRequest;
    private List<Donor> matchedDonors;
    private String message;

    public BloodRequestResponse() {}

    public BloodRequestResponse(BloodRequest bloodRequest, List<Donor> matchedDonors, String message) {
        this.bloodRequest = bloodRequest;
        this.matchedDonors = matchedDonors;
        this.message = message;
    }

    public BloodRequest getBloodRequest() {
        return bloodRequest;
    }

    public void setBloodRequest(BloodRequest bloodRequest) {
        this.bloodRequest = bloodRequest;
    }

    public List<Donor> getMatchedDonors() {
        return matchedDonors;
    }

    public void setMatchedDonors(List<Donor> matchedDonors) {
        this.matchedDonors = matchedDonors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
