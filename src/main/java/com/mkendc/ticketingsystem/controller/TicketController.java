package com.mkendc.ticketingsystem.controller;

import com.mkendc.ticketingsystem.dto.CreateTicketRequest;
import com.mkendc.ticketingsystem.dto.TicketResponse;
import com.mkendc.ticketingsystem.entity.Ticket;
import com.mkendc.ticketingsystem.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mkendc.ticketingsystem.mapper.TicketMapper;


import java.util.List;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody CreateTicketRequest request) {

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());

        Ticket savedTicket = ticketService.createTicket(ticket);

        TicketResponse response = TicketMapper.toResponse(savedTicket);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {

        List<TicketResponse> tickets = ticketService.getAllTickets()
                .stream()
                .map(TicketMapper::toResponse)
                .toList();

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id)  {

        return ticketService.getTicketById(id)
                .map(TicketMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody CreateTicketRequest request) {

        return ticketService.updateTicket(id, request)
                .map(TicketMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {

        boolean deleted = ticketService.deleteTicket(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


}