package com.airline.Airline.Dto;


import com.airline.Airline.validation.RegisterCarrier;
import com.airline.Airline.validation.RegisterUser;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CarrierDto(int carrierId ,
		
		@NotEmpty(message="Carrier name is required", groups= {RegisterCarrier.class})
		String carrierName,
		
		
		@NotNull(message="Discount % for 30 days advance booking is required" , groups={RegisterCarrier.class})
		Integer discountPercentageThirtyDaysAdvanceBooking,
		@NotNull(message="Discount % for 6 days advance booking is required" , groups={RegisterCarrier.class})
		Integer discountPercentageSixDaysAdvanceBooking,
		@NotNull(message="Discount % for 90 days advance booking is required" , groups={RegisterCarrier.class})
		Integer discountPercentageNinteyDaysAdvanceBooking,
		@NotNull(message="Bulk discount booking is required" , groups={RegisterCarrier.class})
		Integer bulkDiscountBooking,
		@NotNull(message="Refund % for ticket cancellation 2 days before travel date is required" , groups={RegisterCarrier.class})
		Integer refundPercentageForTicketCancellation2DaysBeforeTravelDate,
		@NotNull(message="Refund % for ticket cancellation 10 days before travel date is required" , groups={RegisterCarrier.class})
		Integer refundPercentageForTicketCancellation10DaysBeforeTravelDate,
		@NotNull(message="Refund % for ticket cancellation 20 days before travel date is required" , groups={RegisterCarrier.class})
		Integer refundPercentageForTicketCancellation20DaysBeforeTravelDate,
		@NotNull(message="Silver user discount is required" , groups={RegisterCarrier.class})
		Integer silverUserDiscount,
		@NotNull(message="Gold user discount is required" , groups={RegisterCarrier.class})
		Integer goldUserDiscount,
		@NotNull(message="Platinum user discount is required" , groups={RegisterCarrier.class})
		Integer platinumUserDiscount
		
		) {


}


