package sa47.team11.caps.service;

import java.util.ArrayList;

import sa47.team11.caps.model.Course;
import sa47.team11.caps.model.StudentEnrolment;
import sa47.team11.caps.model.User;

public interface LecturerService {
	ArrayList<StudentEnrolment> FindCourseidCoursenameCreditById(int uid);   //function from repository

	
	int updateUser(User u);

	User findUserById(Integer id);

	ArrayList<Course> findCourseByAssignedLecturerID(Integer uid);

	Course findCourseById(Integer cid);

	int updateStudent(Integer seid, Integer score);

	StudentEnrolment findStudentEnrolment(Integer seid);

	StudentEnrolment createStudentEnrolment(StudentEnrolment se);
	
	User findStudentById(Integer id);
}
