package com.busticketingsystem.paymentservice.service;

import com.busticketingsystem.paymentservice.dto.PaymentRequest;
import com.busticketingsystem.paymentservice.dto.PaymentResponse;

public interface PaymentService
{
	void createPayment(PaymentRequest paymentRequest);

	PaymentResponse getPayment(Long id);

	void updatePayment(Long id, PaymentRequest paymentRequest);

	void deletePayment(Long id);

	PaymentResponse getPaymentByBookingId(Long bookingId);

	boolean isBookingPaid(Long bookingId);
}
