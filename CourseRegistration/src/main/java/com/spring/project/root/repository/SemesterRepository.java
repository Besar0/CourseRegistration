package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Semester;

@Repository
public class SemesterRepository extends BasicRepository<Semester>{

	protected SemesterRepository() {
		super(Semester.class);
	}
	
	@Override
	public List<Semester> getAll(){
		TypedQuery<Semester> query = entityManager.createNamedQuery("semester.findAll", Semester.class);
		List<Semester> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Semester getSemesterById(int id){
		TypedQuery<Semester> query = entityManager.createNamedQuery("semester.findByIdSemester", Semester.class);
		query.setParameter("idSemester", id);
		Semester element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	public Semester getCurrentSemester(){
		TypedQuery<Semester> query = entityManager.createNamedQuery("semester.findByCurrent", Semester.class);
		Semester element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	@Transactional
	@Override
	public Semester update(Semester semester) {
		entityManager.merge(semester);
		return semester;
	}
	
}
