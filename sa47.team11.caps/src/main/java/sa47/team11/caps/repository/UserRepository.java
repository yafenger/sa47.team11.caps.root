package sa47.team11.caps.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sa47.team11.caps.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
    
	@Query("SELECT u FROM User u WHERE u.email=:un AND u.password=:pwd")
	User findUserByNamePwd(@Param("un") String uname, @Param("pwd") String pwd);


	@Query("SELECT l FROM User l where l.userid =:userid")
	User findLecturerById(@Param("userid") Integer userid);
	
	@Query("SELECT l FROM User l INNER JOIN l.role r where r.name = 'Lecturer'")
	ArrayList<User> findAllLecturers();
	
	@Modifying
	@Query(value="update user set first_Mid_Name=:firstMidName, last_Name=:lastName, `STATUS`=:status where userid=:userid", nativeQuery=true)
	void editLecturer(@Param("firstMidName") String firstMidName, 
			@Param("lastName") String lastName, @Param("status") String status, @Param("userid") Integer userid);
	
	@Modifying
	@Query(value="INSERT INTO `user`(`FIRST_MID_NAME`,`LAST_NAME`,`ROLEID`,`DATE_JOINED`,`EMAIL`,`PASSWORD`,`STATUS`)VALUES (:firstMidName, :lastName, '2', now(), :email, :password, 'active')", nativeQuery=true)
	void createLecturer(@Param("firstMidName") String firstMidName, 
			@Param("lastName") String lastName, @Param("email") String email,  @Param("password") String password);
	
	@Query("SELECT l FROM User l INNER JOIN l.role r where r.name = 'Student'")
	ArrayList<User> getStudent();
	
	@Query ("SELECT u FROM User u WHERE u.userid=:uid")
	User findUserById (@Param("uid") Integer uid);

	@Modifying
	@Query(value="update user set PASSWORD = :password where userid = :userid", nativeQuery=true)
	void editStudent(@Param("userid") Integer userid,@Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.userid=:uid")
	User findStudentByID(@Param("uid")Integer uid);
	
	
	//Show all student
	@Query("SELECT l FROM User l INNER JOIN l.role r where r.name = 'Student'")
	ArrayList<User> getAllUsers();
	
	//Show student by id
	@Query("SELECT l FROM User l where l.userid =:userid")
	User findStudentById(@Param("userid") Integer userid);
	
	//Add new student
	@Modifying
	@Query(value = "insert into User (FIRST_MID_NAME, LAST_NAME,ROLEID,DATE_JOINED,EMAIL,PASSWORD,STATUS) VALUES (:firstMidName,:lastName,'3',:dateJoined,:email,:password,'active')", nativeQuery = true)
	void createStudent(@Param("firstMidName") String firstMidName, @Param("lastName") String lastName,@Param("dateJoined") Date dateJoined,@Param("email")String email,@Param("password")String password);

	//Edit student
	@Modifying
	@Query(value="update user set first_Mid_Name=:firstMidName, last_Name=:lastName, `STATUS`=:status where userid=:userid", nativeQuery=true)
	void editStudent(@Param("firstMidName") String firstMidName, @Param("lastName") String lastName, @Param("status") String status, @Param("userid") Integer userid);
	
	
}
