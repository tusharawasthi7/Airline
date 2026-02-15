package com.airline.Airline.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.FlightDto;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.entities.Carrier;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.repositories.CarrierRepository;

@Component
public class FlightDtoToFlightConverter implements Converter<FlightDto, Flight> {

	
	@Autowired
	CarrierRepository carrierRepository;
	
	
	@Override
	public Flight convert(FlightDto source) {
		
		Flight flight=new Flight();
	
		
		flight.setFlightId(source.flightId());
		flight.setOrigin(source.origin());
		flight.setDestination(source.destination());
		flight.setAirFare(source.airFare());
		
		flight.setSeatCapacityEconomyClass(source.seatCapacityEconomyClass());
		flight.setSeatCapacityBusinessClass(source.seatCapacityBussinessClass());
		flight.setSeatCapacityExecutiveClass(source.seatCapacityExecutiveClass());
		
		
		
		
		Carrier carrier=this.carrierRepository.findById(source.carrierId()).orElseThrow(() -> new ObjectNotFoundException("carrier", source.carrierId()));
		flight.setCarrier(carrier);
		
		
		
		return flight;
		
	}

}
