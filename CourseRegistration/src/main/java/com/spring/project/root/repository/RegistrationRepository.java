package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Registration;
import com.spring.project.root.dataacess.User;

@Repository
public class RegistrationRepository extends BasicRepository<Registration>{

	protected RegistrationRepository() {
		super(Registration.class);
	}
	
	@Override
	public List<Registration> getAll(){
		TypedQuery<Registration> query = entityManager.createNamedQuery("Registration.findAll", Registration.class);
		List<Registration> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Registration getByRegistrationId(int id){
		TypedQuery<Registration> query = entityManager.createNamedQuery("registration.findByIdRegistration", Registration.class);
		query.setParameter("idRegistration", id);
		Registration element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	public List<Registration> getByUserId(User user){
		TypedQuery<Registration> query = entityManager.createNamedQuery("registration.findByUserId", Registration.class);
		query.setParameter("idUser", user);
		List<Registration> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Registration> getByUserIdApproved(User user){
		TypedQuery<Registration> query = entityManager.createNamedQuery("registration.findByUserIdApproved", Registration.class);
		query.setParameter("idUser", user);
		List<Registration> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Registration> getByUserIdUnapproved(User user){
		TypedQuery<Registration> query = entityManager.createNamedQuery("registration.findByUserIdUnapproved", Registration.class);
		query.setParameter("idUser", user);
		List<Registration> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	@Transactional
	@Override
	public Registration save(Registration registration) {
		entityManager.persist(registration);
		return registration;
	}
	
	@Transactional
	@Override
	public void delete(Registration registration) {
		if (entityManager.contains(registration)){
	        entityManager.remove(registration);
	    }
	    else{
	        entityManager.remove(entityManager.merge(registration));
	    }
	}
	
	@Transactional
	@Override
	public Registration update(Registration registration) {
		entityManager.merge(registration);
		return registration;
	}
}
