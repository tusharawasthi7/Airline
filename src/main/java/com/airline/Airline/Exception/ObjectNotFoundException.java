package com.airline.Airline.Exception;

public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException(String objectName,Integer ID)
	{
		super("Could not found object "+objectName+" with ID: "+ID);
	}

}

