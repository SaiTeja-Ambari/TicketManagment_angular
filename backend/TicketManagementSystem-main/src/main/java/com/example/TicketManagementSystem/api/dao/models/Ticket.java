package com.example.TicketManagementSystem.api.dao.models;

import com.example.TicketManagementSystem.api.repository.EnPriorityType;
import com.example.TicketManagementSystem.api.repository.EnStatusType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnStatusType status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnPriorityType priority;

    @OneToOne
    private User createdByUserId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(nullable = false)
    private String description;

    // One ticket can have many comments
    @OneToMany(mappedBy = "ticket")
    private List<Comments> comments;

    // One ticket can have many attachments
    @OneToMany(mappedBy = "ticket")
    private List<Attachments> attachments;

    // Many tickets belongs to one Category
    @ManyToOne(optional = false)
    private Category category;

    // Many tickets can be assigned to one user
    @ManyToOne
    private User user;

    public Ticket(int ticketId, User createdByUserId, EnStatusType status, String title, EnPriorityType priority,
                  Date startDate, Date dueDate, String description, List<Comments> comments, List<Attachments> attachments,
                  Category category, User user) {
        super();
        this.ticketId = ticketId;
        this.createdByUserId = createdByUserId;
        this.status = status;
        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.description = description;
        this.comments = comments;
        this.attachments = attachments;
        this.category = category;
        this.user = user;
    }

    public Ticket() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public User getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(User createdByUserId) {
        this.createdByUserId = createdByUserId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnPriorityType getPriority() {
        return priority;
    }

    public void setPriority(EnPriorityType priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnStatusType getStatus() {
        return status;
    }

    public void setStatus(EnStatusType status) {
        this.status = status;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
