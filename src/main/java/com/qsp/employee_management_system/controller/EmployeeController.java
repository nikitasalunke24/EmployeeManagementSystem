package com.qsp.employee_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.employee_management_system.dao.EmployeeDao;
import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.repo.EmployeeRepo;
import com.qsp.employee_management_system.service.EmployeeService;
import com.qsp.employee_management_system.util.ResponseStructure;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Autowired
	private EmployeeDao dao;

	@PostMapping
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@PostMapping("/saveall")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(@RequestBody List<Employee> employees) {
		return service.saveAllEmployee(employees);
	}

	@GetMapping("/find/id")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(@RequestParam int id) {
		return service.findEmployeeById(id);
	}

	@GetMapping("/find/phone")
	public ResponseEntity<ResponseStructure<Employee>> findByPhone(@RequestParam long phone) {
		return service.findByPhone(phone);
	}

	@GetMapping("/find/email")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(@RequestParam String email) {
		return service.findByEmail(email);
	}

	@GetMapping("/find/address")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(@RequestParam String address) {
		return service.findByAddress(address);
	}

	@GetMapping("/find/designation")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByDesignation(@RequestParam String designation) {
		return service.findByDesignation(designation);
	}

	@GetMapping("/find/name")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(@RequestParam String name) {
		return service.findByName(name);
	}

	@GetMapping("/find/all")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		return service.findAll();
	}

	@GetMapping("/find/salary/less")
	public ResponseEntity<ResponseStructure<List<Employee>>> salaryLessThan(@RequestParam double salary) {
		return service.salaryLessThan(salary);
	}

	@GetMapping("/find/salary/greater")
	public ResponseEntity<ResponseStructure<List<Employee>>> salaryGreaterThan(@RequestParam double salary) {
		return service.salaryGreaterThan(salary);
	}

	@GetMapping("/find/salary/between")
	public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(@RequestParam double salary1,
			@RequestParam double salary2) {
		return service.salaryBetween(salary1, salary2);
	}

	@PutMapping("/updateall")
	public ResponseEntity<ResponseStructure<Employee>> updateAll(@RequestParam int id, @RequestBody Employee e) {
		return service.updateAll(id, e);

	}

	@PatchMapping("/update/email")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeEmail(@RequestParam int id,
			@RequestBody String email) {
		return service.updateEmail(id, email);

	}

	@PatchMapping("/update/name")
	public ResponseEntity<ResponseStructure<Employee>> updateName(@RequestParam int id, @RequestBody String name) {
		return service.updateName(id, name);
	}

	@PatchMapping("/update/address")
	public ResponseEntity<ResponseStructure<Employee>> updateAddress(@RequestParam int id,
			@RequestBody String address) {
		return service.updateAddress(id, address);
	}

	@PatchMapping("/update/salary")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeSalary(@RequestParam int id,
			@RequestBody double salary) {
		return service.updateSalary(id, salary);
	}

	@PatchMapping("/update/phone")
	public ResponseEntity<ResponseStructure<Employee>> updatePhone(@RequestParam int id, @RequestBody long phone) {
		return service.updatePhone(id, phone);
	}

	@PatchMapping("/update/designation")
	public ResponseEntity<ResponseStructure<Employee>> updateDesignation(@RequestParam int id,
			@RequestBody String designation) {
		return service.updateDesignation(id, designation);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@RequestParam int id) {
		return service.deleteEmployee(id);
	}

	@DeleteMapping("/delete/phone")
	public ResponseEntity<ResponseStructure<Employee>> deletePhone(@RequestParam long phone) {

		return service.deleteEmployee(phone);
	}

	@DeleteMapping("/delete/name")
	public ResponseEntity<ResponseStructure<List<Employee>>> deleteEmployeeByName(@RequestParam String name) {

		return service.deleteAllEmployeeByName(name);
	}

	@DeleteMapping("/delete/email")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeByEmail(@RequestParam String email) {

		return service.deleteEmployee(email);
	}

	@DeleteMapping("/delete/address")
	public ResponseEntity<ResponseStructure<List<Employee>>> deleteEmployeeByAddress(@RequestParam String address) {

		return service.deleteAllEmployeeByAddress(address);
	}

	@DeleteMapping("/delete/designation")
	public ResponseEntity<ResponseStructure<List<Employee>>> deleteEmployeeByDesignation(
			@RequestParam String designation) {

		return service.deleteAllEmployeeByDesignation(designation);
	}

	@DeleteMapping("/delete/salary")
	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployeeBySalary(@RequestParam double salary) {

		return service.deleteAllEmployeeBySalary(salary);
	}

	@DeleteMapping("/deleteall")
	public void deleteAllEmployee() {

		service.deleteAllEmployee();
	}

}
