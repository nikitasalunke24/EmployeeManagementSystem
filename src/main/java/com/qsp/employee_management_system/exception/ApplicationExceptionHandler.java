package com.qsp.employee_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.employee_management_system.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Id not found");
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmailNotFoundException1(EmailNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("EMail not found");
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNameNotFoundException(NameNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Name not found");
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PhoneNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhoneNotFoundException(PhoneNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Phone not found");
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}
}
