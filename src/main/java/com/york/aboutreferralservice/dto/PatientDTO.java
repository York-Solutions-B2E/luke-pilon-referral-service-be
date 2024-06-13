package com.york.aboutreferralservice.dto;

import com.york.aboutreferralservice.entities.Patient;
import com.york.aboutreferralservice.entities.PatientNote;
import com.york.aboutreferralservice.entities.ReferralStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDTO {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String contactInfo;
    private String reasonForReferral;
    private ReferralStatus referralStatus;

    private List<PatientNoteDTO> patientNotes;

    public PatientDTO(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.contactInfo = patient.getContactInfo();
        this.reasonForReferral = patient.getReasonForReferral();
        this.referralStatus = patient.getReferralStatus();
        this.patientNotes = new ArrayList<PatientNoteDTO>();
        if(patient.getPatientNotes() != null && patient.getPatientNotes().size() > 0){
            for(PatientNote note : patient.getPatientNotes()){
                PatientNoteDTO noteDTO = new PatientNoteDTO(note);
                this.patientNotes.add(noteDTO);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getReasonForReferral() {
        return reasonForReferral;
    }

    public ReferralStatus getReferralStatus() {
        return referralStatus;
    }

    public List<PatientNoteDTO> getPatientNotes() {
        return patientNotes;
    }
}
