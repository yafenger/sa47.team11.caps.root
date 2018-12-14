<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/logout" var="logout" />
<ul class="nav navbar-nav">

	<c:choose>
		<c:when
			test="${sessionScope.USERSESSION.getRole().getName() eq 'Administrator' }">

			<li class="active"><spring:url value="/course/index" var="ulist"
					htmlEscape="true" /> <a href="${ulist}"> <%-- <spring:message 	code="menu.admin.ulist" /> --%>
					Manage Course
			</a></li>
			<li><spring:url value="/manageLecturer/list" var="llist"
					htmlEscape="true" /> <a href="${llist}"> Manage Lecturer <%-- 	<spring:message
								code="menu.admin.rlist" /> --%>
			</a></li>
			<li><spring:url value="/student/index" var="slist"
					htmlEscape="true" /> <a href="${slist}"> Manage Student <%-- 	<spring:message
								code="menu.admin.rlist" /> --%>
			</a></li>
			<li><spring:url value="/enrol/list" var="elist"
					htmlEscape="true" /> <a href="${elist}"> Manage Enrollment <%-- 	<spring:message
								code="menu.admin.rlist" /> --%>
			</a></li>
		</c:when>

		<c:when
			test="${sessionScope.USERSESSION.getRole().getName()  eq 'Lecturer' }">
			<li><spring:url
					value="/lecturer/coursetaught/${sessionScope.USERSESSION.getUserid()}"
					var="lect" htmlEscape="true" /> <a href="${lect}"> View
					Courses Taught </a></li>
			<li><spring:url value="/enrol/list" var="elist"
					htmlEscape="true" /> <a href="${elist}"> View Student
					Enrollment </a></li>

		</c:when>
		<c:when
			test="${sessionScope.USERSESSION.getRole().getName() eq 'Student' }">
			<li><spring:url
					value="/studentprofile/list/${sessionScope.USERSESSION.getUserid()}"
					var="lect" htmlEscape="true" /> <a href="${lect}"> <%-- <spring:message
								code="menu.personalHistory" /> --%> View Student Profile
			</a></li>
			<li><spring:url
					value="/studentcourse/list/${sessionScope.USERSESSION.getUserid()}"
					var="enrol" htmlEscape="true" /> <a href="${enrol}"> <%-- <spring:message
								code="menu.personalHistory" /> --%> View Enrollment
			</a></li>
			<li><spring:url value="/course/enroll" var="ce"
					htmlEscape="true" /> <a href="${ce}"> <%-- <spring:message
								code="menu.personalHistory" /> --%> Course Catalog
			</a></li>
		</c:when>

	</c:choose>
	<c:if test="${sessionScope.USERSESSION.getUserid() gt 0}">
	<li><spring:url value="/home/logout" var="logout"
			htmlEscape="true" /> <a href="${logout}"> <spring:message
				code="menu.logout" />
	</a></li>
	</c:if>

</ul>


