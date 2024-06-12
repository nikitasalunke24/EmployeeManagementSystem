package com.qsp.employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	public List<Employee> saveAllEmployee(List<Employee> employees) {
		return repo.saveAll(employees);
	}

	public Employee findEmployeeById(int id) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Employee findByPhone(long phone) {
		return repo.findByPhone(phone);
	}

	public Employee findByEmail(String email) {
		return repo.getByEmail(email);
	}

	public List<Employee> findByAddress(String address) {
		return repo.findByAddress(address);
	}

	public List<Employee> findByDesignation(String designation) {
		return repo.findByDesignation(designation);
	}

	public List<Employee> findByName(String name) {
		return repo.findByName(name);
	}

	public List<Employee> findAll() {
		return repo.findAll();
	}

	public List<Employee> salaryLessThan(double salary) {
		return repo.findBySalaryLessThan(salary);
	}

	public List<Employee> salaryGreaterThan(double salary) {
		return repo.findBySalaryGreaterThan(salary);
	}

	public List<Employee> salaryBetween(double salary1, double salary2) {
		return repo.findBySalaryBetween(salary1, salary2);
	}

	public Employee updateName(int id, String name) {
		Employee employee = repo.findById(id).get();
		employee.setName(name);
		return repo.save(employee);
	}

	public List<Employee> findAllEmployee() {
		return repo.findAll();
	}

	public Employee updateEmployee(@RequestParam int id, @RequestBody Employee e) {
		Optional<Employee> e1 = repo.findById(id);
		if (e1.isPresent()) {
			repo.save(e);
			return repo.save(e);
		}
		return null;

	}

	public Employee updateEmail(@RequestParam int id, @RequestParam String email) {
		Employee employee = repo.findById(id).get();
		if (employee != null) {
			employee.setEmail(email);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Employee updateEmployeeName(@RequestParam int id, @RequestParam String name) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee emp = optional.get();
			emp.setName(name);
			return repo.save(emp);
		} else {
			return null;

		}
	}

	public Employee updateAddress(@RequestParam int id, @RequestParam String address) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {

			Employee emp = optional.get();
			emp.setAddress(address);
			return repo.save(emp);

		} else {
			return null;
		}
	}

	public Employee updatePhone(@RequestParam int id, @RequestParam long phone) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee emp = optional.get();
			emp.setPhone(phone);
			return repo.save(emp);
		} else {
			return null;
		}
	}

	public Employee updateSalary(@RequestParam int id, @RequestParam double salary) {

		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee emp = optional.get();
			emp.setSalary(salary);
			return repo.save(emp);
		} else {
			return null;

		}
	}

	public Employee updateDesignation(@RequestParam int id, @RequestParam String designation) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee emp = optional.get();
			emp.setDesignation(designation);
			return repo.save(emp);
		} else {
			return null;

		}
	}

	public Employee updateEmployeePassword(@RequestParam int id, @RequestParam String password) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee emp = optional.get();
			emp.setPassword(password);
			return repo.save(emp);
		} else {
			return null;
		}
	}

	public Employee updatePassword(int id, String password) {
		Employee employee = repo.findById(id).get();
		if (employee != null) {
			employee.setPassword(password);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Employee updatePassword(long phone, String password) {
		Employee employee = repo.findByPhone(phone);
		if (employee != null) {
			employee.setPassword(password);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Employee updatePassword(String email, String password) {
		Employee employee = repo.getByEmail(email);
		if (employee != null) {
			employee.setPassword(password);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public String Login(@RequestParam String email, String password) {

		Employee e = repo.findByEmail(email);

		if (e != null) {
			String password1 = e.getPassword();

			System.out.println(password);
			System.out.println(password1);
			if (password1.equalsIgnoreCase(password)) {

				return "Login successful";
			}
		}

		return "Login Failed";

	}

	public List<Employee> deleteAllEmployeeByName(String name) {
		List<Employee> list = repo.findByName(name);
		if (list.isEmpty()) {
			return null;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Employee> deleteAllEmployeeByAddress(String address) {
		List<Employee> list = repo.findByName(address);
		if (list.isEmpty()) {
			return null;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Employee> deleteAllEmployeeBySalary(double salary) {
		List<Employee> list = repo.findBySalary(salary);
		if (list.isEmpty()) {
			return null;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Employee> deleteAllEmployeeByDesignation(String designation) {
		List<Employee> list = repo.findByDesignation(designation);
		if (list.isEmpty()) {
			return null;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public Employee deleteEmployee(String email) {
		Employee employee = repo.getByEmail(email);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public Employee deleteEmployee(long phone) {
		Employee employee = repo.findByPhone(phone);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public List<Employee> findAllEmployees() {
		return repo.findAll();
	}

	public List<Employee> deleteAllEmployee() {
		repo.deleteAll();
		return null;
	}

	public Employee deleteEmployee(int id) {
		Optional<Employee> employee = repo.findById(id);
		if (employee.isPresent()) {
			repo.deleteById(id);
			return employee.get();
		} else {
			return null;
		}
	}

	public Employee updateAll(int id, Employee employee) {
		Optional<Employee> employee2 = repo.findById(id);
		if (employee2.isPresent()) {
			employee.setId(id);
			return repo.save(employee);
		} else {
			return null;
		}
	}
}
