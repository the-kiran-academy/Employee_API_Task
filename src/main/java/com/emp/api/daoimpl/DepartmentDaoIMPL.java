package com.emp.api.daoimpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emp.api.dao.DepartmentDao;
import com.emp.api.entity.Department;

@Repository
public class DepartmentDaoIMPL implements DepartmentDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public Boolean addDepartment(Department department) {
		Session session = null;
		Boolean isAdded = false;
		try {
			session = factory.openSession();
			session.save(department);
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

	@Override
	public Department getDepartment(Long department_id) {
		Session session = null;
		Department department = null;
		try {
			session = factory.openSession();
			department = session.get(Department.class, department_id);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return department;
	}

	@Override
	public List<Department> getAllDepartments() {
		Session session = null;
		List<Department> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Department.class);
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
	public Boolean deleteDepartment(Long department_id) {
		Session session = null;
		Boolean isDeleted = false;
		try {
			session = factory.openSession();
			Department department = getDepartment(department_id);
			if (department != null) {
				session.delete(department);
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
	public Boolean updateDepartment(Department department) {
		Session session = null;
		Boolean isUpdated=false;
		try {
			session = factory.openSession();
			Department dbDepartment = getDepartment(department.getDepartment_id());
			if (dbDepartment != null) {
				session.update(department);
				session.beginTransaction().commit();
				isUpdated=true;
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

}
