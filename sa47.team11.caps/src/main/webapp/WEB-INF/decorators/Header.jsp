<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<h1 class="text-center">
		<small><spring:message code="title.application" /></small>
	</h1>
	<p>
		<c:if test="${not empty sessionScope.USERSESSION}">
			<spring:message code="caption.user" /> :
			<c:out value="${sessionScope.USERSESSION.getFirstMidName()}" /> ;
			<spring:message code="caption.role" /> :
			${sessionScope.USERSESSION.getRole().getName()} ;
		</c:if>
	</p>



