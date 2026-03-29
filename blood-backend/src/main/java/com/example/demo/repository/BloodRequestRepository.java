package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.BloodRequest;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {

}
