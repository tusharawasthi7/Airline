package com.airline.Airline.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.airline.Airline.entities.Carrier;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.entities.FlightSchedule;
import com.airline.Airline.Dto.DynamicResponseDto;
import com.airline.Airline.Dto.SearchDto;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.repositories.FlightRepository;
import com.airline.Airline.repositories.FlightSchduleRepository;

@Service
public class FlightSchduleService {

	FlightSchduleRepository flightSchduleRepository;
	FlightRepository flightRepository;

	public FlightSchduleService(FlightSchduleRepository flightSchduleRepository, FlightRepository flightRepository) {
		super();
		this.flightSchduleRepository = flightSchduleRepository;
		this.flightRepository = flightRepository;
	}

	public FlightSchedule schduleFlight(FlightSchedule flightSchedule) {
		FlightSchedule saved = this.flightSchduleRepository.save(flightSchedule);
		return saved;
	}

	public FlightSchedule getFlightSchduleById(Integer flightSchduleId) {

		FlightSchedule foundSchedule = this.flightSchduleRepository.findById(flightSchduleId)
				.orElseThrow(() -> new ObjectNotFoundException("Flight Schedule", flightSchduleId));
		return foundSchedule;

	}

	public List<FlightSchedule> getAllSchdule() {

		List<FlightSchedule> allSchedule = this.flightSchduleRepository.findAll();
		return allSchedule;
	}

	public void deleteFlightSchduleById(Integer schduleFlightId) {

		this.flightSchduleRepository.findById(schduleFlightId)
				.orElseThrow(() -> new ObjectNotFoundException("flight schdule", schduleFlightId));
		this.flightSchduleRepository.deleteById(schduleFlightId);

	}

	public Page<FlightSchedule> searchFlight(SearchDto searchDto,Pageable pageable) {
		Page<Flight> flights = this.flightRepository.findByOriginContainingAndDestinationContaining(searchDto.origin(),
				searchDto.destination(),pageable);
		List<FlightSchedule> schedule = this.flightSchduleRepository.findAll();
		List<FlightSchedule> ans = new ArrayList<>();
		for (FlightSchedule s : schedule) {
			for (Flight f : flights) {
			  System.out.println("ABCD            :"+s.getFlight().getFlightId()+"          "+f.getFlightId()+"         "+s.getDateOfTravel()+"          "+searchDto.date());
				if (s.getFlight().getFlightId() == f.getFlightId() && s.getDateOfTravel() == searchDto.date()) {
					ans.add(s);
				}
			}
		}
		return new PageImpl(ans) ;
	}

	public List<FlightSchedule> findByDate(String date) {
		LocalDateTime newDate=LocalDateTime.parse(date);
		return this.flightSchduleRepository.findByDateOfTravel(newDate);
	}

	public List<DynamicResponseDto> getSummary(LocalDateTime date) {
		List<FlightSchedule> allFlightScheduleByDate=this.flightSchduleRepository.findByDateOfTravel(date);
		List<DynamicResponseDto> summary=new ArrayList<>();
		for(FlightSchedule flightSchedule:allFlightScheduleByDate)
		{
			
			
			
			DynamicResponseDto thisSummary=new DynamicResponseDto(); 
			Flight flight=flightSchedule.getFlight();
			Carrier carrier=flight.getCarrier();
			
			
			Integer totalPassenger=flightSchedule.getBooking().stream().map((x) -> x.getNoOfSeats()).reduce(0,(a,b) -> a+b);
			
			thisSummary.add("Flight Schedule ID:", flightSchedule.getFlightScheduleId())
			.add("Carrier Name", carrier.getCarrierName())
			.add("Source", flight.getOrigin())
			.add("destination", flight.getDestination())
			.add("time", flightSchedule.getDateOfTravel())
			.add("total Passengers", totalPassenger)
			.build();
			
			summary.add(thisSummary);
		}
		
		return summary;
		
	}

}
