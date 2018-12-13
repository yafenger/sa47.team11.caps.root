<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/logout" var="logout" />
<ul class="nav navbar-nav">
	               <!--  <li class="active"><a href="https://www.tutorialrepublic.com" target="_blank">Home</a></li>
	                <li><a href="https://www.tutorialrepublic.com/about-us.php" target="_blank">About</a></li>
	                <li><a href="https://www.tutorialrepublic.com/contact-us.php" target="_blank">Contact</a></li>
	            -->
	<%-- <c:if test="${sessionScope.USERSESSION.user.getRole().size() gt 0}"> --%>
		<%-- <c:forEach var="role" items="${sessionScope.USERSESSION.User.getRole()}"> --%>
			<c:choose>
				<c:when test="${sessionScope.USERSESSION.getRole().getName() eq 'Administrator' }">

					<li class="active">
						<spring:url value="/course/list" var="ulist" htmlEscape="true" /> 
						<a href="${ulist}"> 
							<%-- <spring:message 	code="menu.admin.ulist" /> --%>
							Manage Course
						</a>
					</li>
					<li><spring:url value="/manageLecturer/list" var="rlist"
							htmlEscape="true" /> <a href="${rlist}"> 
							Manage Lecturer
						<%-- 	<spring:message
								code="menu.admin.rlist" /> --%>
					</a></li>
					<li><spring:url value="/student/index" var="rlist"
							htmlEscape="true" /> <a href="${rlist}"> 
							Manage Student
						<%-- 	<spring:message
								code="menu.admin.rlist" /> --%>
					</a></li>
				</c:when>

				<c:when test="${sessionScope.USERSESSION.getRole().getName()  eq 'Lecturer' }">
					<li>
					<spring:url value="/lecturer/coursetaught/{sessionScope.USERSESSION.getUserid()}" var="lect"
							htmlEscape="true" /> <a href="${lect}"> 
							<%-- <spring:message
								code="menu.personalHistory" /> --%>
								View Courses Taught
					</a></li>
					

				</c:when>
				<c:when test="${sessionScope.USERSESSION.getRole() eq 'Student' }">
					<li>
					<spring:url value="/studentprofile/list/{sessionScope.USERSESSION.getUserid()}" var="lect"
							htmlEscape="true" /> <a href="${lect}"> 
							<%-- <spring:message
								code="menu.personalHistory" /> --%>
								View Student Profile
					</a></li>
					<li>
					<spring:url value="/studentcourse/list/{sessionScope.USERSESSION.getUserid()}" var="enrol"
							htmlEscape="true" /> <a href="${enrol}"> 
							<%-- <spring:message
								code="menu.personalHistory" /> --%>
								view enrollment
					</a></li>
					<li>
					<spring:url value="/studentcourse/viewgrade/{sessionScope.USERSESSION.getUserid()}" var="grade"
							htmlEscape="true" /> <a href="${enrol}"> 
							<%-- <spring:message
								code="menu.personalHistory" /> --%>
								view Grade
					</a></li>
				</c:when>
			</c:choose>

		<%-- </c:forEach> --%>
	<%-- </c:if>
 --%>
</ul>

	 