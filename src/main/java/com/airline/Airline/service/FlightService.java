package com.airline.Airline.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.airline.Airline.entities.Flight;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.repositories.FlightRepository;


@Service
public class FlightService {
	
	FlightRepository flightRepository;
	
	
	
	public FlightService(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}



	public Flight addFight(Flight flight)
	{
		Flight savedFlight=flightRepository.save(flight);
		return savedFlight;
	}



	public Flight getFlightById(Integer flightId) {
		
		
		return this.flightRepository.findById(flightId)
				.orElseThrow(()-> new ObjectNotFoundException("Flight", flightId));
		
	}




	public List<Flight> getAllFlight() {
		
		return this.flightRepository.findAll();
	}
	
	


	public void deleteFlightById(Integer flightId) {
		this.flightRepository.findById(flightId)
		.orElseThrow(() -> new ObjectNotFoundException("Flight",flightId));
		
		//handle delete more properly
	  this.flightRepository.deleteById(flightId);
	}




}


