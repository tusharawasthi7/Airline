package com.airline.Airline.entities;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Carrier implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int carrierID;
	
	String carrierName;
	
	
	Integer discountPercentageThirtyDaysAdvanceBooking;
	Integer discountPercentageSixDaysAdvanceBooking;
	Integer discountPercentageNinteyDaysAdvanceBooking;
	Integer bulkDiscountBooking;
	@Column(name = "refund_cancel_2d_before")
	Integer refundPercentageForTicketCancellation2DaysBeforeTravelDate;
	@Column(name = "refund_cancel_10d_before")
	Integer refundPercentageForTicketCancellation10DaysBeforeTravelDate;
	@Column(name = "refund_cancel_20d_before")
	Integer refundPercentageForTicketCancellation20DaysBeforeTravelDate;
	Integer silverUserDiscount;
	Integer goldUserDiscount;
	Integer platinumUserDiscount;
	
	
	
	public Carrier() {
		super();
	}



	public Carrier(int carrierID, String carrierName, int discountPercentageThirtyDaysAdvanceBooking,
			int discountPercentageSixDaysAdvanceBooking, int discountPercentageNinteyDaysAdvanceBooking,
			int bulkDiscountBooking, int refundPercentageForTicketCancellation2DaysBeforeTravelDate,
			int refundPercentageForTicketCancellation10DaysBeforeTravelDate,
			int refundPercentageForTicketCancellation20DaysBeforeTravelDate, int silverUserDiscount,
			int goldUserDiscount, int platinumUserDiscount) {
		super();
		this.carrierID = carrierID;
		this.carrierName = carrierName;
		this.discountPercentageThirtyDaysAdvanceBooking = discountPercentageThirtyDaysAdvanceBooking;
		this.discountPercentageSixDaysAdvanceBooking = discountPercentageSixDaysAdvanceBooking;
		this.discountPercentageNinteyDaysAdvanceBooking = discountPercentageNinteyDaysAdvanceBooking;
		this.bulkDiscountBooking = bulkDiscountBooking;
		this.refundPercentageForTicketCancellation2DaysBeforeTravelDate = refundPercentageForTicketCancellation2DaysBeforeTravelDate;
		this.refundPercentageForTicketCancellation10DaysBeforeTravelDate = refundPercentageForTicketCancellation10DaysBeforeTravelDate;
		this.refundPercentageForTicketCancellation20DaysBeforeTravelDate = refundPercentageForTicketCancellation20DaysBeforeTravelDate;
		this.silverUserDiscount = silverUserDiscount;
		this.goldUserDiscount = goldUserDiscount;
		this.platinumUserDiscount = platinumUserDiscount;
	}



	
	
	public int getCarrierID() {
		return carrierID;
	}



	public void setCarrierID(int carrierID) {
		this.carrierID = carrierID;
	}



	public String getCarrierName() {
		return carrierName;
	}



	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}



	public int getDiscountPercentageThirtyDaysAdvanceBooking() {
		return discountPercentageThirtyDaysAdvanceBooking;
	}



	public void setDiscountPercentageThirtyDaysAdvanceBooking(int discountPercentageThirtyDaysAdvanceBooking) {
		this.discountPercentageThirtyDaysAdvanceBooking = discountPercentageThirtyDaysAdvanceBooking;
	}



	public int getDiscountPercentageSixDaysAdvanceBooking() {
		return discountPercentageSixDaysAdvanceBooking;
	}



	public void setDiscountPercentageSixDaysAdvanceBooking(int discountPercentageSixDaysAdvanceBooking) {
		this.discountPercentageSixDaysAdvanceBooking = discountPercentageSixDaysAdvanceBooking;
	}



	public int getDiscountPercentageNinteyDaysAdvanceBooking() {
		return discountPercentageNinteyDaysAdvanceBooking;
	}



	public void setDiscountPercentageNinteyDaysAdvanceBooking(int discountPercentageNinteyDaysAdvanceBooking) {
		this.discountPercentageNinteyDaysAdvanceBooking = discountPercentageNinteyDaysAdvanceBooking;
	}



	public int getBulkDiscountBooking() {
		return bulkDiscountBooking;
	}



	public void setBulkDiscountBooking(int bulkDiscountBooking) {
		this.bulkDiscountBooking = bulkDiscountBooking;
	}



	public int getRefundPercentageForTicketCancellation2DaysBeforeTravelDate() {
		return refundPercentageForTicketCancellation2DaysBeforeTravelDate;
	}



	public void setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(
			int refundPercentageForTicketCancellation2DaysBeforeTravelDate) {
		this.refundPercentageForTicketCancellation2DaysBeforeTravelDate = refundPercentageForTicketCancellation2DaysBeforeTravelDate;
	}



	public int getRefundPercentageForTicketCancellation10DaysBeforeTravelDate() {
		return refundPercentageForTicketCancellation10DaysBeforeTravelDate;
	}



	public void setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(
			int refundPercentageForTicketCancellation10DaysBeforeTravelDate) {
		this.refundPercentageForTicketCancellation10DaysBeforeTravelDate = refundPercentageForTicketCancellation10DaysBeforeTravelDate;
	}



	public int getRefundPercentageForTicketCancellation20DaysBeforeTravelDate() {
		return refundPercentageForTicketCancellation20DaysBeforeTravelDate;
	}



	public void setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(
			int refundPercentageForTicketCancellation20DaysBeforeTravelDate) {
		this.refundPercentageForTicketCancellation20DaysBeforeTravelDate = refundPercentageForTicketCancellation20DaysBeforeTravelDate;
	}



	public int getSilverUserDiscount() {
		return silverUserDiscount;
	}



	public void setSilverUserDiscount(int silverUserDiscount) {
		this.silverUserDiscount = silverUserDiscount;
	}



	public int getGoldUserDiscount() {
		return goldUserDiscount;
	}



	public void setGoldUserDiscount(int goldUserDiscount) {
		this.goldUserDiscount = goldUserDiscount;
	}



	public int getPlatinumUserDiscount() {
		return platinumUserDiscount;
	}



	public void setPlatinumUserDiscount(int platinumUserDiscount) {
		this.platinumUserDiscount = platinumUserDiscount;
	}
	
	
	
	
}
