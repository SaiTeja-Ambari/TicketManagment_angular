package com.example.TicketManagementSystem.api.dao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @Column(nullable = false)
    private String groupName;
    //Many groups belongs to one category

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    private List<User> user = new ArrayList<>();

    public Groups(int groupId, String groupName, Category category, List<User> user) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.category = category;
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Groups() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}

