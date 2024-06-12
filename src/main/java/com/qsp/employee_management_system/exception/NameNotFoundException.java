package com.qsp.employee_management_system.exception;

public class NameNotFoundException extends RuntimeException {

	private String message;

	public NameNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
