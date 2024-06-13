package com.york.aboutreferralservice.repositories;

import com.york.aboutreferralservice.entities.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientNoteRepository extends JpaRepository<PatientNote, Long> {
}
