package com.airline.Airline.security;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.airline.Airline.service.CustomUserDetailServiceImplementation;

@Configuration
public class SecurityConfig  {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	public SecurityConfig(CustomUserDetailServiceImplementation userDeatailService) {
		super();
		this.userDetailService = userDeatailService;
	}

	
	@Autowired
	CustomUserDetailServiceImplementation userDetailService;
	
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .cors(cors -> cors.configurationSource(request -> {
	            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
	            corsConfig.setAllowedOrigins(List.of("http://localhost:3000")); // ✅ your frontend origin
	            corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	            corsConfig.setAllowedHeaders(List.of("*"));
	            corsConfig.setAllowCredentials(true); // Optional if you're using cookies/auth headers
	            return corsConfig;
	        }))
	        .authorizeHttpRequests((auth) -> auth
	            .requestMatchers("/public/**", "/h2-console/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("Admin")
	            .requestMatchers("/user/**").hasRole("User")
	            .anyRequest().authenticated()
	        )
	        .headers(headers -> headers.frameOptions().sameOrigin())
	        .csrf(csrf -> csrf.disable());

	    http
	        .sessionManagement(httpSecuritySessionManagementConfigurer ->
	            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider())
	        .httpBasic();

	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	
	@Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	 DaoAuthenticationProvider authenticationProvider()
	{
		
		
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	

}