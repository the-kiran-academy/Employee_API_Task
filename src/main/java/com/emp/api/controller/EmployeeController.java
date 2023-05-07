package com.emp.api.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.api.entity.Department;
import com.emp.api.entity.Employee;
import com.emp.api.exception.ResourceAlreadyExistsException;
import com.emp.api.exception.ResourceNotFoundException;
import com.emp.api.model.Employee_Department;
import com.emp.api.service.EmployeeService;

@RestController
@RequestMapping(value = "/admin/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping(value = "/add")
	public ResponseEntity<Boolean> addEmployee(@RequestBody Employee employee) {

		Boolean isAdded = empService.addEmployee(employee);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			throw new ResourceAlreadyExistsException("Resource Already Exists With ID = " + employee.getUsername());
		}

	}

	@GetMapping(value = "/getbyid")
	public ResponseEntity<Employee> getDepartment(@RequestParam String id) {
		Employee employee = empService.getEmployee(id);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists With Id = " + id);
		}
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Employee>> getAllEmployee() {

		List<Employee> list = empService.getAllEmployees();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(list, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists");

		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Boolean> deleteEmployee(@RequestParam String id) {
		Boolean isDeleted = empService.deleteEmployee(id);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.MOVED_PERMANENTLY);

		} else {
			throw new ResourceNotFoundException("Resource Not Exists With ID = " + id);

		}

	}

	@PutMapping(value = "/update")
	public ResponseEntity<Boolean> updateEmployee(@RequestBody Employee employee) {
		Boolean isUpdated = empService.updateEmployee(employee);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists With ID = " + employee.getUsername());

		}
	}

	@GetMapping(value = "/getby_date_dept")
	public ResponseEntity<List<Employee>> getEmployeeBy_Date_Department(@RequestParam Date date, Long depId) {
		List<Employee> list = empService.getEmployeeBy_Date_Department(date, depId);

		if (!list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(list, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists");

		}

	}

	@GetMapping(value = "/getworking_department")
	public ResponseEntity<Employee_Department> getWorkingDepartments(@RequestParam String employee_id) {
		Employee_Department workingDepartments = empService.getWorkingDepartments(employee_id);
		if (workingDepartments != null) {
			return new ResponseEntity<Employee_Department>(workingDepartments, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists");

		}

	}

}
