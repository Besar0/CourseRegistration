package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Course;
import com.spring.project.root.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getList(){
		return this.courseRepository.getAll();
	}
	
	public Course getById(int id){
		return this.courseRepository.getCourseById(id);
	}
	
	public void addCourse(Course course){
		this.courseRepository.save(course);
	}
	
	public void removeCourse(Course course) {
		this.courseRepository.delete(course);
	}
	
	public void updateCourse(Course course) {
		this.courseRepository.update(course);
	}
}
