package com.airline.Airline.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.CarrierDto;
import com.airline.Airline.Dto.FlightDto;
import com.airline.Airline.Dto.UserDto;
import com.airline.Airline.entities.Carrier;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.entities.User;
import com.airline.Airline.converter.FlightDtoToFlightConverter;
import com.airline.Airline.converter.FlightToFlightDtoConverter;
import com.airline.Airline.service.FlightService;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;
import com.airline.Airline.validation.RegisterCarrier;
import com.airline.Airline.validation.RegisterFlight;
import com.airline.Airline.validation.RegisterUser;


@RestController
@RequestMapping("/admin/flight")
public class FlightController {
	
	
	FlightService flightService;
	FlightDtoToFlightConverter flightDtoToFlightConverter;
	FlightToFlightDtoConverter flightToFlightDtoConverter;
	
	
	

	
	
	
	public FlightController(FlightService flightService, FlightDtoToFlightConverter flightDtoToFlightConverter,
			FlightToFlightDtoConverter flightToFlightDtoConverter) {
		super();
		this.flightService = flightService;
		this.flightDtoToFlightConverter = flightDtoToFlightConverter;
		this.flightToFlightDtoConverter = flightToFlightDtoConverter;
	}





	@PostMapping
	public Result addFlight(@Validated(RegisterFlight.class) @RequestBody FlightDto flightDto)
	{
		Flight flight=this.flightDtoToFlightConverter.convert(flightDto);
		Flight savedFlight=this.flightService.addFight(flight);
		FlightDto savedFlightDto=this.flightToFlightDtoConverter.convert(savedFlight);
		return new Result(true,StatusCode.SUCCESS,"Flight Added Successfully",savedFlightDto);
		
	}
	
	
	@GetMapping("/{flightId}")
	public Result getFlight(@PathVariable Integer flightId)
	{
	  Flight foundFlight=this.flightService.getFlightById(flightId);
	  FlightDto foundFlightDto=this.flightToFlightDtoConverter.convert(foundFlight);
	  return new Result(true,StatusCode.SUCCESS,"Found Flight",foundFlightDto);
		
	}
	
	
	
	@GetMapping
	public Result getAllFlight()
	{
      List<Flight> foundAllFlight=this.flightService.getAllFlight();
      List<FlightDto>  foundAllFlightDto=foundAllFlight.stream()
    		  .map((flight) -> this.flightToFlightDtoConverter.convert(flight))
    		  .collect(Collectors.toList());
	 
	  return new Result(true,StatusCode.SUCCESS,"Found Flight",foundAllFlightDto);
		
	}
	
	

	
}



