package com.busticketingsystem.paymentservice.repository;

import com.busticketingsystem.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{
	Optional<Payment> findByBookingId(Long bookingId);
}
