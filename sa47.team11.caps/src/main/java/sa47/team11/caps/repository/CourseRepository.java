package sa47.team11.caps.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sa47.team11.caps.model.Course;

/*@Repository
@Transactional*/
public interface CourseRepository extends JpaRepository<Course,Integer> {
	/*@Query("SELECT r.courseName FROM Course r")
	List<String> getCourseName();*/
	
	@Query("SELECT r FROM Course r WHERE r.courseid=:courseId")
	Course getCoursebyId(@Param("courseId") int id);
	
	

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Course r SET r.classSize=:classSize,r.courseName=:courseName,r.courseDescription =:courseDesc"
			+ ",r.startDate=:startDate,r.endDate=:endDate,r.endTime=:endTime,r.startTime=:startTime,r.examTime=:examTime WHERE r.courseid=:courseId")
	int updateCourses(@Param("courseId") int cid,@Param("courseName") String courseName, @Param("classSize") int classSize,@Param("courseDesc") String courseDesc
			,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("examTime") Date examTime);

	
	//@Query("INSERT INTO Course (courseName, courseDescription,classSize,startDate,endDate,startTime,endTime) VALUES (?, ?)")
	@Modifying
	@Query(value = "insert into Course (course_Name, course_Description,class_Size,start_Date,end_Date,start_Time,end_Time,exam_Time) VALUES (:courseName,:courseDesc,:classSize,:startDate,:endDate,:startTime,:endTime,:examTime)", nativeQuery = true)
	int insertCourse(@Param("courseName") String courseName, @Param("classSize") int classSize,@Param("courseDesc") String courseDesc,@Param("startDate")Date startDate
			,@Param("endDate")Date endDate,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("examTime") Date examTime);
}
