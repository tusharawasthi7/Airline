package com.airline.Airline.converter;




import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.UserDto;
import com.airline.Airline.entities.User;

@Component
public class UserDtoToUserConverter implements Converter<UserDto,User>{

	@Override
	public User convert(UserDto userDto) {
		User user=new User();
		user.setUserName(userDto.userName());
		user.setPassword(userDto.password());
		user.setRoles(userDto.roles());
		user.setCustomerCategory(userDto.customerCategory());
		user.setPhone(userDto.phone());
		user.setEmailId(userDto.emailId());
		user.setAddress1(userDto.address1());
		user.setAddress2(userDto.address2());
		user.setCity(userDto.city());
		user.setState(userDto.state());
		user.setCountry(userDto.country());
		user.setZipCode(userDto.zipCode());
		user.setDate(userDto.date());
		return user;
	}
}

