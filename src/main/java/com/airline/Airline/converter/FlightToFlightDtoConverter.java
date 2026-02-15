package com.airline.Airline.converter;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.FlightDto;
import com.airline.Airline.entities.Flight;

@Component
public class FlightToFlightDtoConverter  implements Converter<Flight, FlightDto>{

	@Override
	public FlightDto convert(Flight source) {
	
		FlightDto flightDto=new FlightDto(
				source.getFlightId(),
				source.getCarrier().getCarrierID(),
				source.getOrigin(),
				source.getDestination(),
				source.getAirFare(),
				source.getSeatCapacityEconomyClass(),
				source.getSeatCapacityBusinessClass(),
				source.getSeatCapacityExecutiveClass()
				);
		
		return flightDto;
	}

	
}
