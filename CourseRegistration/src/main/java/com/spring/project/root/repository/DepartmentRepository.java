package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Department;

@Repository
public class DepartmentRepository extends BasicRepository<Department>{

	protected DepartmentRepository() {
		super(Department.class);
	}
	
	@Override
	public List<Department> getAll(){
		TypedQuery<Department> query = entityManager.createNamedQuery("department.findAll", Department.class);
		List<Department> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Department  getDepartmentById(int id) {
		TypedQuery<Department> query = entityManager.createNamedQuery("department.findByIdDepartment", Department.class);
		query.setParameter("idDepartment", id);
		Department element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}

}
