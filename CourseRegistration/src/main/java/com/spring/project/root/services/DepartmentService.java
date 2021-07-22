package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Department;
import com.spring.project.root.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getList(){
		return this.departmentRepository.getAll();
	}
	
	public Department getById(int id) {
		return this.departmentRepository.getDepartmentById(id);
	}
	
}
