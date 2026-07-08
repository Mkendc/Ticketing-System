package com.mkendc.ticketingsystem.service;

import com.mkendc.ticketingsystem.entity.Ticket;
import com.mkendc.ticketingsystem.repository.TicketRepository;
import org.springframework.stereotype.Service;
import com.mkendc.ticketingsystem.dto.CreateTicketRequest;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Optional<Ticket> updateTicket(Long id, CreateTicketRequest request) {

        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setTitle(request.getTitle());
                    ticket.setDescription(request.getDescription());
                    ticket.setStatus(request.getStatus());
                    ticket.setPriority(request.getPriority());

                    return ticketRepository.save(ticket);
                });
    }

    public boolean deleteTicket(Long id) {

        if (!ticketRepository.existsById(id)) {
            return false;
        }

        ticketRepository.deleteById(id);
        return true;
    }
}