package com.airline.Airline.Dto;

import java.sql.Date;

import com.airline.Airline.validation.RegisterUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDto( int userId,
@NotEmpty(message="UserName is required", groups= {RegisterUser.class})
String userName,


@NotEmpty(message="Password is required", groups= {RegisterUser.class})
@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
String password,

@NotEmpty(message="Role is required", groups= {RegisterUser.class})
 String roles,
 
 @NotEmpty(message="Customer Category is required", groups= {RegisterUser.class})
String customerCategory,

@NotNull(message="Phone number is required", groups= {RegisterUser.class})
 Integer phone,

@NotEmpty(message="EmailId is required", groups= {RegisterUser.class})
 String emailId,

@NotEmpty(message="Address1 is required", groups= {RegisterUser.class})
String address1,

@NotEmpty(message="Address2  is required", groups= {RegisterUser.class})
 String address2,


@NotEmpty(message="City is required", groups= {RegisterUser.class})
 String city,

@NotEmpty(message="State is required", groups= {RegisterUser.class})
 String state,

@NotEmpty(message="Country is required", groups= {RegisterUser.class})
 String country,

@NotNull(message="ZipCode is required", groups= {RegisterUser.class})
 Integer zipCode,

@NotNull(message="Date is required", groups= {RegisterUser.class})
 Date date) {


}