package com.spring.project.root.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.root.dataacess.Course;
import com.spring.project.root.dataacess.Course_semester;
import com.spring.project.root.dataacess.Semester;
import com.spring.project.root.dataacess.User;
import com.spring.project.root.services.ClassroomService;
import com.spring.project.root.services.CourseService;
import com.spring.project.root.services.Course_semesterService;
import com.spring.project.root.services.DepartmentService;
import com.spring.project.root.services.SemesterService;
import com.spring.project.root.services.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private Course_semesterService courseSemesterService;
	
	@Autowired
	private SemesterService semesterService;
	
	@RequestMapping(path = "admin/dashboard", method = { RequestMethod.GET })
	public String doGetDashboard(final Model model) {
		model.addAttribute("currentSemester", semesterService.getCurrentSemester());
		model.addAttribute("semesterList", semesterService.getList());
		return "admin/dashboard";
	}
	
	@RequestMapping(path = "/updateCurrentSemester", method = { RequestMethod.POST })
	public String doUpdateCurrentSemester(@RequestParam(name="idSemester")int id, Model model) {
		Semester current = semesterService.getCurrentSemester();
		current.setCurrent((short)0);
		semesterService.updateSemester(current);
		Semester newCurrent = semesterService.getSemesterById(id);
		newCurrent.setCurrent((short)1);
		semesterService.updateSemester(newCurrent);
		return "redirect:admin/dashboard";
	}
	
