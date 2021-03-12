package com.example.TicketManagementSystem.api.dao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attachmentId;

    @OneToOne
    private User userId;

    private String attachmentName;
    private String attachmentType;

    @JsonIgnore
    @Lob
    private byte[] attachment;

    // Many attachment belongs to one ticket
    @ManyToOne
    @JsonBackReference
    private Ticket ticket;

    public Attachments(User userId, String attachmentName, String attachmentType, byte[] attachment, Ticket ticket) {
        this.userId = userId;
        this.attachmentName = attachmentName;
        this.attachmentType = attachmentType;
        this.attachment = attachment;
        this.ticket = ticket;
    }

    public Attachments() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }
}

