package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Course;


@Repository
public class CourseRepository extends BasicRepository<Course>{

	protected CourseRepository() {
		super(Course.class);
	}
	
	@Override
	public List<Course> getAll(){
		TypedQuery<Course> query = entityManager.createNamedQuery("course.findAll", Course.class);
		List<Course> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Course getCourseById(int id) {
		TypedQuery<Course> query = entityManager.createNamedQuery("course.findByIdCourse", Course.class);
		query.setParameter("idCourse", id);
		Course element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	@Transactional
	@Override
	public Course save(Course course) {
		entityManager.persist(course);
		return course;
	}
	
	@Transactional
	@Override
	public void delete(Course course) {
		if (entityManager.contains(course)){
	        entityManager.remove(course);
	    }
	    else{
	        entityManager.remove(entityManager.merge(course));
	    }
	}
	
	@Transactional
	@Override
	public Course update(Course course) {
		entityManager.merge(course);
		return course;
	}
	
}
