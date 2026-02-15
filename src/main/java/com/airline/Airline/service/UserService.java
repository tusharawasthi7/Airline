package com.airline.Airline.service;





import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.airline.Airline.entities.User;
import com.airline.Airline.Exception.ObjectAlreadyExistsException;
import com.airline.Airline.repositories.UserRepository;

@Service
public class UserService {

	
	 BCryptPasswordEncoder bcrptyBCryptPasswordEncoder;
	private UserRepository userRepository;
	
	

	public UserService(BCryptPasswordEncoder bcrptyBCryptPasswordEncoder, UserRepository userRepository) {
		super();
		this.bcrptyBCryptPasswordEncoder = bcrptyBCryptPasswordEncoder;
		this.userRepository = userRepository;
	}



	public User addUser(User user) {

		// checking if user already exists
		Optional<User> existingUser = this.userRepository.findByEmailId(user.getEmailId());

		if (!existingUser.isEmpty())
			throw new ObjectAlreadyExistsException("User with email " + user.getEmailId() + " already exits");

		user.setPassword(bcrptyBCryptPasswordEncoder.encode(user.getPassword()));
		User myuser = this.userRepository.save(user);
		System.out.print(user);
		return myuser;
	}

}