
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.Duration"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

   <a href="${pageContext.request.contextPath}/studentcourse/viewgrade/${sessionScope.USERSESSION.getUserid()}">View Grade</a>

<%--     <c:if test="${fn:length(users) gt 0 }"> 
   <table>
     <tr>
       <td>#</td>
       <td>COURSEID</td>
       <td>DATE_REGISTERED</td>
       <td>STATUS_ENROLL</td>
   
     </tr>
     <c:forEach items="${StudentCourse}" var="user" varStatus="index">
         <tr>
       <td>${user.dateRegistered}</td>
       <td>${user.studentEnrolId}</td>
        
         <td>${user.course.courseName}</td>
         <td>${user.statusEnroll}</td>
<td><a
					href="${pageContext.request.contextPath}/studentcourse/cancelcourse/${user.studentEnrolId}.html">
					<s:message code="Cancel" /></a></td> 
       <td>${user.STATUS_ENROLL}</td>
 <td>${user.DATE_REGISTERED}</td>
     </tr>
     </c:forEach>
   </table>
   </c:if> --%>

<div class="table-responsive">
  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Course Id</th>
        <th scope="col">Course Name</th>
        <th>Date Registered</th>
        <th scope="col">Credits</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
      
      </tr>
    </thead>
    <tbody>
         <c:forEach items="${StudentCourse}" var="user" varStatus="index">
		
			<tr>
		<td>${index.index+1}</td>  
		<td>${user.course.courseid}</td>  
		<td>${user.course.courseName}</td>
		<td id="dateReg">${user.dateRegistered}</td>
		<td>${user.course.credit}<c:set var = "totalcredit" value = "${credit + user.course.credit}"/><c:set var = "credit" value = "${user.course.credit}"/></td>
		
			<td>${user.statusEnroll}</td>



			
		

<!--  LocalDate today = LocalDate.now(); -->

<!--  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); -->
			        
<!--  String dd = (String)pageContext.getAttribute("user.dateRegistered"); -->
<!--  out.println("zap" + dd); --> 
<!-- LocalDate date2 = LocalDate.parse(dd, formatter);  -->
<!-- System.out.println("Next month: " + date2);  -->

<!-- Put latest date 1st and old date 2nd in 'between' method to get -ve date difference -->
<!-- long daysDiff = ChronoUnit.DAYS.between(date2, today); -->
<!-- pageContext.setAttribute("daydiff", daysDiff);  -->
	        	
<!-- pageContext.setAttribute("daydiff", daysDiff); -->
	
		<td>	
			<%-- <c:if test="${daydiff gt 14}">  --%>
			
			   <a  href="${pageContext.request.contextPath}/studentcourse/cancelcourse/${user.studentEnrolId}.html">
							<s:message code="Cancel" /></a>
			<%--  </c:if> --%>
			 
			</td>
		</tr>
		</c:forEach>
    </tbody>
  </table>
</div>


<%-- <script>
	$(document).ready(function(){ -->
		
var dateReg =new Date( $("#dateReg").text()); -->
var today = new Date(); -->
var diff  = new Date(today - dateReg); -->
var days  = diff/1000/60/60/24; -->
if(days > 14){ -->
$("#btncancel").hide();//attr("disabled",true); -->
}else $("#btncancel").show(); //attr("disabled",false); -->
//	diff = Math.round(Math.abs((today.getTime() - dateReg.getTime())/(oneDay))); -->
	}) -->
</script>
 --%>

</body>
</html>
