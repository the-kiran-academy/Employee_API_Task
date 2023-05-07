package com.emp.api.daoimpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emp.api.dao.EmployeeDao;
import com.emp.api.entity.Department;
import com.emp.api.entity.Emp_Dept_Keys;
import com.emp.api.entity.Employee;
import com.emp.api.model.Employee_Department;
import com.emp.api.service.DepartmentService;

@Repository
public class EmployeeDaoIMPL implements EmployeeDao {

	@Autowired
	private SessionFactory factory;

	@Autowired
	private DepartmentService departmentService;

	@Override
	public Boolean addEmployee(Employee employee) {
		Session session = null;
		Boolean isAdded = false;
		try {
			session = factory.openSession();
			session.save(employee);
			session.beginTransaction().commit();
			isAdded = true;
			if (isAdded) {

				addKeys(employee.getUsername(), employee.getDepartment().getDepartment_id());
			}

		} catch (PersistenceException e) {
			isAdded = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isAdded;
	}

	/////////////////////

	public Boolean addKeys(String eid, Long did) {
		Session session = null;
		Boolean isAdded = false;
		Emp_Dept_Keys emp_Dept_Keys = new Emp_Dept_Keys();
		emp_Dept_Keys.setEid(eid);
		emp_Dept_Keys.setDid(did);
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

		emp_Dept_Keys.setId(id);
		try {
			session = factory.openSession();
			session.save(emp_Dept_Keys);
			session.beginTransaction().commit();
			isAdded = true;

		} catch (PersistenceException e) {
			isAdded = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isAdded;
	}

	///////////////////

	@Override
	public Employee getEmployee(String employee_id) {
		Session session = null;
		Employee empUser = null;
		try {
			session = factory.openSession();
			empUser = session.get(Employee.class, employee_id);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return empUser;
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session session = null;
		List<Employee> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public Boolean deleteEmployee(String employee_id) {
		Session session = null;
		Boolean isDeleted = false;
		try {
			session = factory.openSession();
			Employee empUser = getEmployee(employee_id);
			if (empUser != null) {
				session.delete(empUser);
				session.beginTransaction().commit();
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isDeleted;
	}

	@Override
	public Boolean updateEmployee(Employee employee) {
		Session session = null;
		Boolean isUpdated = false;
		try {
			session = factory.openSession();
			Employee empUser = getEmployee(employee.getUsername());
			if (empUser != null) {

				session.update(employee);
				session.beginTransaction().commit();
				isUpdated = true;
				if (isUpdated) {
					addKeys(employee.getUsername(), employee.getDepartment().getDepartment_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public List<Employee> getEmployeeBy_Date_Department(Date date, Long department_id) {
		Session session = null;
		List<Employee> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			Department department = new Department();
			department.setDepartment_id(department_id);
			criteria.add(Restrictions.eq("department", department));
			criteria.add(Restrictions.eq("createdDate", date));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public Employee_Department getWorkingDepartments(String eid) {
		Session session = null;
		Employee_Department employee_Department = new Employee_Department();
		List<Department> departments = new ArrayList<>();
		try {
			session = factory.openSession();
			employee_Department.setUsername(eid);

			List<Emp_Dept_Keys> keys = getAllKeys(eid);
			for (Emp_Dept_Keys emp : keys) {
				Department department = departmentService.getDepartment(emp.getDid());
				departments.add(department);
			}
			employee_Department.setDepartments(departments);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return employee_Department;
	}

	public List<Emp_Dept_Keys> getAllKeys(String eid) {
		Session session = null;
		List<Emp_Dept_Keys> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Emp_Dept_Keys.class);
			criteria.add(Restrictions.eq("eid", eid));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

}
