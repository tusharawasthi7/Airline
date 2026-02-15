package com.airline.Airline.service;



import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airline.Airline.entities.User;
import com.airline.Airline.repositories.UserRepository;

//This class is used for authentication purpose i.e to tell spring security how to load user from database

@Service
public class CustomUserDetailServiceImplementation implements UserDetailsService {
	
	
	public CustomUserDetailServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	UserRepository userRepository;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		System.out.println(username);
		
		Optional<User> user=userRepository.findByEmailId(username);
		
		System.out.println(user.get().getRoles());
		
		
		if(user.isPresent())
		{
			//This is the User which spring security is expecting 
			UserDetails userDetail=org.springframework.security.core.userdetails.User
			.builder()
			.username(user.get().getEmailId())
			.password(user.get().getPassword())
			.authorities(user.get().getRoles())
			.build();
			
			System.out.println(user.get().getPassword());
			
			return userDetail;
		}
		
	  throw new UsernameNotFoundException("User not found wih email: "+username);
	}

}


