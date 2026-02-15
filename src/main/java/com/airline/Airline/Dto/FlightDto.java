package com.airline.Airline.Dto;

import com.airline.Airline.validation.RegisterFlight;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record  FlightDto(
		Integer flightId,
		
		@NotNull(message="Carrier Id is required",groups={RegisterFlight.class})
		Integer carrierId,
		
		@NotEmpty(message="Origin is required",groups={RegisterFlight.class})
		String origin,
		
		@NotEmpty(message="Destination is required",groups={RegisterFlight.class})
		String destination,
		
		@NotNull(message="AirFare is required",groups={RegisterFlight.class})
		Integer airFare,
		
		@Min(value=20,message="Seat Capacity for economy class cannot be less than 20.",groups={RegisterFlight.class})
		@NotNull(message="Seat capacity for economy class is required",groups={RegisterFlight.class})
		Integer seatCapacityEconomyClass,
		
		@Min(value=10,message="Seat Capacity for Bussiness class cannot be less than 10.",groups={RegisterFlight.class})
		@NotNull(message="Seat capacity for Bussiness class is required",groups={RegisterFlight.class})
		Integer seatCapacityBussinessClass,
		
		@Min(value=5,message="Seat Capacity for Executive  class cannot be less than 5.",groups={RegisterFlight.class})
		@NotNull(message="Seat capacity for Bussiness class is required",groups={RegisterFlight.class})
		Integer seatCapacityExecutiveClass
)
{
	
}
