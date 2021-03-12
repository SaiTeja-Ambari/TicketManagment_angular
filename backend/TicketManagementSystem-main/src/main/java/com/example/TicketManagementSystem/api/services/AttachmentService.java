package com.example.TicketManagementSystem.api.services;

import com.example.TicketManagementSystem.api.dao.models.Attachments;
import com.example.TicketManagementSystem.api.dao.models.Ticket;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.AttachmentRepository;
import com.example.TicketManagementSystem.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Attachments saveAttachment(User user, MultipartFile file, Ticket ticket) {
        try {
            Attachments attachment = new Attachments();
            attachment.setUserId(user);
            attachment.setAttachment(file.getBytes());
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentType(file.getContentType());
            attachment.setTicket(ticket);
            attachmentRepository.save(attachment);

            // save to Ticket
            List<Attachments> attachments = ticket.getAttachments();
            attachments.add(attachment);
            ticket.setAttachments(attachments);
            ticketRepository.save(ticket);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Attachments getFile(Integer attachmentId) {
        List<Attachments> attachments = attachmentRepository.findAll();
        for(Attachments attachment: attachments) {
            if (attachment.getAttachmentId() == attachmentId) {
                return attachment;
            }
        }
        return null;
    }

    public List<Attachments> getFiles() {
        return attachmentRepository.findAll();
    }
}
