package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Course_semester;
import com.spring.project.root.dataacess.Semester;

@Repository
public class Course_semesterRepository extends BasicRepository<Course_semester>{

	protected Course_semesterRepository() {
		super(Course_semester.class);
	}
	
	@Override
	public List<Course_semester> getAll(){
		TypedQuery<Course_semester> query = entityManager.createNamedQuery("course_semester.findAll", Course_semester.class);
		List<Course_semester> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Course_semester getById(int id){
		TypedQuery<Course_semester> query = entityManager.createNamedQuery("course_semester.findByIdCourseSemester", Course_semester.class);
		query.setParameter("idCourseSemester", id);
		Course_semester element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	public List<Course_semester> getCurrentAndAvailable(Semester id){
		TypedQuery<Course_semester> query = entityManager.createNamedQuery("course_semester.findByCurrentAndAvailable", Course_semester.class);
		query.setParameter("idSemester", id);
		List<Course_semester> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	@Transactional
	@Override
	public Course_semester save(Course_semester CourseSemester) {
		entityManager.persist(CourseSemester);
		return CourseSemester;
	}
	
	@Transactional
	@Override
	public void delete(Course_semester CourseSemester) {
		if (entityManager.contains(CourseSemester)){
	        entityManager.remove(CourseSemester);
	    }
	    else{
	        entityManager.remove(entityManager.merge(CourseSemester));
	    }
	}
	
	@Transactional
	@Override
	public Course_semester update(Course_semester CourseSemester) {
		entityManager.merge(CourseSemester);
		return CourseSemester;
	}
}
