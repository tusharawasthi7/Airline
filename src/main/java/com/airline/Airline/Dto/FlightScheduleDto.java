package com.airline.Airline.Dto;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.airline.Airline.entities.Booking;
import com.airline.Airline.validation.FlightScheduleRegister;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record FlightScheduleDto(
		Integer flightScheduleId,
		@NotNull(message="Flight Id is required",groups={FlightScheduleRegister.class})
		Integer flightId,
		

        @DateTimeFormat(pattern="yyyy-MM-dd")
		@NotNull(message="Date is required",  groups={FlightScheduleRegister.class})
		@FutureOrPresent(message="Schedule date should be present or future",groups={FlightScheduleRegister.class})
		LocalDateTime date,

		@NotNull(message="Bussiness class booked discount is required",groups={FlightScheduleRegister.class})
		Integer bussinessClassBookedDiscount,
		
		@NotNull(message="Economy class booked discount is required",groups={FlightScheduleRegister.class})
		Integer economyClassBookedDiscount,
		
		@NotNull(message="Executive class booked discount is required",groups={FlightScheduleRegister.class})
		Integer executiveClassBookedDiscount,
		
		List<Booking> bookings
		){

}
