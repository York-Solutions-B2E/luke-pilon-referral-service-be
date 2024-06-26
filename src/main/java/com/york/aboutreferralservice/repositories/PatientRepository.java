package com.york.aboutreferralservice.repositories;

import com.york.aboutreferralservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
