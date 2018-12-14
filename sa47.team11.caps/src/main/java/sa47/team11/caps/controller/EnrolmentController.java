package sa47.team11.caps.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sa47.team11.caps.model.User;
import sa47.team11.caps.service.UserService;
import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.StudentEnrolment;
import sa47.team11.caps.service.CourseService;
import sa47.team11.caps.service.StudentEnrolmentService;

@Controller
@RequestMapping(value = "/enrol")
public class EnrolmentController {

	
		@Autowired
		private StudentEnrolmentService sService;
		
		@Autowired
		private CourseService cService;
		
		@Autowired
		private UserService uService;
		
		@InitBinder("user")
		private void initDepartmentBinder(WebDataBinder binder) {
			//binder.addValidators(sValidator);
		}
		
		

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView listAll(HttpSession session) {
			ArrayList<StudentEnrolment> enrolmt = (ArrayList<StudentEnrolment>)sService.findAllStudentEnrolment();
			User _authenticatedUser = (User) session.getAttribute("USERSESSION");
			if(_authenticatedUser != null || _authenticatedUser.getRole().getName().toLowerCase() != "student") {
				ModelAndView mav = new ModelAndView("enrolmentindex");
				mav.addObject("enrolmt", enrolmt);
				return mav;
			}else {
				return new ModelAndView("redirect:/home/login");
			}
		}

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView newEnrolmentPage() {
			ModelAndView mav = new ModelAndView("EnrolmentNewForm", "enrolobj", new StudentEnrolment());
			ArrayList<Course> courselst =(ArrayList<Course>) cService.getAllCourses();
			ArrayList<User> studentlst =(ArrayList<User>) uService.getUserStudentRole();
			mav.addObject("courselst",courselst);
			mav.addObject("studentlst",studentlst);
			return mav;
		}

		@RequestMapping(value = "/create", method = RequestMethod.POST)
		public ModelAndView createNewEnrolment(@ModelAttribute @Valid StudentEnrolment StudentEnrolment, BindingResult result,
				final RedirectAttributes redirectAttributes) {
			Integer courseid=StudentEnrolment.getCourse().getCourseid();
			Integer studentid=StudentEnrolment.getUser().getUserid();
			sService.createEnrolment(courseid,studentid);
			if (result.hasErrors())
				return new ModelAndView("EnrolmentNewForm");
			ArrayList<StudentEnrolment> enrolmt = (ArrayList<StudentEnrolment>)sService.findAllStudentEnrolment();
			ModelAndView mav = new ModelAndView("enrolmentindex");
			mav.addObject("enrolmt", enrolmt);
			return mav;
		}

		/*@RequestMapping(value = "/edit/{uid}/{cid}", method = RequestMethod.GET)
		public ModelAndView editEnrolmentPage(@PathVariable("uid") int uid,@PathVariable("cid") int cid) {
			StudentEnrolment enrol = sService.getEnrolledStudent(uid,cid);
			ModelAndView mav = new ModelAndView("enrolmentedit", "studentenrol", enrol);
			return mav;
	
		}

		@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
		public ModelAndView editEnrolmentPage(@PathVariable Integer id, @ModelAttribute StudentEnrolment enrol) {
			sService.updateStudentEnrolment(enrol);
			ArrayList<StudentEnrolment> ulist = sService.findAllStudentEnrolment();
			ModelAndView mav = new ModelAndView("EnrolCRUD");
			mav.addObject("users", ulist);
			return mav;
		}*/

		@RequestMapping(value = "/edit/{uid}/{cid}", method = RequestMethod.GET)
		public ModelAndView editEnrolmentPage(@PathVariable("uid") int uid,@PathVariable("cid") int cid) {
			StudentEnrolment enrol = sService.getEnrolledStudent(uid,cid);
			ModelAndView mav = new ModelAndView("enrolmentedit", "studentenrol", enrol);
			return mav;
	
		}

		@RequestMapping(value = "/edit/{uid}/{cid}", method = RequestMethod.POST)
		public ModelAndView editEnrolmentPage(@PathVariable("uid") int uid,@PathVariable("cid") int cid, @ModelAttribute StudentEnrolment enrol, BindingResult result,
				final RedirectAttributes redirectAttributes) {
			sService.editEnrolment(enrol.getStatusEnroll(), enrol.getStudentEnrolId());
			ArrayList<StudentEnrolment> enrolmt = (ArrayList<StudentEnrolment>)sService.findAllStudentEnrolment();
			ModelAndView mav = new ModelAndView("enrolmentindex");
			mav.addObject("enrolmt", enrolmt);
			return mav;

		}
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public ModelAndView deleteStudent(@PathVariable Integer id, final RedirectAttributes redirectAttributes)
		{
			StudentEnrolment studentenrolment = sService.findStudentEnrolment(id);
			sService.removeStudentEnrolment(studentenrolment);
			ArrayList<StudentEnrolment> ulist = sService.findAllStudentEnrolment();
			ModelAndView mav = new ModelAndView("redirect:/enrol/list");
			mav.addObject("studentenrol", ulist);
			return mav;
		}	
}
