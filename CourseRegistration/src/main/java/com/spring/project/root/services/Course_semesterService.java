package com.spring.project.root.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacess.Course_semester;
import com.spring.project.root.dataacess.Registration;
import com.spring.project.root.dataacess.Semester;
import com.spring.project.root.dataacess.User;
import com.spring.project.root.repository.Course_semesterRepository;

@Service
public class Course_semesterService {
	
	@Autowired
	private Course_semesterRepository course_semesterRepository;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired 
	private SemesterService semesterService;
	
	public List<Course_semester> getList(){
		return this.course_semesterRepository.getAll();
	}
	
	public Course_semester getById(int id){
		return this.course_semesterRepository.getById(id);
	}
	
	public List<Course_semester> getCoursesBySemesterAndAvailable(Semester id){
		return this.course_semesterRepository.getCurrentAndAvailable(id);
	}
	
	public List<Course_semester> getCoursesForSemester(User current){
		List<Course_semester> courselist = course_semesterRepository.getCurrentAndAvailable(semesterService.getCurrentSemester());
		
		//filter out courses that aren't in users department
		courselist = courselist.stream()
		.filter(i -> i.getIdCourse().getIdDepartment().getIdDepartment().equals(current.getIdDepartment().getIdDepartment()))
		.collect(Collectors.toList());
		
		//gets a list of course semester that the user has registered both approved and unapproved
		List<Registration> listOfRegistered = registrationService.getRegistrationByUserId(current);
		
		if(listOfRegistered != null) {
			List<Course_semester> registeredCourses = listOfRegistered
					.stream()
					.map(i -> i.getIdCourseSemester())
					.collect(Collectors.toList());
			//filters out registered courses
			courselist.removeIf(i -> inList(i.getIdCourseSemester(), registeredCourses));
			
			//filter out only approved courses from the list of registered courses
			List<Course_semester>  approvedCourses = listOfRegistered
					.stream()
					.filter(i -> i.getApproved()==(short)1)
					.map(i -> i.getIdCourseSemester())
					.collect(Collectors.toList());
			
			//create a list of all courses that have prerequisite
			List<Course_semester> coursesWithPre = courselist
					.stream()
					.filter(i -> i.getIdCourse().getIdPrerequisite() != null)
					.collect(Collectors.toList());
			//filter out the courses who prerequisite user has taken
			coursesWithPre.removeIf(i -> !inList(i.getIdCourse().getIdPrerequisite(), approvedCourses));
			//remove those courses from the list
			courselist = courselist
					.stream()
					.filter(i -> i.getIdCourse().getIdPrerequisite() == null)
					.collect(Collectors.toList());
			//add only course with prerequisite taken to the list
			courselist.addAll(0, coursesWithPre);
			return courselist;
			
		}
		else 
			return courselist = courselist
					.stream()
					.filter(i -> i.getIdCourse().getIdPrerequisite() == null)
					.collect(Collectors.toList());
	}
	
	private boolean inList(int id, List<Course_semester> courses) {
		return courses.stream()
			.anyMatch(i -> i.getIdCourse().getIdCourse() == id);
	}
	
	public void addCourseSemester(Course_semester courseSem){
		this.course_semesterRepository.save(courseSem);
	}
	
	public void removeCourseSemester(Course_semester courseSem) {
		this.course_semesterRepository.delete(courseSem);
	}
	
	public Course_semester updateCourseSemester(Course_semester courseSem) {
		return this.course_semesterRepository.update(courseSem);
		
	}
}
