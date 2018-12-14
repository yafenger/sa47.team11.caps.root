
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


   <a href="${pageContext.request.contextPath}/studentcourse/list/${sessionScope.USERSESSION.getUserid()}">View Course Enrolled</a>


<div class="table-responsive">
  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Course Id</th>
        <th scope="col">Course Name</th>
        <th scope="col">Credits</th>
        <th scope="col">Score</th>
        <th scope="col">Grade</th>
      </tr>
    </thead>
    <tbody>
         <c:forEach items="${ViewGrade}" var="user" varStatus="index">
		
			<tr>
		<td>${index.index+1}</td>  
		<td>${user.course.courseid}</td>  
		<td>${user.course.courseName}</td>
		<td>${user.course.credit}<c:set var = "totalcredit" value = "${credit + user.course.credit}"/><c:set var = "credit" value = "${user.course.credit}"/></td>
		<td>${user.score}</td>
<%-- 		<td>${course.user.userid}</td> --%>
		<td>
			<c:choose>
				<c:when test ='${user.score>80}'>A<c:set var = "coursegpa5" value = "5"/></c:when>
				<c:when test ='${user.score>70}'>B<c:set var = "coursegpa4" value = "4"/></c:when>
				<c:when test ='${user.score>60}'>C<c:set var = "coursegpa3" value = "3"/></c:when>
				<c:when test ='${user.score>50}'>D<c:set var = "coursegpa2" value = "2"/></c:when>
				<c:when test ='${user.score<50}'>F<c:set var = "coursegpa1" value = "1"/></c:when>
			</c:choose>
		</td>
		</tr>
		</c:forEach>
    </tbody>
  </table>
</div>


<div>
<c:set var="totalcredit" value="${0}"/>    
<c:forEach var="course" items="${ViewGrade}"> 
    <c:set var="totalcredit" value="${totalcredit + course.course.credit}" />
</c:forEach>
<c:set var = "CGPA" value = "${(coursegpa1*credit + coursegpa2*credit + coursegpa3*credit + coursegpa4*credit + coursegpa5*credit)/totalcredit}"/>
Total credits: <strong>${totalcredit}</strong>     CGPA:<strong>${CGPA}</strong> <c:choose><c:when test ='${CGPA>4}'>Honours</c:when><c:when test ='${CGPA>2.5}'>Merit</c:when><c:when test ='${CGPA<2.5}'><span style="color:red;">On probation</span></c:when></c:choose>
</div>
</body>
</html>

