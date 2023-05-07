package com.emp.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.api.entity.Department;
import com.emp.api.entity.Employee;
import com.emp.api.exception.ResourceNotFoundException;
import com.emp.api.service.DepartmentService;
import com.emp.api.service.EmployeeService;
import com.emp.api.service.AuthService;

@RestController
@RequestMapping(value = "/common")
public class CommonController {

	private static Logger LOG = LogManager.getLogger(CommonController.class);

	@Autowired
	private DepartmentService depService;
	@Autowired
	private EmployeeService empService;

	@GetMapping(value = "/get-department")
	public ResponseEntity<Department> getDepartment(@RequestParam Long id) {
		Department department = depService.getDepartment(id);
		if (department != null) {
			return new ResponseEntity<Department>(department, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists With Id =" + id);
		}
	}

	@GetMapping(value = "/get-employee")
	public ResponseEntity<Employee> getEmployee(@RequestParam String id) {

		Employee employee = empService.getEmployee(id);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists With Id =" + id);
		}

	}

}
