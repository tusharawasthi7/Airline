package com.airline.Airline.Exception;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;


@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Result handleValidationException(MethodArgumentNotValidException ex)
	{
		List<ObjectError> errors=ex.getBindingResult().getAllErrors();
		
		Map<String,String> map=new HashMap<>(errors.size());
		
		
		errors.forEach((error) ->{
		String key= ((FieldError)error).getField();
		String message=error.getDefaultMessage();
		map.put(key, message);
		});
		return new Result(false,StatusCode.BAD_REQUEST,"Provided Arguments are invalid , see data for details.",map);
		
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Result handleObjectNotFoundExceptionResult(ObjectNotFoundException ex)
	{
		return new Result(false,StatusCode.NOT_FOUND,ex.getMessage());
	}
	
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	Result handleUserAlreadyExistsException(ObjectAlreadyExistsException ex)
	{
		return new Result(false,StatusCode.CONFLICT,ex.getMessage(),null);
	}
	
	@ExceptionHandler(NoSeatAvailableException.class)
	Result handleNoSeatAvailableException(NoSeatAvailableException ex)
	{
		return new Result(false,StatusCode.NOT_FOUND,ex.getMessage());
	}

}
