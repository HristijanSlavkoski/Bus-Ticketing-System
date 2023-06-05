package com.busticketingsystem.bookingservice.controller;

import com.busticketingsystem.bookingservice.dto.TicketRequest;
import com.busticketingsystem.bookingservice.dto.TicketResponse;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.model.TicketValidity;
import com.busticketingsystem.bookingservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController
{
	private final TicketService ticketService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTicket(@RequestBody TicketRequest ticketRequest)
	{
		ticketService.createTicket(ticketRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TicketResponse> getAllTickets()
	{
		return ticketService.getAllTickets();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TicketResponse getTicket(@PathVariable Long id)
	{
		return ticketService.getTicket(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTicket(@PathVariable Long id)
	{
		ticketService.deleteTicket(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateTicket(@PathVariable Long id, @RequestBody TicketRequest ticketRequest)
	{
		ticketService.updateTicket(id, ticketRequest);
	}

	@GetMapping("/validity/{validity}")
	@ResponseStatus(HttpStatus.OK)
	public List<TicketResponse> getTicketsByValidity(@PathVariable TicketValidity validity)
	{
		return ticketService.getTicketsByValidity(validity);
	}

	@GetMapping("/type/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<TicketResponse> getTicketsByType(@PathVariable TicketTypeEnum type)
	{
		return ticketService.getTicketsByType(type);
	}

	@GetMapping("/{routeId}/cheapest")
	@ResponseStatus(HttpStatus.OK)
	public TicketResponse getCheapestTicketByRoute(@PathVariable Long routeId)
	{
		return ticketService.getCheapestTicketByRoute(routeId);
	}

}
