package com.airline.Airline.Dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicResponseDto {
	
	
	private final Map<String ,Object> data=new LinkedHashMap<>();
	
	
	public DynamicResponseDto add(String key, Object value)
	{
		data.put(key, value);
		return this;
	}
	
	public Map<String , Object> build()
	{
		return data;
	}

	public Map<String, Object> getData() {
		return data;
	}
	
	
}
