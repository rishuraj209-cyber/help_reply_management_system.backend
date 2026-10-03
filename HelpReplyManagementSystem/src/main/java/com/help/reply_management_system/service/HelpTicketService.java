package com.help.reply_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.help.reply_management_system.entity.HelpTicket;
import com.help.reply_management_system.repository.HelpTicketRepository;

@Service
public class HelpTicketService {

    private final HelpTicketRepository repository;

    public HelpTicketService(HelpTicketRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public HelpTicket createTicket(HelpTicket ticket) {
        return repository.save(ticket);
    }

    // GET ALL
    public List<HelpTicket> getAllTickets() {
        return repository.findAll();
    }

    // GET BY ID
    public Optional<HelpTicket> getTicketById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public HelpTicket updateTicket(Long id, HelpTicket ticket) {

        Optional<HelpTicket> existingTicket =
                repository.findById(id);

        if (existingTicket.isPresent()) {

            HelpTicket oldTicket = existingTicket.get();

            oldTicket.setName(ticket.getName());
            oldTicket.setEmail(ticket.getEmail());
            oldTicket.setSubject(ticket.getSubject());
            oldTicket.setMessage(ticket.getMessage());

            return repository.save(oldTicket);
        }

        return null;
    }

    // DELETE
    public void deleteTicket(Long id) {
        repository.deleteById(id);
    }
}