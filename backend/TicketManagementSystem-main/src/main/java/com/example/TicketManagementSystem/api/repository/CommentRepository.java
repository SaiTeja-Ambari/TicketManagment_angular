package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
}
