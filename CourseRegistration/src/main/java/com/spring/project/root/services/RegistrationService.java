package com.spring.project.root.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Registration;
import com.spring.project.root.dataacess.User;
import com.spring.project.root.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public List<Registration> getList(){
		return this.registrationRepository.getAll();
	}
	
	public Registration getRegistrationById(int id) {
		return this.registrationRepository.getByRegistrationId(id);
	}
	
	public List<Registration> getRegistrationByUserId(User user) {
		return this.registrationRepository.getByUserId(user);
	}
	
	public List<Registration> getRegistrationByUserIdApproved(User user) {
		return this.registrationRepository.getByUserIdApproved(user);
	}
	
	public List<Registration> getRegistrationByUserIdUnapproved(User user) {
		return this.registrationRepository.getByUserIdUnapproved(user);
	}
	
	public void addResgistration(Registration registration){
		registrationRepository.save(registration);
	}
	
	public void removeRegistration(Registration registration) {
		registrationRepository.delete(registration);
	}
	
	public void updateRegistration(Registration registration) {
		registrationRepository.update(registration);
	}
	
}
