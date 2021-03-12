package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Groups;
import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.GroupRepository;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void askForDetails(Ticket ticket) {
        User user = ticket.getCreatedByUserId();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Asking for Details for Ticket ID " + ticket.getTicketId());
        simpleMailMessage.setText("Hi " + user.getName() + ",\n\n" +
                "Member is Asking for Details regarding the Ticket you opened on " + ticket.getStartDate() +
                " with Ticket ID " + ticket.getTicketId() + "\n\n" +
                "Ticket Details: \n\n" +
                "Title: " + ticket.getTitle() + "\n" +
                "Description: " + ticket.getDescription() + "\n" +
                "Created On: " + ticket.getStartDate());
        javaMailSender.send(simpleMailMessage);
    }

    public void ticketClosed(Ticket ticket) {
        User user = ticket.getCreatedByUserId();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Ticket ID " + ticket.getTicketId() + " Closed");
        simpleMailMessage.setText("Hi " + user.getName() + ",\n\n" +
                "The Ticket you opened on " + ticket.getStartDate() +
                " with Ticket ID " + ticket.getTicketId() + " CLOSED\n\n" +
                "Ticket Details: \n\n" +
                "Title: " + ticket.getTitle() + "\n" +
                "Description: " + ticket.getDescription() + "\n" +
                "Created On: " + ticket.getStartDate());
        javaMailSender.send(simpleMailMessage);
    }

    public void notifyMember(Ticket ticket) {
        Set<User> members = userService.getUserInSameCategory(ticket.getCategory().getCategoryId());


        // notify all members in this set that a ticket has been created
        for(User member: members) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(member.getEmail());
            simpleMailMessage.setSubject("New Ticket ID " + ticket.getTicketId() + " Created");
            simpleMailMessage.setText("Hi " + member.getName() + ",\n\n" +
                    "A New Ticket has been created on " + ticket.getStartDate() +
                    " with Ticket ID " + ticket.getTicketId() + "\n\n" +
                    "Ticket Details: \n\n" +
                    "Title: " + ticket.getTitle() + "\n" +
                    "Description: " + ticket.getDescription() + "\n" +
                    "Created On: " + ticket.getStartDate() + "\n" +
                    "Created By: " + ticket.getCreatedByUserId().getName());
            javaMailSender.send(simpleMailMessage);
        }


//        List<User> users = userRepository.findAll();
//        List<User> members = new ArrayList<>();
//        for(User user: users) {
//            List<Groups> groups = user.getGroups();
//            for(Groups group: groups) {
//                if (group.getCategory().getCategoryId() == category_id) {
//                    members.add(user);
//                    break;
//                }
//            }
//        }
    }
}
