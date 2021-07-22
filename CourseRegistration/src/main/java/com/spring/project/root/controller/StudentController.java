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
public class StudentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private Course_semesterService courseSemesterService;
	
	@Autowired
	private RegistrationService registratoinService;
	
	
	@RequestMapping(path = "student/dashboard", method = { RequestMethod.GET })
	public String doGetDashboardStudent(final Model model) {
		User current = this.userService.getByEmail(loggedUserEmail());
		model.addAttribute("User", current);
		List<Registration> unapprovedList = registrationService.getRegistrationByUserIdUnapproved(current);
		model.addAttribute("UnapprovedList", unapprovedList);
		List<Course_semester> courselist = courseSemesterService.getCoursesForSemester(current);
		model.addAttribute("CourseList", courselist);
		return "student/dashboard";
	}
	
	@RequestMapping(path = "/student/searchCourses", method = { RequestMethod.POST })
	public String doSearchStudent(@RequestParam(name="search")String search, final Model model) {
		User current = this.userService.getByEmail(loggedUserEmail());
		model.addAttribute("User", current);
		List<Registration> unapprovedList = registrationService.getRegistrationByUserIdUnapproved(current);
		model.addAttribute("UnapprovedList", unapprovedList);
		List<Course_semester> courselist = courseSemesterService.getCoursesForSemester(current);
		if(!search.equals(null))
			courselist = courselist
			.stream()
			.filter(i -> i.getIdCourse().getCourseName().toLowerCase().contains(search))
			.collect(Collectors.toList());
		model.addAttribute("CourseList", courselist);
		return "student/dashboard";
	}
	
	@RequestMapping(path = "/add", method = { RequestMethod.GET })
	public String doAddCourse(@RequestParam(name="idCourse")int idCourse) {
		Course_semester courseSem = courseSemesterService.getById(idCourse);
		Registration regi = new Registration();
		regi.setIdCourseSemester(courseSem);
		String email = loggedUserEmail();
		regi.setIdStudent(userService.getByEmail(email));
		regi.setApproved((short) 0);
		registratoinService.addResgistration(regi);
		return "redirect:student/dashboard";
	}
	
	@RequestMapping(path = "student/register", method = { RequestMethod.GET })
	public String doGoToRegister(final Model model) {
		User current = this.userService.getByEmail(loggedUserEmail());
		model.addAttribute("User", current);
		final List<Registration> approvedList = registrationService.getRegistrationByUserIdApproved(current);
		model.addAttribute("ApprovedList", approvedList);
		return "student/register";
	}
	
	@RequestMapping(path = "/remove", method = { RequestMethod.GET })
	public String doRemoveRegistration(@RequestParam(name="idRegistration")int idRegistration) {
		Registration regi = registrationService.getRegistrationById(idRegistration);
		registrationService.removeRegistration(regi);
		return "redirect:student/dashboard";
	}
	
	 private String loggedUserEmail() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 return principal.toString();
	 }
	 
}
