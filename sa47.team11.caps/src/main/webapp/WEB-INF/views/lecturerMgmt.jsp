
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Lecturer Management</h3>
<br>

<a href="${pageContext.request.contextPath}/manageLecturer/courses/unassigned" class="btn btn-info">View Unassigned
		Courses</a><br/><br/>
<a href="${pageContext.request.contextPath}/manageLecturer/create" class="btn btn-info">Add
	Lecturer</a>
	<br />
	<br />
	<table class="table">
		<tr>
			<th>#</th>
			<th><s:message code="label.lecturer.id" /></th>
			<th><s:message code="label.lecturer.firstMidName" /></th>
			<th><s:message code="label.lecturer.lastName" /></th>
			<th><s:message code="label.lecturer.dateJoined" /></th>
			<th><s:message code="label.lecturer.email" /></th>
			<th><s:message code="label.lecturer.status" /></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="user" items="${lecturerList}" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${user.userid}</td>
				<td>${user.firstMidName}</td>
				<td>${user.lastName}</td>
				<td><fmt:formatDate pattern="dd MMM yyyy" value="${user.dateJoined }"/></td>
				<td>${user.email}</td>
				<td>${user.status}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/manageLecturer/courses/${user.userid}.html">
						<s:message code="label.lecturer.courses" />
				</a></td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/manageLecturer/edit/${user.userid}.html">
						<s:message code="label.lecturer.edit" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
	