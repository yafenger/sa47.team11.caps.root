package sa47.team11.caps.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sa47.team11.caps.model.StudentEnrolment;

public interface StudentEnrolRepository extends JpaRepository<StudentEnrolment, String> {

	@Query
	(value = "SELECT * from Student_Enrolment c WHERE c.StudentID = null", nativeQuery = true)
	ArrayList<StudentEnrolment> findCoursesByCID(@Param("cid") Integer courseID);
	
	@Query
	(value = "SELECT * from Student_Enrolment c WHERE c.StudentID=:userid", nativeQuery = true)
	ArrayList<StudentEnrolment> findAllStudentEnrolmentbyCID(@Param("userid") int userid);
	
	@Query
	(value = "SELECT c from Student_Enrolment c WHERE c.DateRegistered != null", nativeQuery = true)
	ArrayList<StudentEnrolment> findStudentEnrolment();
	
	/*@Modifying
	@Query
	(value = "UPDATE student_enrolment set Status_Enroll=: statusEnroll WHERE studentid=: userid", nativeQuery = true)
	void editEnrolment(@Param ("statusEnroll") String statusEnroll, @Param("userid") Integer userid);*/
	@Modifying
	@Query(value = "UPDATE STUDENT_ENROLMENT set STATUS_ENROLL = :status where STUDENT_ENROL_ID = :studentEnrolId", nativeQuery=true)
	void editEnrolment(@Param("status") String status, @Param("studentEnrolId") Integer studentEnrolId);

	@Query(value="SELECT * FROM student_enrolment se WHERE se.STUDENTID =:UserId and courseid=:courseid",nativeQuery=true)
    StudentEnrolment getEnrolledStudent(Integer UserId,Integer courseid);
	
	@Modifying
	@Query(value="INSERT INTO student_enrolment(COURSEID,STUDENTID,DATE_REGISTERED,STATUS_ENROLL,SCORE) VALUES(:courseid, :studentid, now(), :status,:score)", nativeQuery=true)
	void createEnrolment(@Param("courseid") int courseid, @Param("studentid") int studentid,@Param("score") int score,@Param("status") String status);
	
	/*@Modifying
	@Query(value="SELECT * FROM student_enrolment se WHERE se.COURSEID =:CourseId and se.STUDENTID =:UserId",nativeQuery=true)
    StudentEnrolment getEnrolledStudent(Integer UserId,Integer CourseId);*/
	
	@Query("SELECT c from StudentEnrolment c WHERE c.user.userid= :userid")
	ArrayList<StudentEnrolment> findAllCourses(@Param("userid") Integer userid);

	
	@Modifying
	@Query(value = "UPDATE STUDENT_ENROLMENT set STATUS_ENROLL = :status where STUDENT_ENROL_ID = :studentEnrolId", nativeQuery=true)
	void CancelCourse(@Param("studentEnrolId") Integer studentEnrolId,@Param("status") String status);
}
