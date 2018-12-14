package sa47.team11.caps.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.StudentEnrolment;
import sa47.team11.caps.model.User;
import sa47.team11.caps.service.LecturerService;

@Controller
@RequestMapping(value = "/lecturer")

public class LecturerController {
	@Autowired
	private LecturerService lservice;


	@RequestMapping(value = "/viewstudent/{uid}", method = RequestMethod.GET)
	public ModelAndView viewStudentById(@PathVariable int uid, Model model) {
		ModelAndView mav = new ModelAndView("viewstudentbyid");
		ArrayList<StudentEnrolment> courses = lservice.FindCourseidCoursenameCreditById(uid);
		model.addAttribute("courses", courses);

		String studentName = lservice.findStudentById(uid).getFirstMidName() + " "
				+ lservice.findStudentById(uid).getLastName();
		model.addAttribute("studentName", studentName);

		return mav;
	}

	/* courseTaught */
	@RequestMapping(value = "/coursetaught/{uid}", method = RequestMethod.GET)
	public ModelAndView listCoursesTaught(@PathVariable Integer uid,HttpSession session) {
		User authenticatedUser = (User) session.getAttribute("USERSESSION");
		ModelAndView mav;
		if(authenticatedUser != null && authenticatedUser.getRole().getName().equalsIgnoreCase("lecturer")) {
			mav = new ModelAndView("CourseTaught");
			ArrayList<Course> courses = lservice.findCourseByAssignedLecturerID(uid);
			mav.addObject("courses", courses);
			return mav;
		}else
		{
			return new ModelAndView("redirect:/home/login");
		}
		
	}

	// view course enrollment
	@RequestMapping(value = "/{cid}", method = RequestMethod.GET)
	public ModelAndView listEnrollments(@PathVariable Integer cid, Model model) {
		ModelAndView mav = new ModelAndView("CourseEnrollment");
		Course course = lservice.findCourseById(cid);
		model.addAttribute("studentEnrollments", course.getStudentEnrolments());
		mav.addObject(model);
		return mav;
	}

	// grade list
	@RequestMapping(value = "/gradelist/{courseId}", method = RequestMethod.GET)
	public ModelAndView listGrade(@PathVariable Integer courseId, Model model) {
		ModelAndView mav = new ModelAndView("GradeList");
		Course course = lservice.findCourseById(courseId);
		model.addAttribute("studentEnrollments", course.getStudentEnrolments());
		model.addAttribute("courseid", courseId);
		mav.addObject(model);
		return mav;
	}

	@RequestMapping(value = "/create/{seid}", method = RequestMethod.GET)
	public ModelAndView gradeStudentPage(@PathVariable Integer seid, Model model) {
		ModelAndView mav = new ModelAndView("GradeAStudent");
		if (seid > 0) {
			StudentEnrolment studentEnrollment = lservice.findStudentEnrolment(seid);
			model.addAttribute("studentEnrollment", studentEnrollment);
		}
		return mav;
	}

	@RequestMapping(value = "/create/{seid}", method = RequestMethod.POST)
	public ModelAndView editStudent(@ModelAttribute @Valid StudentEnrolment studentEnrollment,
			@PathVariable Integer seid, BindingResult result, final RedirectAttributes redirectAttributes,HttpSession session) {
		
		if (result.hasErrors())
			return new ModelAndView("GradeAStudent");
		//User authenticatedUser = (User) session.getAttribute("USERSESSION");
		
		lservice.updateStudent(seid,studentEnrollment.getScore());
		int courseid = studentEnrollment.getCourse().getCourseid();
		//ModelAndView mav =  new ModelAndView("redirect:/lecturer/gradelist/"+courseid); //new ModelAndView("redirect:/lecturer/gradelist/" + courseid);
		String message = "Score was successfully input.";
		redirectAttributes.addFlashAttribute("message", message);
		return new ModelAndView("redirect:/lecturer/gradelist/"+courseid);

	}
}
