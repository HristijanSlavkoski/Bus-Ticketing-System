package com.busticketingsystem.bookingservice.dto;

import com.busticketingsystem.bookingservice.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
	private Long id;
	private Long userId;
	private Long companyRouteId;
	private Integer seatNumber;
	private LocalDate bookingDate;
	private BookingStatus status;
}
