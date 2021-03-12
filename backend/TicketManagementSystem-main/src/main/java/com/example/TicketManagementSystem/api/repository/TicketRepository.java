package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
