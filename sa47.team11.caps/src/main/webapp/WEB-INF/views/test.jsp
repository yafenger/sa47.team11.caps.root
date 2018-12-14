
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<head>
</head>

<body>
enrollment has done successfully</br>
<a href="${pageContext.request.contextPath}/course/enroll.html">
						<s:message code="Back to Enrollment" /></a>
</body>
</html>