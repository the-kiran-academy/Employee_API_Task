package com.emp.api.service;

import java.sql.Date;
import java.util.List;

import com.emp.api.entity.Department;
import com.emp.api.entity.Employee;
import com.emp.api.model.Employee_Department;
import com.emp.api.security.CustomUserDetail;

public interface EmployeeService {

	public Boolean addEmployee(Employee employee);

	public Employee getEmployee(String employee_id);

	public List<Employee> getAllEmployees();

	public Boolean deleteEmployee(String employee_id);

	public Boolean updateEmployee(Employee employee);

	public List<Employee> getEmployeeBy_Date_Department(Date date, Long department_id);

	public Employee_Department getWorkingDepartments(String employee_id);
	
	//public Boolean transferDepartment();

}
