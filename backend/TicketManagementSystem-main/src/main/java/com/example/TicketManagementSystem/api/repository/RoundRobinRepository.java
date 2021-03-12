package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.RoundRobin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRobinRepository extends JpaRepository<RoundRobin, Integer> {
}
