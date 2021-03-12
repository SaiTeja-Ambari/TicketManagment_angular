package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachments, Integer> {
}
