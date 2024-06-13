package com.york.aboutreferralservice.services;

import com.york.aboutreferralservice.entities.Patient;
import com.york.aboutreferralservice.entities.PatientNote;
import com.york.aboutreferralservice.repositories.PatientNoteRepository;
import com.york.aboutreferralservice.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private PatientNoteRepository patientNoteRepository;

    public PatientService(PatientRepository patientRepository, PatientNoteRepository patientNoteRepository){
        this.patientRepository = patientRepository;
        this.patientNoteRepository = patientNoteRepository;
    }

    public Patient getPatientById(Long id){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElseThrow();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> existingPatientOpt = patientRepository.findById(id);
        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();
            existingPatient.setName(patient.getName());
            existingPatient.setDateOfBirth(patient.getDateOfBirth());
            existingPatient.setContactInfo(patient.getContactInfo());
            existingPatient.setReasonForReferral(patient.getReasonForReferral());
            existingPatient.setReferralStatus(patient.getReferralStatus());
            for(PatientNote note : patient.getPatientNotes()){
                if(note.getId() == null){
                    PatientNote newNote = new PatientNote(note.getContent(),existingPatient);
                    existingPatient.addNote(newNote);
                }
            }
            return patientRepository.save(existingPatient);
        } else {
            return null;
        }
    }

    public PatientNote addPatientNote(Long patientId, String content) {
        Optional<Patient> existingPatientOpt = patientRepository.findById(patientId);
        if (existingPatientOpt.isPresent()) {
            Patient patient = existingPatientOpt.get();
            PatientNote note = new PatientNote(content, patient);
            patientNoteRepository.save(note);
            return note;
        } else {
            throw new RuntimeException("Patient not found");
        }
    }


    public boolean deletePatientById(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
