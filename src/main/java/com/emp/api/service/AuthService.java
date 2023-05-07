package com.emp.api.service;
import org.springframework.transaction.annotation.Transactional;
import com.emp.api.entity.Employee;
import com.emp.api.security.CustomUserDetail;

@Transactional
public interface AuthService {
	
	CustomUserDetail loadUserByUserId(String userId);
	Employee loginUser(Employee user);


	

}
