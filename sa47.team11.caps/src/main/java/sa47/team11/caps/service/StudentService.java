package sa47.team11.caps.service;

import java.util.ArrayList;

import sa47.team11.caps.model.*;

public interface StudentService {

//	ArrayList<User> findAllStudents();

	public interface findAllCourse {

	}

	User findUser(String email);

//	ArrayList<User> findStudentsByCriteria(User student);

	ArrayList<StudentEnrolment> findAllCourses(int userid);

	void CancelCourse(int studentEnrolId);

//	ArrayList<StudentEnrolment> findAllGrade();

//	ArrayList<StudentEnrolment> ViewGrade(int cid);
	
	User updateStudent(User u);

	User findUser(Integer UserId);

	ArrayList<User> findUserByCriteria(User student);
	
	void editStudent(Integer uid,String passTochange);
	
	
	

}