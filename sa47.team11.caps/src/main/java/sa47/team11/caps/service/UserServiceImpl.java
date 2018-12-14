package sa47.team11.caps.service;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sa47.team11.caps.model.User;
import sa47.team11.caps.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	// Retrieve all user from DB
	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> usrep = userRepository.getAllUsers();
		return usrep;
	}

	// Retrieve user with the specific ID
	@Override
	@Transactional
	public User findStudentById(Integer userId) {
		return userRepository.findStudentById(userId);
	}

	// Create new student record
	@Override
	@Transactional
	public void createStudent(String firstMidName, String lastName, Date dateJoined, String email, String password) {
		userRepository.createStudent(firstMidName, lastName, dateJoined, email, password);
	}

	// Edit student record
	@Override
	@Transactional
	public void editStudent(String firstMidName, String lastName, String status, Integer userid) {
		userRepository.editStudent(firstMidName, lastName, status, userid);
	}

	// Delete student record
	@Override
	public void removeUser(User user) {
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public User authenticate(String uname, String pwd) {
		User u = userRepository.findUserByNamePwd(uname, pwd);
		return u;
	}

	@Override
	@Transactional
	public ArrayList<User> getUserStudentRole() {
		ArrayList<User> u = (ArrayList<User>) userRepository.getStudent();
		return u;
	}

	@Override
	@Transactional
	public User findUser(Integer userId) {
		return userRepository.findById(userId).get();
	}
}
