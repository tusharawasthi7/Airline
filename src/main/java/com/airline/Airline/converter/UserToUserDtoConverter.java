package com.airline.Airline.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.UserDto;
import com.airline.Airline.entities.User;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User user) {
	
		UserDto userDto=new UserDto(user.getUserId(),
				user.getUserName(),
				null,
				user.getRoles(),
				user.getCustomerCategory(),
				user.getPhone(),
				user.getEmailId(),
				user.getAddress1(),
				user.getAddress2(),
				user.getCity(),
				user.getState(),
				user.getCountry(),
				user.getZipCode(),
				user.getDate()
				);
		return userDto;
	}
	
}