//Admin User Functionality
	
	@RequestMapping(path = "admin/newUser", method = { RequestMethod.GET })
	public String doGetNewUser(final Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("departments", departmentService.getList());
		return "admin/newUser";
	}
	
	@RequestMapping(path = "/addUser", method = { RequestMethod.POST })
	public String doAddNewUser(@ModelAttribute User user, @RequestParam(name="role")String role, Model model) {
		user.setRole(role);
		userService.addUser(user);
		return "redirect:admin/dashboard";
	}
	
	@RequestMapping(path = "admin/allUsers", method = { RequestMethod.GET })
	public String doGetAllUsers(final Model model) {
		model.addAttribute("UserList", userService.getList());
		return "admin/allUsers";
	}
	
	@RequestMapping(path = "admin/updateUser", method = { RequestMethod.GET })
	public String doUpdateUsers(@RequestParam(name="idUser")int idUser, Model model) {
		model.addAttribute("user", userService.getById(idUser));
		model.addAttribute("departments", departmentService.getList());
		return "admin/updateUser";
	}
	
	@RequestMapping(path = "admin/removeUser", method = { RequestMethod.GET })
	public String doRemoveUsers(@RequestParam(name="idUser")int idUser, Model model) {
		userService.removeUser(	userService.getById(idUser) );
		return "redirect:allUsers";
	}
	
	@RequestMapping(path = "/updateUserInfo", method = { RequestMethod.POST })
	public String doUpdateUserInfo(@ModelAttribute User user,@RequestParam(name="role")String role, Model model) {
		user.setRole(role);
		userService.updateUser(user);
		return "redirect:admin/allUsers";
	}
	
	//Admin Course Functionality
	
	@RequestMapping(path = "admin/newCourse", method = { RequestMethod.GET })
	public String doGetNewCourse(final Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("departments", departmentService.getList());
		model.addAttribute("courseList", courseService.getList());
		model.addAttribute("classroomList", classroomService.getList());
		return "admin/newCourse";
	}
	
	@RequestMapping(path = "/addCourse", method = { RequestMethod.POST })
	public String doAddNewCourse(@ModelAttribute Course course, @RequestParam(name="day")String day, Model model) {
		course.setDay(day);
		courseService.addCourse(course);
		return "redirect:admin/dashboard";
	}
	
	@RequestMapping(path = "admin/allCourses", method = { RequestMethod.GET })
	public String doGetAllCourses(final Model model) {
		model.addAttribute("courseList", courseService.getList());
		return "admin/allCourses";
	}
	
	@RequestMapping(path = "admin/updateCourse", method = { RequestMethod.GET })
	public String doUpdateCourse(@RequestParam(name="idCourse")int id, Model model) {
		model.addAttribute("course", courseService.getById(id));
		model.addAttribute("departments", departmentService.getList());
		model.addAttribute("courseList", courseService.getList());
		model.addAttribute("classroomList", classroomService.getList());
		return "admin/updateCourse";
	}
	
	@RequestMapping(path = "admin/removeCourse", method = { RequestMethod.GET })
	public String doRemoveCourse(@RequestParam(name="idCourse")int id, Model model) {
		courseService.removeCourse(	courseService.getById(id) );
		return "redirect:allCourses";
	}
	
	@RequestMapping(path = "/updateCourseInfo", method = { RequestMethod.POST })
	public String doUpdateCourseInfo(@ModelAttribute Course course,@RequestParam(name="day")String day, Model model) {
		course.setDay(day);
		courseService.updateCourse(course);
		return "redirect:admin/allCourses";
	}	
	
	//Admin Course Semester Functionality
	
	@RequestMapping(path = "admin/newCourseSemester", method = { RequestMethod.GET })
	public String doGetNewCourseSemester(final Model model) {
		model.addAttribute("courseSemester", new Course_semester());
		model.addAttribute("courseList", courseService.getList());
		model.addAttribute("semesterList", semesterService.getList());
		return "admin/newCourseSemester";
	}
	
	@RequestMapping(path = "/addCourseSemester", method = { RequestMethod.POST })
	public String doAddNewCourse(@ModelAttribute Course_semester courseSem, Model model) {
		courseSemesterService.addCourseSemester(courseSem);
		return "redirect:admin/dashboard";
	}
	
	@RequestMapping(path = "admin/allCourseSemester", method = { RequestMethod.GET })
	public String doGetAllCourseSemester(final Model model) {
		model.addAttribute("courseSemesterList", courseSemesterService.getList());
		return "admin/allCourseSemester";
	}
	
	@RequestMapping(path = "admin/updateCourseSemester", method = { RequestMethod.GET })
	public String doUpdateCourseSemester(@RequestParam(name="idCourseSemester")int id, Model model) {
		model.addAttribute("courseSemester", courseSemesterService.getById(id));
		model.addAttribute("courseList", courseService.getList());
		model.addAttribute("semesterList", semesterService.getList());
		return "admin/updateCourseSemester";
	}
	
	@RequestMapping(path = "admin/removeCourseSemester", method = { RequestMethod.GET })
	public String doRemoveCourseSemester(@RequestParam(name="idCourseSemester")int id, Model model) {
		courseSemesterService.removeCourseSemester(	courseSemesterService.getById(id) );
		return "redirect:allCourseSemester";
	}
	
	@RequestMapping(path = "/updateCourseSemesterInfo", method = { RequestMethod.POST })
	public String doUpdateCourseSemesterInfo(@ModelAttribute Course_semester courseSem, Model model) {
		courseSemesterService.updateCourseSemester(courseSem);
		return "redirect:admin/allCourseSemester";
	}	
	
	//Admin Search Functionality
	
	@RequestMapping(path = "/admin/searchCourses", method = { RequestMethod.POST })
	public String doSearchCourse(@RequestParam(name="search")String search, final Model model) {
		List<Course> courselist = courseService.getList();
		if(!search.equals(null))
			courselist = courselist
			.stream()
			.filter(i -> i.getCourseName().toLowerCase().contains(search))
			.collect(Collectors.toList());
		model.addAttribute("courseList", courselist);
		return "admin/allCourses";
	}
	
	@RequestMapping(path = "/admin/searchCourseSemesters", method = { RequestMethod.POST })
	public String doSearchCourseSem(@RequestParam(name="search")String search, final Model model) {
		List<Course_semester> courselist = courseSemesterService.getList();
		if(!search.equals(null))
			courselist = courselist
			.stream()
			.filter(i -> i.getIdCourse().getCourseName().toLowerCase().contains(search))
			.collect(Collectors.toList());
		model.addAttribute("courseSemesterList", courselist);
		return "admin/allCourseSemester";
	}
	
	@RequestMapping(path = "/admin/searchUsers", method = { RequestMethod.POST })
	public String doSearchUsers(@RequestParam(name="search")String search, final Model model) {
		List<User> students = this.userService.getList();
		if(!search.equals(null))
			students = students
				.stream()
				.filter(i -> i.getName().toLowerCase().contains(search) || i.getSurname().toLowerCase().contains(search))
				.collect(Collectors.toList());
		model.addAttribute("UserList", students);
		return "admin/allUsers";
	}
	
}
