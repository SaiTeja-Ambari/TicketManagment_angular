package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Comments;
import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.CommentRepository;
import com.example.TicketManagementSystem.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public void createComment(User user, String comment, Ticket ticket) {
        Comments comment_obj = new Comments();
        comment_obj.setUserId(user);
        comment_obj.setComment(comment);
        comment_obj.setTicket(ticket);
        commentRepository.save(comment_obj);
    }

    public List<Comments> getAllComments(Ticket ticket) {
        List<Comments> comments = commentRepository.findAll();
        List<Comments> result = new ArrayList<>();
        for(Comments comment: comments) {
            if (comment.getTicket().getTicketId() == ticket.getTicketId()) {
                result.add(comment);
            }
        }
        return comments;
    }
}
