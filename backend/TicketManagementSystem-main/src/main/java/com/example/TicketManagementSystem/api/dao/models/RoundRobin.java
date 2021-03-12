package com.example.TicketManagementSystem.api.dao.models;

import javax.persistence.*;

@Entity
@Table(name = "TicketsAssigned")
public class RoundRobin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public int category_id;

    public RoundRobin() {
    }

    public RoundRobin(int category_id, int member_id, int tickets_assigned) {
        this.category_id = category_id;
        this.member_id = member_id;
        this.tickets_assigned = tickets_assigned;
    }

    public int member_id;

    public int tickets_assigned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getTickets_assigned() {
        return tickets_assigned;
    }

    public void setTickets_assigned(int tickets_assigned) {
        this.tickets_assigned = tickets_assigned;
    }
}
