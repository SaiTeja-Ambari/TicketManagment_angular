package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Integer> {
}
