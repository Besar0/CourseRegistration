package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacess.Department;
import com.spring.project.root.dataacess.User;

@Repository
public class UserRepository extends BasicRepository<User>{

	protected UserRepository() {
		super(User.class);
	}
	
	@Override
	public List<User> getAll(){
		TypedQuery<User> query = entityManager.createNamedQuery("user.findAll", User.class);
		List<User> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public User getUserByEmail(final String email) {
		TypedQuery<User> query = entityManager.createNamedQuery("user.findByEmail", User.class);
		query.setParameter("email", email);
		User element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	public List<User>  getStudentByDep(Department idDepartment) {
		TypedQuery<User> query = entityManager.createNamedQuery("user.findStudentByMajorId", User.class);
		query.setParameter("idDepartment", idDepartment);
		List<User> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public User  getStudentById(int idUser) {
		TypedQuery<User> query = entityManager.createNamedQuery("user.findByIduser", User.class);
		query.setParameter("idUser", idUser);
		User element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	@Transactional
	@Override
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}
	
	@Transactional
	@Override
	public void delete(User user) {
		if (entityManager.contains(user)){
	        entityManager.remove(user);
	    }
	    else{
	        entityManager.remove(entityManager.merge(user));
	    }
	}
	
	@Transactional
	@Override
	public User update(User user) {
		entityManager.merge(user);
		return user;
	}
}
