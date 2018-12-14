package sa47.team11.caps.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sa47.team11.caps.model.User;
import sa47.team11.caps.service.UserService;

@Controller
@RequestMapping(value="/student")
public class UserController {

	@Autowired
	UserService uservice;


	//Show list of Student
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView listAllStudent()
	{
		ArrayList<User> ulist = uservice.getAllUsers();
		ModelAndView mav = new ModelAndView("UserCRUD");
		mav.addObject("ulist",ulist);
		return mav;
	}
	
	//Create New Student
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage() {
		ModelAndView mav = new ModelAndView("UserNewForm", "user", new User());
		return mav;
	}
	
    @RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView newStudentPage(@ModelAttribute User user) {
		uservice.createStudent(user.getFirstMidName(),user.getLastName(),user.getDateJoined(),user.getEmail(),user.getPassword());
		ArrayList<User> ulist = uservice.getAllUsers();
		ModelAndView mav = new ModelAndView("UserCRUD");
		mav.addObject("ulist", ulist);
		return mav;
	}
	
	//Edit Student
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		User user = uservice.findStudentById(id);
		ModelAndView mav = new ModelAndView("UserEditForm", "user", user);
		return mav;
		
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editUserPage(@PathVariable Integer id, @ModelAttribute @Valid User user, BindingResult result) {
		uservice.editStudent(user.getFirstMidName(),user.getLastName(),user.getStatus(),user.getUserid());
		ArrayList<User> ulist = uservice.getAllUsers();
		ModelAndView mav = new ModelAndView("UserCRUD");
		mav.addObject("ulist", ulist);
		return mav;
	}
	
	//Delete student record
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) {
		User user = uservice.findStudentById(id);
		uservice.removeUser(user);
		ArrayList<User> ulist = uservice.getAllUsers();
		ModelAndView mav = new ModelAndView("UserCRUD");
		mav.addObject("ulist", ulist);
		return mav;
	}

	
}
