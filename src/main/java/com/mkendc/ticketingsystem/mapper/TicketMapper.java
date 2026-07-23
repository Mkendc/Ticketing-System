package com.mkendc.ticketingsystem.mapper;

import com.mkendc.ticketingsystem.dto.TicketResponse;
import com.mkendc.ticketingsystem.entity.Ticket;

public class TicketMapper {

    private TicketMapper() {
        // Prevent instantiation
    }

    public static TicketResponse toResponse(Ticket ticket) {

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCreatedAt()
        );
    }
}