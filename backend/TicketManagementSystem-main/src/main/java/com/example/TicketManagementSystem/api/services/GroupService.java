package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Groups;
import com.example.TicketManagementSystem.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Groups getGroupByID(Integer id) {
        List<Groups> groups = groupRepository.findAll();
        for(Groups group: groups) {
            if (group.getGroupId() == id) {
                return group;
            }
        }
        return null;
    }
}
