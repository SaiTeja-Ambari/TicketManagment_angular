package com.example.TicketManagementSystem.api.dao.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String name;

    public Category(int categoryId, String name) {
        super();
        this.categoryId = categoryId;
        this.name = name;
    }


    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
