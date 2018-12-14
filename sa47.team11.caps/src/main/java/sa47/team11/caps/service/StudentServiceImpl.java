package sa47.team11.caps.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sa47.team11.caps.model.*;

import sa47.team11.caps.repository.*;
import sa47.team11.caps.service.*;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	StudentEnrolRepository srepo;
//	StudentEnrolmentRepository srepo;
	// UserRepository urepo;
	@Resource
	UserRepository urepo;

	@Override
	@Transactional
	public ArrayList<StudentEnrolment> findAllCourses(int userId) {
//TODO Auto-generated method stub

		return (ArrayList<StudentEnrolment>) srepo.findAllCourses(userId);
//return return (ArrayList<StudentEnrolment>) srepo.findAll();
//}

	}

	@Override
	public User findUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
//public  CancelCourse(int studentEnrolId) {
//	// TODO Auto-generated method stub
//  srepo.CancelCourse(studentEnrolId, "cancelled");
//  return(ArrayList<StudentEnrolment>) srepo.CancelCourse(studentEnrolId,"cancelled");
//	}
//

	@Transactional
	@Override
	public void CancelCourse(int studentEnrolId) {
		srepo.CancelCourse(studentEnrolId, "cancelled");

	}

//	@Override
//	public void CancelCourse(int studentEnrolId) {
//		// TODO Auto-generated method stub
//		  return;
//	}
//	
	@Override
	@Transactional
	public User updateStudent(User u) {
		// TODO Auto-generated method stub
		return urepo.saveAndFlush(u);
	}

	@Override
	@Transactional
	public User findUser(Integer UserId) {
		// TODO Auto-generated method stub
		System.out.println("UserId" + UserId);

		User student = urepo.findById(UserId).get();
		System.out.println(student.toString());
		return student;
	}

	@Override
	@Transactional
	public ArrayList<User> findUserByCriteria(User student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void editStudent(Integer uid, String passTochange) { /// *String password, Integer userid*/
		// TODO Auto-generated method stub

		urepo.editStudent(uid, passTochange);
		// return res;
		/* urepo.editStudent(password, userid); */
	}

}