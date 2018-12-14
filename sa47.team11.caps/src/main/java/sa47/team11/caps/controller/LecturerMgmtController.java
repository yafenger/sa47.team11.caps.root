package sa47.team11.caps.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sa47.team11.caps.service.CourseService;
import sa47.team11.caps.service.LecturerMgmtService;
import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.User;

@Controller
@RequestMapping(value="/manageLecturer")
public class LecturerMgmtController {
	
	@Autowired
	LecturerMgmtService lecturerMgmtService;
	@Autowired
	CourseService courseService;
	
	//Lecturers (View-Create-Edit-Delete)
	
	@SuppressWarnings("null")
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView listAllLecturers(HttpSession session) {
		User authenticatedUser = (User) session.getAttribute("USERSESSION");
		
		ModelAndView mav;
		if(authenticatedUser != null || authenticatedUser.getRole().getName().equalsIgnoreCase("administrator")) {
			ArrayList<User> lecturerList = lecturerMgmtService.findAllLecturers();
			mav = new ModelAndView("lecturerMgmt");
			mav.addObject("lecturerList", lecturerList);
			return mav;
		}else {
			return new ModelAndView("redirect:/home/login");
		}
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView newLecturerPage() {
		ModelAndView mav = new ModelAndView("NewLecturerForm", "user", new User());
		return mav;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView newLecturerPage(@ModelAttribute User user) {
		lecturerMgmtService.createLecturer(user.getFirstMidName(),user.getLastName(),user.getEmail(),user.getPassword());
		ArrayList<User> lecturerList = lecturerMgmtService.findAllLecturers();
		ModelAndView mav = new ModelAndView("lecturerMgmt");
		mav.addObject("lecturerList", lecturerList);
		return mav;
	}
	
	@RequestMapping(value="/edit/{userid}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer userid) {
		User user = lecturerMgmtService.findLecturerById(userid);
		ModelAndView mav = new ModelAndView("EditLecturerForm");
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value="/edit/{userid}", method = RequestMethod.POST)
	public ModelAndView editUserPage(@PathVariable Integer userid, @ModelAttribute User user) {
		lecturerMgmtService.editLecturer(user.getFirstMidName(),user.getLastName(), user.getStatus(),userid);
		ArrayList<User> lecturerList = lecturerMgmtService.findAllLecturers();
		ModelAndView mav = new ModelAndView("lecturerMgmt");
		mav.addObject("lecturerList", lecturerList);
		return mav;
	}
	
	//Lecturer Courses (View-Edit)
	
	@RequestMapping(value="/courses/{userid}", method = RequestMethod.GET)
	public ModelAndView viewLecturerCourses(@PathVariable Integer userid) {
		ArrayList<Course> lCourseList = lecturerMgmtService.findLecturerCourses(userid);
		User lecturer = lecturerMgmtService.findLecturerById(userid);
		ModelAndView mav = new ModelAndView("LecturerCourses");
		mav.addObject("lCourseList", lCourseList);
		mav.addObject("lecturer", lecturer);
		return mav;
	}
	
	@RequestMapping(value="/courses/delete/{courseid}", method = RequestMethod.GET)
	public ModelAndView unassignCourse(@PathVariable Integer courseid) {
		Integer userid = lecturerMgmtService.getLecturerID(courseid);
		lecturerMgmtService.removeAssignedLecturer(courseid);
		ArrayList<Course> lCourseList = lecturerMgmtService.findLecturerCourses(userid);
		User lecturer = lecturerMgmtService.findLecturerById(userid);
		ModelAndView mav = new ModelAndView("LecturerCourses");
		mav.addObject("lCourseList", lCourseList);
		mav.addObject("lecturer", lecturer);
		
		return mav;
	}

	@RequestMapping(value="/courses/unassigned", method = RequestMethod.GET)
	public ModelAndView unassignedCourseList() {
		ArrayList<Course> unassignedCourseList = lecturerMgmtService.findUnassignedCourses();
		ModelAndView mav = new ModelAndView("UnassignedCourses");
		mav.addObject("unassignedCourseList", unassignedCourseList);
		return mav;
	}
	
	@RequestMapping(value="/courses/assign/{courseid}", method = RequestMethod.GET)
	public ModelAndView assignCourseForm(@PathVariable Integer courseid) {
		ArrayList<User> lecturerList = lecturerMgmtService.findAllLecturers();
		Course course = courseService.getCoursebyId(courseid);
		ModelAndView mav = new ModelAndView("AssignCourseForm", "user", new User());
		mav.addObject("lecturerList", lecturerList);
		mav.addObject("course", course);
		return mav;
	}
	
	@RequestMapping(value="/courses/assign/{courseid}", method = RequestMethod.POST)
	public ModelAndView assignCourseForm(@PathVariable Integer courseid, 
			@ModelAttribute User user) {
		lecturerMgmtService.assignCourse(user, courseid);
		ArrayList<Course> unassignedCourseList = lecturerMgmtService.findUnassignedCourses();
		ModelAndView mav = new ModelAndView("UnassignedCourses");
		mav.addObject("unassignedCourseList", unassignedCourseList);
		return mav;
	
	}
}
