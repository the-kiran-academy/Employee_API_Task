package com.emp.api.serviceimpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emp.api.dao.EmployeeDao;
import com.emp.api.entity.Department;
import com.emp.api.entity.Employee;
import com.emp.api.model.Employee_Department;
import com.emp.api.security.CustomUserDetail;
import com.emp.api.service.EmployeeService;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeDao dao;

	@Override
	public Boolean addEmployee(Employee employee) {
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		return dao.addEmployee(employee);
	}

	@Override
	public Employee getEmployee(String employee_id) {
		return dao.getEmployee(employee_id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}

	@Override
	public Boolean deleteEmployee(String employee_id) {
		return dao.deleteEmployee(employee_id);
	}

	@Override
	public Boolean updateEmployee(Employee employee) {
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		return dao.updateEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeeBy_Date_Department(Date date, Long department_id) {
		return dao.getEmployeeBy_Date_Department(date, department_id);
	}

	@Override
	public Employee_Department getWorkingDepartments(String employee_id) {
		return dao.getWorkingDepartments(employee_id);
	}


}
