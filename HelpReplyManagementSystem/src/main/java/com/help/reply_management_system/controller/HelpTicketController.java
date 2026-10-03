package com.help.reply_management_system.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.help.reply_management_system.entity.HelpTicket;
import com.help.reply_management_system.service.HelpTicketService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
public class HelpTicketController {

    private final HelpTicketService service;

    public HelpTicketController(HelpTicketService service) {
        this.service = service;
    }

    // CREATE TICKET
    @PostMapping
    public ResponseEntity<HelpTicket> createTicket(
            @RequestBody HelpTicket ticket) {

        HelpTicket savedTicket = service.createTicket(ticket);

        return ResponseEntity.ok(savedTicket);
    }

    // GET ALL TICKETS
    @GetMapping
    public ResponseEntity<List<HelpTicket>> getAllTickets() {

        return ResponseEntity.ok(service.getAllTickets());
    }

    // GET TICKET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<HelpTicket> getTicketById(
            @PathVariable Long id) {

        Optional<HelpTicket> ticket =
                service.getTicketById(id);

        if (ticket.isPresent()) {
            return ResponseEntity.ok(ticket.get());
        }

        return ResponseEntity.notFound().build();
    }

    // UPDATE TICKET
    @PutMapping("/{id}")
    public ResponseEntity<HelpTicket> updateTicket(
            @PathVariable Long id,
            @RequestBody HelpTicket ticket) {

        HelpTicket updatedTicket =
                service.updateTicket(id, ticket);

        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE TICKET
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(
            @PathVariable Long id) {

        Optional<HelpTicket> ticket =
                service.getTicketById(id);

        if (ticket.isPresent()) {

            service.deleteTicket(id);

            return ResponseEntity.ok(
                    "Help ticket deleted successfully"
            );
        }

        return ResponseEntity.notFound().build();
    }
}