package com.spring.project.root.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.root.dataacess.Course_semester;
import com.spring.project.root.dataacess.Registration;
import com.spring.project.root.dataacess.User;
import com.spring.project.root.services.Course_semesterService;
import com.spring.project.root.services.RegistrationService;
import com.spring.project.root.services.UserService;

@Controller
public class ProfessorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationService registratoinService;
	
	@Autowired
	private Course_semesterService courseSemesterService;

	@RequestMapping(path = "professor/dashboard", method = { RequestMethod.GET })
	public String doGetDashboardProfessor(final Model model) {
		model.addAttribute("User", this.userService.getByEmail(loggedUserEmail()));
		final List<User> students = this.userService.getStudentsByDepId( this.userService.getByEmail(loggedUserEmail()).getIdDepartment() );
		model.addAttribute("StudentList", students);
		return "professor/dashboard";
	}
	
	@RequestMapping(path = "professor/courses", method = { RequestMethod.GET })
	public String doGetCoursesProfessor(@RequestParam(name="idStudent")int id, final Model model) {
		model.addAttribute("User", this.userService.getByEmail(loggedUserEmail()));
		List<Registration> ApprovedCourses = registratoinService.getRegistrationByUserIdApproved( userService.getById(id));
		model.addAttribute("ApprovedList", ApprovedCourses);
		List<Registration> UnapprovedCourses = registratoinService.getRegistrationByUserIdUnapproved( userService.getById(id));
		model.addAttribute("UnapprovedList", UnapprovedCourses);
		return "professor/courses";
	}
	
	@RequestMapping(path = "/approve", method = { RequestMethod.GET })
	public String doApproveCourse(@RequestParam(name="idRegistration")int idRegistration) {
		Registration regi = registratoinService.getRegistrationById(idRegistration);
		Course_semester courseSem = courseSemesterService.getById(regi.getIdCourseSemester().getIdCourseSemester());
		
		if(courseSem.getAvailability() == (short)1) {
			regi.setApproved((short) 1);
			courseSem.setStudentsRegistered( courseSem.getStudentsRegistered() +1 );
			courseSem.setAvailableSeats( courseSem.getAvailableSeats() - 1);
			if(courseSem.getAvailableSeats() == 0)
				courseSem.setAvailability((short)0);
			courseSemesterService.updateCourseSemester(courseSem);
		}
		registratoinService.updateRegistration(regi);
		return "redirect:professor/courses?idStudent="+ regi.getIdUser().getIdUser();
	}
	
	@RequestMapping(path = "/professor/searchStudents", method = { RequestMethod.POST })
	public String doSearchStudent(@RequestParam(name="search")String search, final Model model) {
		List<User> students = this.userService.getStudentsByDepId( this.userService.getByEmail(loggedUserEmail()).getIdDepartment() );
		model.addAttribute("User", this.userService.getByEmail(loggedUserEmail()));
		if(!search.equals(null))
			students = students
				.stream()
				.filter(i -> i.getName().toLowerCase().contains(search) || i.getSurname().toLowerCase().contains(search))
				.collect(Collectors.toList());
		model.addAttribute("StudentList", students);
		return "professor/dashboard";
	}
	
	private String loggedUserEmail() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 return principal.toString();
	 }
	
}
