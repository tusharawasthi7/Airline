package com.airline.Airline.Dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.airline.Airline.validation.SearchFlight;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record SearchDto(
		@NotNull(message="Origin is required" , groups={SearchFlight.class})
		String origin,
		@NotNull(message="Destination is required", groups= {SearchFlight.class})
		String destination,
		@DateTimeFormat(pattern="yyyy-MM-dd")
		@NotNull(message="Date is required" , groups= {SearchFlight.class})
		@FutureOrPresent(message="Date should be in present or future" , groups= {SearchFlight.class})
		LocalDateTime date) {

}
