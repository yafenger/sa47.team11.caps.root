package sa47.team11.caps.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sa47.team11.caps.service.*;
import sa47.team11.caps.validator.StudentValidator;
import sa47.team11.caps.model.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.User;
import sa47.team11.caps.service.StudentService;
import sa47.team11.caps.validator.StudentValidator;

@Controller
@RequestMapping(value = "/studentcourse")
public class StudentController {

	@Autowired
	StudentService sService;
	@Autowired
	 StudentValidator sValidator;
	
	@InitBinder("StudentCourse")
	private void initDepartmentBinder(WebDataBinder binder) {
		binder.addValidators(sValidator);
	}

	@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
	public ModelAndView listCourse(@PathVariable int userId) {
		ModelAndView mav = new ModelAndView("StudentCourse");
		ArrayList<StudentEnrolment> student = sService.findAllCourses(userId);
		mav.addObject("StudentCourse", student);
		return mav;
			
		
	}
	
@RequestMapping(value = "/cancelcourse/{studentEnrolId}", method = RequestMethod.GET)
public ModelAndView CancelCourse(@PathVariable int studentEnrolId) {
ModelAndView mav = new ModelAndView("CancelCourse");
// ArrayList<StudentEnrolment> student = sService.CancelCourse(studentEnrolId);
sService.CancelCourse(studentEnrolId);
//mav.addObject("CancelCourse", student);
return mav;}

			
		
	
	
	@RequestMapping(value = "/viewgrade/{userId}", method = RequestMethod.GET)
	public ModelAndView listGrade(@PathVariable int userId) {
		ModelAndView mav = new ModelAndView("ViewGrade");
		ArrayList<StudentEnrolment> student = sService.findAllCourses(userId);
		mav.addObject("ViewGrade", student);
		return mav;
			
		
	}
	
//	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
//	public ModelAndView editStudentPage(@PathVariable int userId) {
//		ModelAndView mav = new ModelAndView("StudentProfileEdit");
//		mav.addObject("student", sService.findUser(userId));
//		return mav;
//	
	
	
	
//	@RequestMapping(value = "/grade", method = RequestMethod.GET)
//	public ModelAndView listGrade() {
//		ModelAndView mav = new ModelAndView("StudentGrade");
//		ArrayList<StudentEnrolment> grade = sService.findAllGrade();
//		mav.addObject("StudentGrade", grade);
//		return mav;
//			
//		
//	}
//	
//	
	
	
	
	
	
	
	
//	@RequestMapping(value = "/{uid}/list", method = RequestMethod.GET) 
//	public ModelAndView listCourse(@PathVariable Integer uid,Model model) { 
//		ModelAndView mav = new ModelAndView("StudentCourse");
//		ArrayList<StudentEnrolment> student = sService.findAllCourses();
//		mav.addObject("StudentCourse", student);
//		return mav;
//		ArrayList<StudentEnrolment> grade = sService.ViewGrade(cid); 
//		ArrayList<Integer> nums=new ArrayList<Integer>(); 
//		for(StudentEnrolment s:grade) { nums.add(sService.CountCurrentEnrollment(c.getCourseid())); } 
//		model.addAttribute("courses", courses); 
//		model.addAttribute("nums",nums); 
//		mav.addObject(model); return mav; sService
//		}
	
	
	
	
	
	
//	@RequestMapping(value = "/{uid}", method = RequestMethod.GET) 
//	public ModelAndView ViewGrade(@PathVariable Integer uid,Model model) { 
//		ModelAndView mav = new ModelAndView("ViewGrade");
//		int cid;
//		ArrayList<StudentEnrolment> grade = sService.ViewGrade(cid); 
//		ArrayList<Integer> nums=new ArrayList<Integer>(); 
//		for(StudentEnrolment s:grade) { nums.add(sService.CountCurrentEnrollment(c.getCourseid())); } 
//		model.addAttribute("courses", courses); 
//		model.addAttribute("nums",nums); 
//		mav.addObject(model); return mav; sService
//		}
//	
	
	@Controller
	@RequestMapping(value = "/studentprofile")
	public class studentController {
		
		@Autowired
		StudentService sService;
		@Autowired
		private StudentValidator sValidator;
		
		@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
		public ModelAndView list(@PathVariable int userId) {

			User students =sService.findUser(userId);
			ModelAndView mav = new ModelAndView("StudentCRUD");
			mav.addObject("student", students);
			return mav;
		}
		
		@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
		public ModelAndView editStudentPage(@PathVariable int userId) {
			ModelAndView mav = new ModelAndView("StudentProfileEdit");
			User student = new User();
			student = sService.findUser(userId);
			mav.addObject("student", student);
			return mav;
		}

		@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
		public ModelAndView editCoursePage(@ModelAttribute("user") @Valid User user, BindingResult result, @PathVariable int userId,
				final RedirectAttributes redirectAttributes,@RequestParam("newpassword") String newpassword)  {//throws RoleNotFound 
			ModelAndView mav = new ModelAndView("redirect:/studentprofile/list/{userId}");

				String message = "Profile was successfully updated.";
				sService.editStudent(userId,newpassword);
		
			return mav;
		}
	}
}
