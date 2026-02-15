package com.airline.Airline.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.DynamicResponseDto;
import com.airline.Airline.Dto.FlightScheduleDto;
import com.airline.Airline.Dto.SearchDto;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.entities.FlightSchedule;
import com.airline.Airline.converter.FlightDtoToFlightConverter;
import com.airline.Airline.converter.FlightSchduleDtoToFlightSchedule;
import com.airline.Airline.converter.FlightSchduleToFlightScheduleDto;
import com.airline.Airline.converter.FlightToFlightDtoConverter;
import com.airline.Airline.repositories.FlightSchduleRepository;
import com.airline.Airline.service.FlightSchduleService;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;
import com.airline.Airline.validation.FlightScheduleRegister;
import com.airline.Airline.validation.SearchFlight;

@RestController
public class FlightScheduleController {

	FlightSchduleService flightSchduleService;

	FlightSchduleToFlightScheduleDto flightSchduleToFlightScheduleDto;

	FlightSchduleDtoToFlightSchedule flightSchduleDtoToFlightSchedule;

	public FlightScheduleController(FlightSchduleService flightSchduleService,
			FlightSchduleToFlightScheduleDto flightSchduleToFlightScheduleDto,
			FlightSchduleDtoToFlightSchedule flightSchduleDtoToFlightSchedule) {
		super();
		this.flightSchduleService = flightSchduleService;
		this.flightSchduleToFlightScheduleDto = flightSchduleToFlightScheduleDto;
		this.flightSchduleDtoToFlightSchedule = flightSchduleDtoToFlightSchedule;
	}

	@PostMapping("/admin/flightSchdule")
	public Result addFlightSchdule(
			@Validated(FlightScheduleRegister.class) @RequestBody FlightScheduleDto flightScheduleDto) {
		FlightSchedule flightSchedule = this.flightSchduleDtoToFlightSchedule.convert(flightScheduleDto);
		FlightSchedule saved = this.flightSchduleService.schduleFlight(flightSchedule);
		FlightScheduleDto savedDto = this.flightSchduleToFlightScheduleDto.convert(saved);
		return new Result(true, StatusCode.SUCCESS, "Flight is scheduled successfully", savedDto);
	}

	@GetMapping("/public/flightSchdule/{flightSchduleId}")
	public Result getFlightSchdule(@PathVariable Integer flightSchduleId) {
		FlightSchedule flightSchdule = this.flightSchduleService.getFlightSchduleById(flightSchduleId);
		FlightScheduleDto flightSchduleDto = this.flightSchduleToFlightScheduleDto.convert(flightSchdule);
		return new Result(true, StatusCode.SUCCESS, "Found the schduled flight", flightSchduleDto);
	}

	@GetMapping("/public/flightSchedule/{date}")
	public Result getFlightsByDate(@PathVariable String date) {
		List<FlightSchedule> flightScheduleByDate = this.flightSchduleService.findByDate(date);
		List<FlightScheduleDto> flightScheduleDtoByDate = flightScheduleByDate.stream()
				.map(x -> flightSchduleToFlightScheduleDto.convert(x)).collect(Collectors.toList());
		return new Result(true, StatusCode.SUCCESS,"All flight Schedule by date are:", flightScheduleDtoByDate);
	}

	@GetMapping("/public/flightSchdule")
	public Result getAllFlightSchdule() {
		List<FlightSchedule> allSchdule = this.flightSchduleService.getAllSchdule();
		List<FlightScheduleDto> allSchduleDto = allSchdule.stream()
				.map((flightSchedule) -> this.flightSchduleToFlightScheduleDto.convert(flightSchedule))
				.collect(Collectors.toList());

		return new Result(true, StatusCode.SUCCESS, "Found all schedule", allSchduleDto);
	}

	@PostMapping("/public/flightSchdule/searchFlight")
	public Result searchFlight(@Validated(SearchFlight.class) @RequestBody SearchDto searchDto, Pageable pageable) {
		Page<FlightSchedule> foundFlightPage = this.flightSchduleService.searchFlight(searchDto, pageable);
		return new Result(true, StatusCode.SUCCESS, "Found flights", foundFlightPage);
	}
	
	@GetMapping("/public/flightSchdule/summary/{date}")
	public Result flightSummary(@PathVariable LocalDateTime date)
	{
		List<DynamicResponseDto> summary=this.flightSchduleService.getSummary(date);
		return new Result(true,StatusCode.SUCCESS,"Here is the summary",summary);
	}

}
