package com.qsp.employee_management_system.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.employee_management_system.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	Employee findByPhone(long phone);

	Employee getByEmail(String email);

	List<Employee> findByAddress(String address);

	List<Employee> findByDesignation(String designation);

//	@Query("SELECT e FROM Employee e WHERE e.address=?1")
//	List<Employee> employeeByAddress(String address);

//	@Query("SELECT e FROM Employee e WHERE e.name=?1")
//	List<Employee> personByName(String name);

	List<Employee> findBySalaryLessThan(double salary);

	List<Employee> findBySalaryGreaterThan(double salary);

	List<Employee> findBySalaryBetween(double salary1, double salary2);

	List<Employee> findByName(String name);

	Employee findByEmail(String email);

	Optional<Employee> findById(int id);

	List<Employee> findBySalary(double salary);

	@Query("DELETE FROM Employee e WHERE e.id = ?1")
	Employee deleteById(Employee emp);

	@Query("DELETE  FROM  Employee e WHERE e.phone = ?1")
	void deleteByPhone(Employee e);

	@Query("DELETE  FROM Employee e WHERE e.email = ?1")
	void deleteByEmail(Employee e);

	@Query("DELETE  FROM Employee e WHERE e.designation = ?1")
	void deleteByDesignation(List<Employee> e);

	@Query("DELETE  FROM Employee e WHERE e.address = ?1")
	void deleteByAdress(Employee e);

	@Query("DELETE  FROM Employee e WHERE e.name = ?1")
	void deleteByName(Employee emp);
}
