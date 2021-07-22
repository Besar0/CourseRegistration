package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Classrooms;

@Repository
public class ClassroomRepository extends BasicRepository<Classrooms>{

	protected ClassroomRepository() {
		super(Classrooms.class);
	}
	
	@Override
	public List<Classrooms> getAll(){
		TypedQuery<Classrooms> query = entityManager.createNamedQuery("classrooms.findAll", Classrooms.class);
		List<Classrooms> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Classrooms  getStudentById(int id) {
		TypedQuery<Classrooms> query = entityManager.createNamedQuery("classrooms.findByIdClassroom", Classrooms.class);
		query.setParameter("idclassroom", id);
		Classrooms element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	

}
