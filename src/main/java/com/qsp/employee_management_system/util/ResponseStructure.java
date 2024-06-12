package com.qsp.employee_management_system.util;

import java.util.List;

import com.qsp.employee_management_system.dto.Employee;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private int status;
	private String message;
	private T data;

}
