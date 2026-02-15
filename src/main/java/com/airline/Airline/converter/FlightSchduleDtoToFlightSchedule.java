package com.airline.Airline.converter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.FlightScheduleDto;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.entities.FlightSchedule;
import com.airline.Airline.repositories.FlightRepository;

@Component
public class FlightSchduleDtoToFlightSchedule implements Converter<FlightScheduleDto, FlightSchedule> {

	@Autowired
	FlightRepository flightRepository;
	
	@Override
	public FlightSchedule convert(FlightScheduleDto source) {
	
		FlightSchedule  flightSchdule=new FlightSchedule();
		flightSchdule.setFlightScheduleId(source.flightScheduleId());
		
		flightSchdule.setDateOfTravel(source.date());
		flightSchdule.setBusinessClassBookedDiscount(source.bussinessClassBookedDiscount());
		flightSchdule.setEconomyClassBookedDiscount(source.economyClassBookedDiscount());
		flightSchdule.setExecutiveClassBookedDiscount(source.executiveClassBookedDiscount());
		
		
		
		Flight flight=flightRepository.findById(source.flightId()).orElseThrow(() -> new ObjectNotFoundException("Flight", source.flightId()));
		flightSchdule.setFligt(flight);
		
		
		return flightSchdule;
		
		
	}

}