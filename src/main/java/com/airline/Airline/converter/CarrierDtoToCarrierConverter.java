package com.airline.Airline.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.CarrierDto;
import com.airline.Airline.entities.Carrier;

@Component
public class CarrierDtoToCarrierConverter implements Converter<CarrierDto, Carrier> {	
	@Override
	public Carrier convert(CarrierDto carrierDto) {
		Carrier carrier=new Carrier();
		
		carrier.setCarrierID(carrierDto.carrierId());
		carrier.setCarrierName(carrierDto.carrierName());
		
		carrier.setDiscountPercentageThirtyDaysAdvanceBooking(carrierDto.discountPercentageThirtyDaysAdvanceBooking());
		carrier.setDiscountPercentageSixDaysAdvanceBooking(carrierDto.discountPercentageSixDaysAdvanceBooking());
		carrier.setDiscountPercentageNinteyDaysAdvanceBooking(carrierDto.discountPercentageNinteyDaysAdvanceBooking());
		
		carrier.setBulkDiscountBooking(carrierDto.bulkDiscountBooking());
		
		carrier.setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(carrierDto.refundPercentageForTicketCancellation2DaysBeforeTravelDate());
		carrier.setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(carrierDto.refundPercentageForTicketCancellation10DaysBeforeTravelDate());
		carrier.setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(carrierDto.refundPercentageForTicketCancellation20DaysBeforeTravelDate());
		
		carrier.setSilverUserDiscount(carrierDto.silverUserDiscount());
		carrier.setGoldUserDiscount(carrierDto.goldUserDiscount());
		carrier.setPlatinumUserDiscount(carrierDto.platinumUserDiscount());
		
		return carrier;
	}
	
	

}

