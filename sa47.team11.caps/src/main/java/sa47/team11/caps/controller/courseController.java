package sa47.team11.caps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sa47.team11.caps.exception.courseNotFound;
import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.User;
import sa47.team11.caps.service.CourseService;


@Controller
@RequestMapping(value = "/course")
public class courseController {
	@Autowired
	private CourseService cService;
	
	/*@Autowired
	private UserService uService;*/
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView logic(HttpSession session) throws courseNotFound{
		User authenticatedUser = (User) session.getAttribute("USERSESSION");
		
		ModelAndView mav;
		if(authenticatedUser != null) {
			mav = new ModelAndView("courseindex");
			ArrayList<Course> courselst = (ArrayList<Course>)cService.getAllCourses();
			mav.addObject("courselst", courselst);
			return mav;
		}else {
			return new ModelAndView("redirect:/home/login");
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editCoursePage(@PathVariable int id, HttpSession session) throws courseNotFound{
		ModelAndView mav ;
		User usersession =(User) session.getAttribute("USERSESSION");
		if(usersession.getRole().getName().equalsIgnoreCase("Administrator")) {
			mav = new ModelAndView("course-edit");
			Course res = new Course();
			if(id>0) {
				res = cService.getCoursebyId(id);
			}
			mav.addObject("course", res);
		}
		else {
			mav = new ModelAndView("redirect:/course/index");
		}
		return mav;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editCoursePage(@ModelAttribute("course") @Valid Course course, BindingResult result, @PathVariable int id,
			final RedirectAttributes redirectAttributes,HttpSession session) throws courseNotFound {//throws RoleNotFound 

		/*if (result.hasErrors())
			return new ModelAndView("course-edit");*/
		ModelAndView mav = new ModelAndView("redirect:/course/index");
		if(id > 0)
		{
			String message = "Course was successfully updated.";
			cService.updateCourse(course);
			redirectAttributes.addFlashAttribute("message", message);
		}else {
			String message = "Course was successfully insert.";
			cService.createCourse(course);
			redirectAttributes.addFlashAttribute("message", message);
		}
		return mav;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCoursePage(@PathVariable int id) throws courseNotFound{
		Course course = cService.getCoursebyId(id);
		cService.removeStudent(course);
		ModelAndView mav = new ModelAndView("redirect:/course/index");
		String message = course.getCourseName() + " was successfully deleted.";
		//redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	@RequestMapping(value = "/enroll", method = RequestMethod.GET)
	public ModelAndView logic1() {
		ModelAndView mav = new ModelAndView("enrollment");
		//User u = uService.authenticate("Jade@gmail.com", "Password1");
		ArrayList<Course> courselst = (ArrayList<Course>)cService.getAllCourses();
		
	//	List<String> courselst = cService.getCourseNames();
		mav.addObject("courselst", courselst);
		return mav;
	}
	
	@RequestMapping(value="/test/{cid}/{sid}", method = RequestMethod.GET)
	public ModelAndView test(@PathVariable int cid,@PathVariable int sid) {
		cService.createEnrollment(cid, sid, "successful");
		ModelAndView mav = new ModelAndView("test");
		return mav;
	}
	
}
