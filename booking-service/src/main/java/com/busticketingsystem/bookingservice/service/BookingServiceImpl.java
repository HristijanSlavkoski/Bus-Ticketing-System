package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.BookingRequest;
import com.busticketingsystem.bookingservice.dto.BookingResponse;
import com.busticketingsystem.bookingservice.dto.CompanyRouteResponse;
import com.busticketingsystem.bookingservice.dto.PaymentRequest;
import com.busticketingsystem.bookingservice.dto.PaymentResponse;
import com.busticketingsystem.bookingservice.model.Booking;
import com.busticketingsystem.bookingservice.model.BookingStatus;
import com.busticketingsystem.bookingservice.model.PaymentStatus;
import com.busticketingsystem.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService
{
	private final BookingRepository bookingRepository;
	private final WebClient webClient;

	@Override
	public void createBooking(BookingRequest bookingRequest)
	{
		Booking booking = mapToBooking(bookingRequest);
		try
		{
			CompanyRouteResponse companyRouteResponse = webClient.get()
					.uri("http://localhost:8081/api/company-route/" + bookingRequest.getCompanyRouteId())
					.retrieve()
					.bodyToMono(CompanyRouteResponse.class)
					.block();
			bookingRepository.save(booking);

			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setBookingId(booking.getId());
			paymentRequest.setAmount(companyRouteResponse.getPrice());
			paymentRequest.setPaymentDate(LocalDate.now());
			paymentRequest.setStatus(PaymentStatus.PENDING);

			webClient.post()
					.uri("http://localhost:8083/api/payment")
					.bodyValue(paymentRequest)
					.retrieve()
					.bodyToMono(PaymentResponse.class)
					.block();
		} catch (Exception e)
		{
			throw new IllegalArgumentException("Route not found");
		}
	}

	@Override
	public BookingResponse getBooking(Long id)
	{
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		return mapToBookingResponse(booking);
	}

	@Override
	public void updateBooking(Long id, BookingRequest bookingRequest)
	{
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));

		booking.setUserId(bookingRequest.getUserId());
		booking.setCompanyRouteId(bookingRequest.getCompanyRouteId());
		booking.setSeatNumber(bookingRequest.getSeatNumber());
		booking.setBookingDate(bookingRequest.getBookingDate());
		booking.setStatus(bookingRequest.getStatus());

		bookingRepository.save(booking);
	}

	@Override
	public void deleteBooking(Long id)
	{
		bookingRepository.deleteById(id);
	}

	@Override
	public List<BookingResponse> getAllBookings()
	{
		List<Booking> bookings = bookingRepository.findAll();
		return bookings.stream().map(this::mapToBookingResponse).collect(Collectors.toList());
	}

	@Override
	public List<BookingResponse> getBookingsByUserId(Long userId)
	{
		List<Booking> bookings = bookingRepository.findByUserId(userId);
		return bookings.stream().map(this::mapToBookingResponse).collect(Collectors.toList());
	}

	@Override
	public List<BookingResponse> getBookingsByRouteId(Long routeId)
	{
		List<Booking> bookings = bookingRepository.findByCompanyRouteId(routeId);
		return bookings.stream().map(this::mapToBookingResponse).collect(Collectors.toList());
	}

	@Override
	public boolean isBookingConfirmed(Long id)
	{
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		return booking.getStatus() == BookingStatus.CONFIRMED;
	}

	@Override
	public void confirmBooking(Long id)
	{
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus(BookingStatus.CONFIRMED);
		bookingRepository.save(booking);
	}

	@Override
	public void cancelBooking(Long id)
	{
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus(BookingStatus.CANCELLED);
		bookingRepository.save(booking);
	}

	private Booking mapToBooking(BookingRequest bookingRequest)
	{
		return Booking.builder()
				.userId(bookingRequest.getUserId())
				.companyRouteId(bookingRequest.getCompanyRouteId())
				.seatNumber(bookingRequest.getSeatNumber())
				.bookingDate(bookingRequest.getBookingDate())
				.status(bookingRequest.getStatus())
				.build();
	}

	private BookingResponse mapToBookingResponse(Booking booking)
	{
		return BookingResponse.builder()
				.id(booking.getId())
				.userId(booking.getUserId())
				.companyRouteId(booking.getCompanyRouteId())
				.seatNumber(booking.getSeatNumber())
				.bookingDate(booking.getBookingDate())
				.status(booking.getStatus())
				.build();
	}
}
