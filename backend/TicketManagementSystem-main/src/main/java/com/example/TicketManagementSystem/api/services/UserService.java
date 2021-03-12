package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.Groups;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.EnUserType;
import com.example.TicketManagementSystem.api.repository.GroupRepository;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public User getUserByEmail(String email) {
        List<User> users = userRepository.findAll();
        for(User user: users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByID(int id) {
        List<User> users = userRepository.findAll();
        for(User user: users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    public Set<User> getUserInSameCategory(int category_id) {
        List<Groups> groups = groupRepository.findAll();
        Set<User> members = new HashSet<>();
        for(Groups group: groups) {
            // if group is under this category, then get all members of this group and add in SET
            if (group.getCategory().getCategoryId() == category_id) {
                List<User> users = group.getUser();
                for(User user: users) {
                    if (user.getType().equals(EnUserType.MEMBER)) {
                        members.add(user);
                    }
                }
            }
        }
        return members;
    }
}
