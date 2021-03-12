package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.Groups;
import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    RoundRobinService roundRobinService;

    public List<Ticket> getTicketCreatedByUser(User user) {
        List<Ticket> tickets = ticketRepository.findAll();
        List<Ticket> result = new ArrayList<>();
        for(Ticket ticket: tickets) {
            if (ticket.getCreatedByUserId().getUserId() == user.getUserId()) {
                result.add(ticket);
            }
        }
        return result;
    }

    public List<Ticket> getTicketWithSameCategory(User user) {
        List<Groups> groups = user.getGroups();
        List<Ticket> result = new ArrayList<>();
        try {
            int category_id = groups.get(0).getCategory().getCategoryId();
            List<Ticket> tickets = ticketRepository.findAll();
            for(Ticket ticket: tickets) {
                if (ticket.getCategory().getCategoryId() == category_id) {
                    result.add(ticket);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Ticket findByID(Integer id) {
        List<Ticket> tickets = ticketRepository.findAll();
        for(Ticket ticket: tickets) {
            if (ticket.getTicketId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket createTicket(User user, Ticket ticket) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String curr_date = format.format(c.getTime());
        c.add(Calendar.DATE, 7);
        String due_date = format.format(c.getTime());
        ticket.setStartDate(format.parse(curr_date));
        ticket.setDueDate(format.parse(due_date));

        ticket.setStatus(EnStatusType.OPEN);
        ticket.setPriority(EnPriorityType.MEDIUM);

        ticket.setCreatedByUserId(user);

        Category category = ticket.getCategory();

        // assign to a user in this category -> currently random allocation

//        List<Groups> groups = groupRepository.findAll();
//        List<User> members = new ArrayList<>();
//        for(Groups group: groups) {
//            List<User> users = group.getUser();
//            for(User u: users) {
//                if (u.getType().equals(EnUserType.MEMBER)) {
//                    members.add(u);
//                }
//            }
//        }

        User member = roundRobinService.assignTicket(category.getCategoryId());

//        int max = members.size();
//        int min = 0;
//        Random random = new Random();
//        int index = random.nextInt(max-min)+min;
//
        ticket.setUser(member);
        return ticket;
    }
}
