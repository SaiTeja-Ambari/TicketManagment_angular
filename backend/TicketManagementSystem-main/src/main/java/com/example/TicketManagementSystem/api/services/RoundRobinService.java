package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.RoundRobin;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.RoundRobinRepository;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoundRobinService {

    @Autowired
    RoundRobinRepository roundRobinRepository;

    @Autowired
    UserService userService;

    public User assignTicket(int category_id) {
        // get all users belonging to this category
        List<RoundRobin> roundRobins = roundRobinRepository.findAll();
        List<RoundRobin> sameCategoryRoundRobin = new ArrayList<>();
        int min = 99999999;

        // Member with minimum number of tickets assigned
        int member_id = 0;
        for(RoundRobin roundRobin: roundRobins) {
            if (roundRobin.getCategory_id() == category_id) {
                sameCategoryRoundRobin.add(roundRobin);
                if (roundRobin.getTickets_assigned() < min) {
                    min = roundRobin.getTickets_assigned();
                    member_id = roundRobin.getMember_id();
                }
            }
        }
        RoundRobin res;
        // assign ticket to member_id
        res = getByMemberID(member_id);
        res.setTickets_assigned(res.getTickets_assigned()+1);
        roundRobinRepository.save(res);
        return userService.getUserByID(res.getMember_id());
    }

    public void addMember(Category category, User user) {
        RoundRobin roundRobin = new RoundRobin(category.getCategoryId(), user.getUserId(), 0);
        if (!checkIfAlreadyExists(category.getCategoryId(), user.getUserId())) {
            roundRobinRepository.save(roundRobin);
        }
    }

    public RoundRobin getByMemberID(int id) {
        List<RoundRobin> roundRobins = roundRobinRepository.findAll();
        for(RoundRobin roundRobin: roundRobins) {
            if (roundRobin.getMember_id() == id) {
                return roundRobin;
            }
        }
        return null;
    }

    public boolean checkIfAlreadyExists(int category_id, int user_id) {
        boolean categoryExists = false;
        boolean userExists = false;
        List<RoundRobin> roundRobins = roundRobinRepository.findAll();
        for(RoundRobin roundRobin: roundRobins) {
            if (roundRobin.getCategory_id() == category_id) {
                categoryExists = true;
                break;
            }
        }
        List<RoundRobin> roundRobins1 = roundRobinRepository.findAll();
        for(RoundRobin roundRobin: roundRobins1) {
            if (roundRobin.getMember_id() == user_id) {
                userExists = true;
                break;
            }
        }
        return userExists && categoryExists;
    }
}
