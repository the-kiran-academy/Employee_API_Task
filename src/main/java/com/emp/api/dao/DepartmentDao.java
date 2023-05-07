package com.emp.api.dao;

import java.util.List;

import com.emp.api.entity.Department;


public interface DepartmentDao {

	public Boolean addDepartment(Department department);

	public Department getDepartment(Long department_id);

	public List<Department> getAllDepartments();

	public Boolean deleteDepartment(Long department_id);

	public Boolean updateDepartment(Department department);
}
