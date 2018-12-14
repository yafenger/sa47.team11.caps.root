<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> --%>
<!--  -->
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
Course Cancelled successfully.
<a href="${pageContext.request.contextPath}/studentcourse/list/${sessionScope.USERSESSION.getUserid()}.html">
					<s:message code="return" /></a>