package sa47.team11.caps.service;

import java.util.List;

import sa47.team11.caps.model.Course;

public interface CourseService {
	List<Course> getAllCourses();
	//List<String> getCourseNames();
	Course getCoursebyId(int id);
	
	int updateCourse(Course c);
	
	int createCourse(Course c);
	void removeStudent(Course c);
}
