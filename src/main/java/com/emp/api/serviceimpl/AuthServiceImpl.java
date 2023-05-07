package com.emp.api.serviceimpl;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emp.api.dao.AuthDao;
import com.emp.api.entity.Role;
import com.emp.api.entity.Employee;
import com.emp.api.security.CustomUserDetail;
import com.emp.api.service.AuthService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthDao dao;

	

	@Override
	public Employee loginUser(Employee user) {

		return dao.loginUser(user);
	}

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {
		System.out.println("service..." + userId);
		return dao.loadUserByUserId(userId);
	}

	
	
	

}
