package com.airline.Airline.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.airline.Airline.entities.Carrier;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.repositories.CarrierRepository;

@Service
public class CarrierService {

	CarrierRepository carrierRepository;

	public CarrierService(CarrierRepository carrierRepository) {
		super();
		this.carrierRepository = carrierRepository;
	}

	// Adding a new Carrier
	public Carrier addCarrier(Carrier carrier) {
		Carrier savedCarrier = this.carrierRepository.save(carrier);
		return savedCarrier;
	}

	// Finding a carrier by ID:
	public Carrier getCarrierById(Integer carrierID) {
		Carrier foundCarrier = this.carrierRepository.findById(carrierID)
				.orElseThrow(() -> new ObjectNotFoundException("carrier", carrierID));

		return foundCarrier;
	}

	// Finding all carrier
	public List<Carrier> getAllCarrier() {
		List<Carrier> foundCarriers = this.carrierRepository.findAll();
		return foundCarriers;
	}

	// Updating a carrier
	public Carrier updateCarrier(Carrier carrier) {
		this.carrierRepository.findById(carrier.getCarrierID())
				.orElseThrow(() -> new ObjectNotFoundException("carrier", carrier.getCarrierID()));
		Carrier savedCarrier = this.carrierRepository.save(carrier);
		return savedCarrier;
	}

	// Delete a carrier
	public void deleteCarrier(Integer carrierID) {
		this.carrierRepository.findById(carrierID).orElseThrow(() -> new ObjectNotFoundException("carrier", carrierID));
		// Before deleteing i have to unassign all the flights to it
		this.carrierRepository.deleteById(carrierID);
	}
}

