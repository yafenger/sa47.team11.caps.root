
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="${pageContext.request.contextPath}/course/edit/${course.courseid}0.html" class="btn btn-default">Add New Course</a>
<c:if test="${fn:length(courselst) gt 0 }">
	<table class="table table-striped">
	  		<thead>
				<tr class="listHeading">
					<th><spring:message code="fieldLabel.courseId" /></th>
					<th><spring:message code="fieldLabel.courseName" /></th>
					<th><spring:message code="fieldLabel.courseDesc" /></th>
					<th><spring:message code="caption.edit" /></th>
					<th>
					<spring:message code="caption.delete" /> 
						
					</th>
				</tr>
			</thead>

	  <tbody>
		 <c:forEach var="course" items="${courselst}">
			<tr>
		      <th scope="row">${course.courseid}</th>
		      <td>${course.courseName}</td>
		      <td>${course.courseDescription}</td>
		      <td>
		     
		      <a href="${pageContext.request.contextPath}/course/edit/${course.courseid}.html">
						<spring:message	code="caption.edit" /></a>
				</td>
				<td>
			
				<a href="${pageContext.request.contextPath}/course/delete/${course.courseid}.html">
				<spring:message code="caption.delete" /></a>
			</td>
		    </tr>
			</c:forEach> 
			</tbody>
			
	</table>
</c:if>