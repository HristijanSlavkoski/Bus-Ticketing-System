package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.TicketRequest;
import com.busticketingsystem.bookingservice.dto.TicketResponse;
import com.busticketingsystem.bookingservice.model.Ticket;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.model.TicketValidity;
import com.busticketingsystem.bookingservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService
{
	private final TicketRepository ticketRepository;

	@Override
	public void createTicket(TicketRequest ticketRequest)
	{
		Ticket ticket = mapToTicket(ticketRequest);
		ticketRepository.save(ticket);
	}

	@Override
	public List<TicketResponse> getAllTickets()
	{
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream().map(this::mapToTicketResponse).collect(Collectors.toList());
	}

	@Override
	public TicketResponse getTicket(Long id)
	{
		Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
		return mapToTicketResponse(ticket);
	}

	@Override
	public void deleteTicket(Long id)
	{
		ticketRepository.deleteById(id);
	}

	@Override
	public void updateTicket(Long id, TicketRequest ticketRequest)
	{
		Ticket ticket = ticketRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ticket not found"));

		ticket.setBookingId(ticketRequest.getBookingId());
		ticket.setIssueDate(ticketRequest.getIssueDate());
		ticket.setValidity(ticketRequest.getValidity());
		ticket.setType(ticketRequest.getType());
		ticket.setUsageCount(ticketRequest.getUsageCount());

		ticketRepository.save(ticket);
	}


	@Override
	public List<TicketResponse> getTicketsByValidity(TicketValidity validity)
	{
		List<Ticket> tickets = ticketRepository.findByValidity(validity);
		return tickets.stream().map(this::mapToTicketResponse).collect(Collectors.toList());
	}

	@Override
	public List<TicketResponse> getTicketsByType(TicketTypeEnum type)
	{
		List<Ticket> tickets = ticketRepository.findByType(type);
		return tickets.stream().map(this::mapToTicketResponse).collect(Collectors.toList());
	}

	@Override
	public TicketResponse getCheapestTicketByRoute(Long routeId)
	{
		return null;
	}

	private Ticket mapToTicket(TicketRequest ticketRequest)
	{
		return Ticket.builder()
				.bookingId(ticketRequest.getBookingId())
				.issueDate(ticketRequest.getIssueDate())
				.validity(ticketRequest.getValidity())
				.type(ticketRequest.getType())
				.usageCount(ticketRequest.getUsageCount())
				.build();
	}

	private TicketResponse mapToTicketResponse(Ticket ticket)
	{
		return TicketResponse.builder()
				.id(ticket.getId())
				.bookingId(ticket.getBookingId())
				.issueDate(ticket.getIssueDate())
				.validity(ticket.getValidity())
				.type(ticket.getType())
				.usageCount(ticket.getUsageCount())
				.build();
	}
}