package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Classrooms;
import com.spring.project.root.repository.ClassroomRepository;

@Service
public class ClassroomService {
	
	@Autowired
	private ClassroomRepository classroomRepository;
	
	public List<Classrooms> getList(){
		return this.classroomRepository.getAll();
	}
	
	public Classrooms getById(int id) {
		return this.classroomRepository.getStudentById(id);
	}
	
}
