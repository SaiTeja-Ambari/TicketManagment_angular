package com.example.TicketManagementSystem.api.dao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Comments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @OneToOne
    private User userId;
    private String comment;

    //Many comments belongs to one ticket
    @ManyToOne
    @JsonBackReference
    private Ticket ticket;


    public Comments(int commentId, User userId, String comment, Ticket ticket) {
        super();
        this.commentId = commentId;
        this.userId = userId;
        this.comment = comment;
        this.ticket = ticket;
    }


    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getCommentId() {
        return commentId;
    }


    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }


    public User getUserId() {
        return userId;
    }


    public void setUserId(User userId) {
        this.userId = userId;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public Ticket getTicket() {
        return ticket;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

