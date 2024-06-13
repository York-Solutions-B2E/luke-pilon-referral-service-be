package com.york.aboutreferralservice.controllers;

import com.york.aboutreferralservice.dto.PatientDTO;
import com.york.aboutreferralservice.entities.Patient;
import com.york.aboutreferralservice.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@Validated
@RestController
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            List<PatientDTO> dtos = new ArrayList<>();
            for(Patient patient : patients){
                PatientDTO dto = new PatientDTO(patient);
                dtos.add(dto);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            PatientDTO dto = new PatientDTO(patient);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We could not find the given patient.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.createPatient(patient);
            return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
        } catch(InvalidDataAccessResourceUsageException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request, please check your inputs and try again.");
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody Patient patient) {
        try {
            Patient updatedPatient = patientService.updatePatient(id, patient);
            if (updatedPatient != null) {
                PatientDTO dto = new PatientDTO(updatedPatient);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(InvalidDataAccessResourceUsageException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request, please check your inputs and try again.");}
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {
        try {
            boolean isDeleted = patientService.deletePatientById(id);
            if(!isDeleted) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
