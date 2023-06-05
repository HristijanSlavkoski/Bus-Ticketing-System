package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.TicketRequest;
import com.busticketingsystem.bookingservice.dto.TicketResponse;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.model.TicketValidity;

import java.util.List;

public interface TicketService
{
	void createTicket(TicketRequest ticketRequest);

	List<TicketResponse> getAllTickets();

	TicketResponse getTicket(Long id);

	void deleteTicket(Long id);

	void updateTicket(Long id, TicketRequest ticketRequest);

	List<TicketResponse> getTicketsByValidity(TicketValidity validity);

	List<TicketResponse> getTicketsByType(TicketTypeEnum type);

	TicketResponse getCheapestTicketByRoute(Long routeId);
}
