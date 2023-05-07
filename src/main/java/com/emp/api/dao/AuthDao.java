package com.emp.api.dao;

import java.sql.Date;
import java.util.List;

import com.emp.api.entity.Role;
import com.emp.api.entity.Employee;
import com.emp.api.security.CustomUserDetail;

public interface AuthDao {
	public CustomUserDetail loadUserByUserId(String userId);

	public Employee loginUser(Employee user);


}
