package com.emp.api.controller;

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
import com.emp.api.exception.ResourceAlreadyExistsException;
import com.emp.api.exception.ResourceNotFoundException;
import com.emp.api.service.DepartmentService;

@RestController
@RequestMapping(value = "/admin/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService depService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Boolean> addDepartment(@RequestBody Department department) {
		Boolean isAdded = depService.addDepartment(department);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			throw new ResourceAlreadyExistsException(
					"Resource Already Exists With ID = " + department.getDepartment_id());
		}

	}
	
	@GetMapping(value = "/getbyid")
	public ResponseEntity<Department> getDepartment(@RequestParam Long id){
		Department department = depService.getDepartment(id);
		if(department!=null) {
			return new ResponseEntity<Department>(department,HttpStatus.FOUND);
		}else {
			throw new ResourceNotFoundException("Resource Not Exists With Id = "+id);
		}
	}

	

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Department>> getAllDepartment() {

		List<Department> list = depService.getAllDepartments();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Department>>(list, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists");

		}

	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Boolean> deleteDepartment(@RequestParam Long id) {
		Boolean isDeleted = depService.deleteDepartment(id);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.MOVED_PERMANENTLY);

		} else {
			throw new ResourceNotFoundException("Resource Not Exists With ID = " + id);

		}

	}

	@PutMapping(value = "/update")
	public ResponseEntity<Boolean> updateDepartment(@RequestBody Department department) {
		Boolean isUpdated = depService.updateDepartment(department);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException("Resource Not Exists With ID = " + department.getDepartment_id());

		}

	}

}
