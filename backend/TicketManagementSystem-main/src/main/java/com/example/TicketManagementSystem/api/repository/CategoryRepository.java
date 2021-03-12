package com.example.TicketManagementSystem.api.repository;

import com.example.TicketManagementSystem.api.dao.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
