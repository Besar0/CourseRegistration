package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Department;
import com.spring.project.root.dataacess.User;
import com.spring.project.root.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getList(){
		return this.userRepository.getAll();
	}
	
	public List<User> getStudentsByDepId(Department idDepartment){
		return this.userRepository.getStudentByDep(idDepartment);
	}
	
	public User getByEmail(String email) {
		return this.userRepository.getUserByEmail(email);
	}
	
	public User getById(int id) {
		return this.userRepository.getStudentById(id);
	}
	
	public void addUser(User user){
		this.userRepository.save(user);
	}
	
	public void removeUser(User user) {
		this.userRepository.delete(user);
	}
	
	public void updateUser(User user) {
		this.userRepository.update(user);
	}
	
}
