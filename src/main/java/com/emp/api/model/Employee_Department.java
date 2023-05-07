package com.emp.api.model;


import java.util.List;

import com.emp.api.entity.Department;


public class Employee_Department {
	private String username;
	private List<Department> departments;

	public Employee_Department() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	

}
