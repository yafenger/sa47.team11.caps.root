<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="${pageContext.request.contextPath}/student/create" class="btn btn-info">Add Student</a>
<c:if test="${fn:length(ulist) gt 0 }">
	<table class="table table-striped">
	  		<thead>
				<tr class="listHeading">
					<th>#</th>
					<th><spring:message code="fieldLabel.firstMidName" /></th>
					<th><spring:message code="fieldLabel.lastName" /></th>
					<th><spring:message code="fieldLabel.enrollmentDate" /></th>
					<th><spring:message code="caption.edit" /></th>
					<th><spring:message code="caption.delete" /></th>
				</tr>
			</thead>

	  <tbody>
		 <c:forEach items="${ulist}" var="user" varStatus="index">
			<tr>
		      <th scope="row">${index.index+1}</th>
		      <td>${user.firstMidName}</td>
		      <td>${user.lastName}</td>
		      <td>${user.dateJoined}</td>
		      <td><a href="${pageContext.request.contextPath}/student/edit/${user.userid}">Edit User</a></td>
		      <td><a href="${pageContext.request.contextPath}/student/delete/${user.userid}">Delete User</a></td>
		    </tr>
			</c:forEach> 
		</tbody>
	</table>
</c:if>