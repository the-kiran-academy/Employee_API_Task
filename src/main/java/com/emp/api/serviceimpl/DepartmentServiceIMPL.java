package com.emp.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.api.dao.DepartmentDao;
import com.emp.api.entity.Department;
import com.emp.api.service.DepartmentService;

@Service
public class DepartmentServiceIMPL implements DepartmentService {
	
	@Autowired
	private DepartmentDao dao;

	@Override
	public Boolean addDepartment(Department department) {
		return dao.addDepartment(department);
	}

	@Override
	public Department getDepartment(Long department_id) {
		return dao.getDepartment(department_id);
	}

	@Override
	public List<Department> getAllDepartments() {
		return dao.getAllDepartments();
	}

	@Override
	public Boolean deleteDepartment(Long department_id) {
		return dao.deleteDepartment(department_id);
	}

	@Override
	public Boolean updateDepartment(Department department) {
		return dao.updateDepartment(department);
	}

}
