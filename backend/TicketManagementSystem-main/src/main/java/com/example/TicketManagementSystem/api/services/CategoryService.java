package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

//    public Category getCategoryByID(Category category) {
//        int id = category.getCategoryId();
//        List<Category> categories = categoryRepository.findAll();
//        for(Category cat: categories) {
//            if (cat.getCategoryId() == id) {
//                return cat;
//            }
//        }
//        return null;
//    }
}
