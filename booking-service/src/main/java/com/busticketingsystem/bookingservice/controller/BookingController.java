package com.busticketingsystem.bookingservice.controller;

import com.busticketingsystem.bookingservice.dto.BookingRequest;
import com.busticketingsystem.bookingservice.dto.BookingResponse;
import com.busticketingsystem.bookingservice.service.BookingService;
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
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController
{
	private final BookingService bookingService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createBooking(@RequestBody BookingRequest bookingRequest)
	{
		bookingService.createBooking(bookingRequest);
	}

	@GetMapping("/{id}")
	public BookingResponse getBooking(@PathVariable Long id)
	{
		return bookingService.getBooking(id);
	}

	@PutMapping("/{id}")
	public void updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest)
	{
		bookingService.updateBooking(id, bookingRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteBooking(@PathVariable Long id)
	{
		bookingService.deleteBooking(id);
	}

	@GetMapping
	public List<BookingResponse> getAllBookings()
	{
		return bookingService.getAllBookings();
	}

	@GetMapping("/user/{userId}")
	public List<BookingResponse> getBookingsByUserId(@PathVariable Long userId)
	{
		return bookingService.getBookingsByUserId(userId);
	}

	@GetMapping("/route/{routeId}")
	public List<BookingResponse> getBookingsByRouteId(@PathVariable Long routeId)
	{
		return bookingService.getBookingsByRouteId(routeId);
	}

	@GetMapping("/{id}/isconfirmed")
	public boolean isBookingConfirmed(@PathVariable Long id)
	{
		return bookingService.isBookingConfirmed(id);
	}

	@PutMapping("/{id}/confirm")
	@ResponseStatus(HttpStatus.OK)
	public void confirmBooking(@PathVariable Long id)
	{
		bookingService.confirmBooking(id);
	}

	@PutMapping("/{id}/cancel")
	@ResponseStatus(HttpStatus.OK)
	public void cancelBooking(@PathVariable Long id)
	{
		bookingService.cancelBooking(id);
	}
}