package com.busticketingsystem.bookingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@JdbcTypeCode(SqlTypes.BIGINT)
	private Long id;

	private Long bookingId;

	private LocalDate issueDate;

	@Enumerated(EnumType.STRING)
	private TicketValidity validity;

	@Enumerated(EnumType.STRING)
	private TicketTypeEnum type;

	private Integer usageCount;
}
