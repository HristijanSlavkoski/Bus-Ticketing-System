package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.BookingRequest;
import com.busticketingsystem.bookingservice.dto.BookingResponse;

import java.util.List;

public interface BookingService
{
	void createBooking(BookingRequest bookingRequest);

	BookingResponse getBooking(Long id);

	void updateBooking(Long id, BookingRequest bookingRequest);

	void deleteBooking(Long id);

	List<BookingResponse> getAllBookings();

	List<BookingResponse> getBookingsByUserId(Long userId);

	List<BookingResponse> getBookingsByRouteId(Long routeId);

	boolean isBookingConfirmed(Long id);

	void confirmBooking(Long id);

	void cancelBooking(Long id);
}
