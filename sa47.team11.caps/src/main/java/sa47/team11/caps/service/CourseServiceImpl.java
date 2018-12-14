package sa47.team11.caps.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sa47.team11.caps.model.Course;
import sa47.team11.caps.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Resource
	private CourseRepository courseRepository;
	
	@Override
	@Transactional
	public List<Course> getAllCourses() {
		List<Course> res = courseRepository.findAll();
		return res;
	}
	
	@Override
	@Transactional
	public Course getCoursebyId(int id) {
		Course res = courseRepository.getCoursebyId(id);
		return res;
	}
	
	@Override
	@Transactional
	public int updateCourse(Course c) {
		//try {
			//return courseRepository.saveAndFlush(c);
			
			int res= courseRepository.updateCourses(c.getCourseid(),c.getCourseName(),c.getClassSize()
					,c.getCourseDescription(),c.getStartDate(),c.getEndDate(),c.getStartTime(),c.getEndTime(),c.getExamTime());
		
			return res;
		//}catch(Exception e) {return 0;}
	}
	
	@Override
	@Transactional
	public int createCourse(Course s) {
	/*	System.out.println("courseid " + s.getCourseid());
		System.out.println("startdate " + s.getStartTime());
		System.out.println("endTime " + s.getEndTime());*/
		
		return courseRepository.insertCourse(s.getCourseName(),s.getClassSize(),s.getCourseDescription(),s.getStartDate(),s.getEndDate(),s.getStartTime(),s.getEndTime(),s.getExamTime());
		               //  @Param("endDate")Date endDate,@Param("endTime")Date endTime,@Param("startTime")Date startTime);
	}
	
	@Override
	@Transactional
	public void removeStudent(Course c) {
		courseRepository.delete(c);
	}
	
	@Override
	@Transactional
	public int removeEnrollment(int cid,int sid) {
		//courseRepository.deleteCourseEnrolled(cid,sid);
		return 0;
	}

	@Override
	@Transactional
	public void createEnrollment(Integer cid,Integer sid,String sta) {
		courseRepository.createEnrollment(cid, sid, sta);
	}

}
