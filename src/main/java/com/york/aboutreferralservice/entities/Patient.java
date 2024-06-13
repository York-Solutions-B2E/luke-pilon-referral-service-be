package com.york.aboutreferralservice.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @NotNull
    @Column(name = "contact_info", nullable = false)
    @Pattern(regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}", message = "Contact info must be in the form (###)###-####")
    private String contactInfo;

    @NotNull
    @Column(name = "reason_for_referral", nullable = false)
    @Size(min = 1, max = 255)
    private String reasonForReferral;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "referral_status", nullable = false)
    private ReferralStatus referralStatus;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PatientNote> patientNotes;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    // Getters and Setters

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<PatientNote> getPatientNotes() {
        return patientNotes;
    }

    public void addNote(PatientNote note) {
        this.patientNotes.add(note);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setReasonForReferral(String reasonForReferral) {
        this.reasonForReferral = reasonForReferral;
    }

    public void setReferralStatus(ReferralStatus referralStatus) {
        this.referralStatus = referralStatus;
    }
}
