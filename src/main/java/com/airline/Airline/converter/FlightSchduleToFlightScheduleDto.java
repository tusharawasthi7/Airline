package com.airline.Airline.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.FlightScheduleDto;
import com.airline.Airline.entities.FlightSchedule;

@Component
public class FlightSchduleToFlightScheduleDto implements Converter<FlightSchedule, FlightScheduleDto> {
	@Override
	public FlightScheduleDto convert(FlightSchedule source) {
	
		FlightScheduleDto  flightSchduleDto=new FlightScheduleDto(
				source.getFlightScheduleId(),source.getFlight().getFlightId(),source.getDateOfTravel(),source.getBusinessClassBookedDiscount(),source.getEconomyClassBookedDiscount(),source.getExecutiveClassBookedDiscount(),source.getBooking());
		
		return flightSchduleDto;	
		
	}

}
