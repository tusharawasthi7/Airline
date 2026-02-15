package com.airline.Airline.controllers;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.CarrierDto;
import com.airline.Airline.entities.Carrier;
import com.airline.Airline.converter.CarrierDtoToCarrierConverter;
import com.airline.Airline.converter.CarrierToCarrierDtoConverter;
import com.airline.Airline.service.CarrierService;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;
import com.airline.Airline.validation.RegisterCarrier;

@RestController
@RequestMapping("/admin/carrier")
public class CarrierController {
	
	
	CarrierService carrierService;
	
	
	CarrierToCarrierDtoConverter carrierToCarrierDtoConverter;
	CarrierDtoToCarrierConverter carrierDtoToCarrierConverter;


	public CarrierController(CarrierService carrierService, CarrierToCarrierDtoConverter carrierToCarrierDtoConverter,
			CarrierDtoToCarrierConverter carrierDtoToCarrierConverter) {
		super();
		this.carrierService = carrierService;
		this.carrierToCarrierDtoConverter = carrierToCarrierDtoConverter;
		this.carrierDtoToCarrierConverter = carrierDtoToCarrierConverter;
	}



	
	
	//Creating and updating a carrier
	
	@PostMapping
	public Result addCarrier(@Validated(RegisterCarrier.class) @RequestBody CarrierDto carrierDto)
	{
		Carrier carrier=this.carrierDtoToCarrierConverter.convert(carrierDto);
		Carrier savedCarrier=this.carrierService.addCarrier(carrier);
		CarrierDto savedCarrierDto=this.carrierToCarrierDtoConverter.convert(savedCarrier);
		return  new Result(true,StatusCode.SUCCESS,"Carrier Added Successfully",savedCarrierDto); 
	}
	
	
	//Fetching a single carrier
	@GetMapping("/{carrierID}")
	public Result getCarrier(@PathVariable Integer carrierID)
	{
		Carrier foundCarrier=this.carrierService.getCarrierById(carrierID);
		return 	new Result(true,StatusCode.SUCCESS,"Found Carrier with Id: "+carrierID,foundCarrier);
	}
	
	
	//Fetching all carrier
	@GetMapping
	public Result getAllCarrier()
	{
		List<Carrier> findAllCarriers=this.carrierService.getAllCarrier();
		List<CarrierDto> allCarrierDtos=findAllCarriers.stream()
				.map((carrier) -> this.carrierToCarrierDtoConverter.convert(carrier))
				.collect(Collectors.toList());	
		return new Result(true,StatusCode.SUCCESS,"Get all Carrier Success", allCarrierDtos);
		
	}
	

	
}

