package com.york.aboutreferralservice.dto;

import com.york.aboutreferralservice.entities.PatientNote;

import java.util.Date;

public class PatientNoteDTO {
    private Long id;
    private String content;
    private Date createdAt;

    public PatientNoteDTO(PatientNote note){
        this.id = note.getId();
        this.content = note.getContent();
        this.createdAt = note.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
