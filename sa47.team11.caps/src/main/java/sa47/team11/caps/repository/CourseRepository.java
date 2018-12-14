package sa47.team11.caps.repository;

import java.util.ArrayList;
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


	@Query(value = "select * from Course where NULLIF(assigned_lecturerID, ' ') IS NULL", nativeQuery = true)
	ArrayList<Course> findUnassignedCourses();
	
	@Query(value = "Select * from Course c where c.ASSIGNED_LECTURERID=:userid", nativeQuery = true)
	ArrayList<Course> findLecturerCourses(@Param("userid")Integer userid); 
	
	@Query("select c from Course c where c.courseid =:courseid")
	Course findCourse(@Param("courseid")Integer courseid);
	
	@Query(value = "select c.Assigned_lecturerID from Course c where courseid = :courseid", nativeQuery=true)
	Integer getLecturerID(@Param("courseid")Integer courseid);

	@Query(value = "SELECT * FROM caps_team11.course c where c.assigned_lecturerid=:uid",nativeQuery=true)
	ArrayList<Course> findCourseByAssignedLecturerID(@Param("uid") Integer uid);
	
	/*@Modifying
	@Query(value="delete s FROM student_enrolment s WHERE s.courseid=:cid and s.studentid=:sid",nativeQuery=true)
//	@Query("Delete s from student_enrolment s where ")  delete s FROM student_enrolment s WHERE s.courseid=4 and s.studentid=44
	void deleteCourseEnrolled(@Param("cid")Integer cid,@Param("sid")Integer sid);*/
	
	@Modifying
	@Query(value="INSERT INTO `student_enrolment`(`COURSEID`,`STUDENTID`,`DATE_REGISTERED`,`STATUS_ENROLL`)VALUES (:cid, :sid, now(),:sta)", nativeQuery=true)
	void createEnrollment(@Param("cid") Integer cid, 
			@Param("sid") Integer sid, @Param("sta") String sta);
}
