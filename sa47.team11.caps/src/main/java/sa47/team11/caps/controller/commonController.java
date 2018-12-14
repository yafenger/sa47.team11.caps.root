package sa47.team11.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sa47.team11.caps.service.UserService;
import sa47.team11.caps.model.User;

@Controller
@RequestMapping(value = "/home")
public class commonController {
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logic(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("user") User user, HttpSession session, BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("login");
		if (user.getEmail() != null && user.getPassword() != null) {
			User authorizeUser = uService.authenticate(user.getEmail(), user.getPassword());
			if(authorizeUser == null) {
				return new ModelAndView("login");
			}else {
				session.setAttribute("USERSESSION", authorizeUser);
			}
			User authenticatedUser = (User) session.getAttribute("USERSESSION");
			if(authenticatedUser.getRole().getName().equalsIgnoreCase("administrator")) {
				return new ModelAndView("redirect:/course/index");
			}else if(authenticatedUser.getRole().getName().equalsIgnoreCase("Lecturer")) {
				return new ModelAndView("redirect:/lecturer/coursetaught/"+authenticatedUser.getUserid()); 
			}else if(authenticatedUser.getRole().getName().equalsIgnoreCase("Student")) {
				return new ModelAndView("redirect:/course/enroll");
			}else {
				return new ModelAndView("login");
			}
			
		} else {
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home/login";

	}
}
