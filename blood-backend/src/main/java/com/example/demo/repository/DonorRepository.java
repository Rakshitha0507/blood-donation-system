package com.example.demo.repository;
import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroupIgnoreCase(String bloodGroup);

Optional<Donor> findByUsername(String username);
    
    List<Donor> findByBloodGroupIgnoreCaseAndLocationIgnoreCase(String bloodGroup, String location);

}

