package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Semester;
import com.spring.project.root.repository.SemesterRepository;

@Service
public class SemesterService {
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	public List<Semester> getList(){
		return this.semesterRepository.getAll();
	}
	
	public Semester getSemesterById(int id){
		return this.semesterRepository.getSemesterById(id);
	}
	
	public Semester getCurrentSemester(){
		return this.semesterRepository.getCurrentSemester();
	}
	
	public void updateSemester(Semester semester) {
		this.semesterRepository.update(semester);
	}
	
}
