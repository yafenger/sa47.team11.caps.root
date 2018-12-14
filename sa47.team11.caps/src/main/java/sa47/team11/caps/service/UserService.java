package sa47.team11.caps.service;

import java.util.ArrayList;
import java.util.Date;

import sa47.team11.caps.model.User;

public interface UserService {

	//Retrieve all user from DB
	ArrayList<User>getAllUsers();
	
	//Retrieve user with the specific ID
	User findStudentById(Integer userId);
	
	//Create new student record	
	void createStudent(String firstMidName, String lastName, Date dateJoined,String email,String password);
	
	//Edit student record
	void editStudent(String firstMidName, String lastName, String status, Integer userid);
	
	//Delete student record
	void removeUser(User user);
	
	User authenticate(String uname, String pwd);
	
	ArrayList<User> getUserStudentRole();
	
	User findUser(Integer userId);
}
