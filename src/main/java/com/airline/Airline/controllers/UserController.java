package com.airline.Airline.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.UserDto;
import com.airline.Airline.entities.User;
import com.airline.Airline.converter.UserDtoToUserConverter;
import com.airline.Airline.converter.UserToUserDtoConverter;
import com.airline.Airline.service.UserService;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;
import com.airline.Airline.validation.RegisterUser;

@RestController
@RequestMapping("/public/registerUser")
public class UserController {
	
	
	
	
	public UserController(UserService userService,UserDtoToUserConverter userDtoToUserConverter,UserToUserDtoConverter userToUserDtoConverter) {
		super();
		this.userService = userService;
		this.userDtoToUserConverter=userDtoToUserConverter;
		this.userToUserDtoConverter=userToUserDtoConverter;
	}


	private UserService userService;
	private UserDtoToUserConverter userDtoToUserConverter;
	private UserToUserDtoConverter userToUserDtoConverter;
	

	@PostMapping
	public Result registerUser(@Validated(RegisterUser.class) @RequestBody UserDto userDto)
	{
		System.out.println("Reached user controller");
		User user=userDtoToUserConverter.convert(userDto);
		User savedUser=userService.addUser(user);
		UserDto savedUserDto=userToUserDtoConverter.convert(savedUser);
		return new Result(true,StatusCode.SUCCESS," User Registered Successfully ",savedUserDto);
	}
	
	
	
}

