package com.airline.Airline.converter;






import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.CarrierDto;
import com.airline.Airline.entities.Carrier;

@Component
public class CarrierToCarrierDtoConverter implements Converter<Carrier,CarrierDto>{

	@Override
	public CarrierDto convert(Carrier source) {
		
		CarrierDto carrierDto=new CarrierDto(source.getCarrierID(),
				source.getCarrierName(),
				source.getDiscountPercentageThirtyDaysAdvanceBooking(),
				source.getDiscountPercentageSixDaysAdvanceBooking(),
				source.getDiscountPercentageNinteyDaysAdvanceBooking(),
				source.getBulkDiscountBooking(),
				source.getRefundPercentageForTicketCancellation2DaysBeforeTravelDate(),
				source.getRefundPercentageForTicketCancellation10DaysBeforeTravelDate(),
				source.getRefundPercentageForTicketCancellation20DaysBeforeTravelDate(),
				source.getSilverUserDiscount(),
				source.getGoldUserDiscount(),
				source.getPlatinumUserDiscount()
				);
		return carrierDto;
	}
	

}
