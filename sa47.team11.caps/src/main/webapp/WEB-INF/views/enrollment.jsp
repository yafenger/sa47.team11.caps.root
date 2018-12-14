
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(courselst) gt 0 }">
	<table class="table table-striped">
	  		<thead>
				<tr class="listHeading">
					<th><s:message code="fieldLabel.courseId" /></th>
					<th><s:message code="fieldLabel.courseName" /></th>
					<th><s:message code="Course Description" /></th>
					<th><s:message code="Enrollment" /></th>
				</tr>
			</thead>

	  <tbody>
		 <c:forEach var="course" items="${courselst}">
			<tr>
		      <th scope="row">${course.courseid}</th>
		      <td>${course.courseName}</td>
		      <td>${course.courseDescription}</td>
		      <td align="center">
		      <a
						href="${pageContext.request.contextPath}/course/test/${course.courseid}/${sessionScope.USERSESSION.getUserid()}.html">
						<s:message code="Enroll" /></a></td>

		    </tr>
			</c:forEach> 
		
			
			</tbody>
			
	</table>
</c:if>