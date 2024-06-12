package com.qsp.employee_management_system.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.function.EntityResponse;

import com.qsp.employee_management_system.dao.EmployeeDao;
import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.exception.EmailNotFoundException;
import com.qsp.employee_management_system.exception.IdNotFoundException;
import com.qsp.employee_management_system.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		double salary = employee.getSalary();
		if (salary <= 10000) {
			employee.setGrade('D');
		} else if (salary >= 10000 && salary <= 20000) {
			employee.setGrade('C');
		} else if (salary >= 20000 && salary <= 30000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("saved successfully");
		structure.setData(dao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(List<Employee> employees) {
		for (Employee employee : employees) {
			double salary = employee.getSalary();
			if (salary <= 10000) {
				employee.setGrade('D');
			} else if (salary >= 10000 && salary <= 20000) {
				employee.setGrade('C');
			} else if (salary >= 20000 && salary <= 30000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
		}
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("saved successfully");
		structure.setData(dao.saveAllEmployee(employees));
		return new ResponseEntity<ResponseStructure<List<Employee>>>(HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(int id) {
		Employee employee = dao.findEmployeeById(id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Employee found successful");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Employee Not found");
//			structure.setData(employee);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.NOT_FOUND);
			throw new IdNotFoundException("Employee with id" + id + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		List<Employee> list = dao.findAll();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No data available");
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Employee found successful");
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByPhone(@RequestParam long phone) {
		Employee employee = dao.findByPhone(phone);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		Employee employee = dao.findByEmail(email);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("Not Found");
//			structure.setData(employee);
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
			throw new EmailNotFoundException("Email not found");
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(String name) throws NameNotFoundException {
		List<Employee> list = dao.findByName(name);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
//			structure.setStatus(HttpStatus.FOUND.value());
//			structure.setMessage("Found Successfully");
//			structure.setData(list);
//			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
			throw new NameNotFoundException("Employee with name" + name + "not found");

		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {
		List<Employee> list = dao.findByAddress(address);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByDesignation(String designation) {
		List<Employee> list = dao.findByDesignation(designation);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryLessThan(double salary) {
		List<Employee> list = dao.salaryLessThan(salary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryGreaterThan(double salary) {
		List<Employee> list = dao.salaryGreaterThan(salary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(double salary1, double salary2) {
		List<Employee> list = dao.salaryBetween(salary1, salary2);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateName(int id, String name) {
		Employee employee = dao.updateName(id, name);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		Employee employee = dao.updateSalary(id, salary);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {
		Employee employee = dao.updatePhone(id, phone);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateAddress(int id, String address) {
		Employee employee = dao.updateAddress(id, address);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		Employee employee = dao.updateEmail(id, email);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateDesignation(int id, String designation) {
		Employee employee = dao.updateDesignation(id, designation);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(int id, String password) {
		Employee employee = dao.updatePassword(id, password);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(long phone, String password) {
		Employee employee = dao.updatePassword(phone, password);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePassword(String email, String password) {
		Employee employee = dao.updatePassword(email, password);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateAll(int id, Employee employee) {
		Employee employee2 = dao.updateAll(id, employee);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee2 != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(employee2);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee2);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		Employee employee = dao.deleteEmployee(id);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(long phone) {
		Employee employee = dao.deleteEmployee(phone);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(String email) {
		Employee employee = dao.deleteEmployee(email);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployee() {
		List<Employee> list = dao.deleteAllEmployee();

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);

		} else {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployeeByAddress(String address) {
		List<Employee> list = dao.deleteAllEmployeeByAddress(address);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);

		} else {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployeeByDesignation(String designation) {
		List<Employee> list = dao.deleteAllEmployeeByDesignation(designation);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployeeBySalary(double salary) {
		List<Employee> list = dao.deleteAllEmployeeBySalary(salary);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> deleteAllEmployeeByName(String name) {
		List<Employee> list = dao.deleteAllEmployeeByName(name);

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);
		}

	}

}
