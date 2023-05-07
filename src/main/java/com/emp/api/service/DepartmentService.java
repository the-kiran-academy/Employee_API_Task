package com.emp.api.service;

import java.util.List;

import com.emp.api.entity.Department;

public interface DepartmentService {

	public Boolean addDepartment(Department department);

	public Department getDepartment(Long department_id);

	public List<Department> getAllDepartments();

	public Boolean deleteDepartment(Long department_id);

	public Boolean updateDepartment(Department department);

}
