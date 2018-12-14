<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<div class="container">
  <form:form class="form-horizontal" method="POST" modelAttribute="course"
  	action="${pageContext.request.contextPath}/course/edit/${course.courseid}.html" >
  	
  	<form:hidden path="courseid" />
    <div class="form-group">
      <label class="control-label col-sm-2" for="courseName">
      	<spring:message code="fieldLabel.courseName" />
      </label>
      <div class="col-sm-4">
     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
     
     
     		<form:input path="courseName" required="required"  oninput="this.setCustomValidity('')" oninvalid="this.setCustomValidity('Please Enter Course Name')" class="form-control" placeholder="course name" />
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="classSize">
      	Class Size
      </label>
      <div class="col-sm-4">
     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
     	<form:input path="classSize" required="required" oninput="this.setCustomValidity('')" oninvalid="this.setCustomValidity('Please Enter Class Size')"  class="form-control" placeholder="class Size" />
      </div>
    </div>

      <div class="form-group">
      <label class="control-label col-sm-2" for="courseid">
      	Course Start Date
      </label>
      <div class="col-sm-4">
     	<form:input path="startDate" required="required" onchange="this.setCustomValidity('')" oninvalid="this.setCustomValidity('Please Enter Start Date')"  class="form-control" placeholder="start Date" />
      </div>
    </div>
    
    
      <div class="form-group">
	      <label class="control-label col-sm-2" for="endDate">
	      	Course End Date
	      </label>
	      <div class="col-sm-4">
		     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
		     <form:input path="endDate" required="required" onchange="this.setCustomValidity('')" oninvalid="this.setCustomValidity('Please Enter End Date')"  class="form-control" placeholder="end Date" />
	      </div>
    </div>
    
    
    
    <div class="form-group">
	      <label class="control-label col-sm-2" for="startTime">
	      	Course Start Time
	      </label>
	      <div class="col-sm-4">
		     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
		     <form:input path="startTime"   required="required"  class="form-control" placeholder="Start Time" />
	      </div>
    </div>   
    <div class="form-group">
	      <label class="control-label col-sm-2" for="endTime">
	      	Course End Time
	      </label>
	      <div class="col-sm-4">
		     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}"-->
		     <form:input path="endTime" required="required"  class="form-control"  placeholder="End Time" />
	      </div>
    </div>
    
    <div class="form-group">
	      <label class="control-label col-sm-2" for="firstMidName">
	       	Exam Date
	      </label>
	      <div class="col-sm-4">
		     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
		     <form:input path="examTime" class="form-control" placeholder="Exam Date" />
	      </div>
    </div>
    
    <div class="form-group ">
      <label class="control-label col-sm-2" for="courseDescription">
		Course Detail
	</label>
      <div class="col-sm-4 md-form amber-textarea active-amber-textarea">          
       <!--  <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"> -->
      	<form:textarea path="courseDescription" oninput="this.setCustomValidity('')" required="required" oninvalid="this.setCustomValidity('Please Enter Course Description')"  class="md-textarea form-control z-depth-1" row="10"></form:textarea>
      </div>
    </div>

    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
       <!--  <button type="submit" class="btn btn-default">Submit</button> -->
        <c:if test="${course.courseid gt 0}">
	       <form:button name="submit" class="btn btn-default" type="submit">
		       Update
	       </form:button>
	       <form:button name="reset" class="btn btn-default" type="submit">
		       Reset
	       </form:button>
       </c:if>
       <c:if test="${course.courseid == 0}">
	       <form:button name="submit" class="btn btn-default" type="submit">
		       Insert
	       </form:button>
	       <a class="btn btn-default" href="${pageContext.request.contextPath}/course/index">
	       		Back
	       </a>
       </c:if>
      </div>

    </div>
  </form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		 $("#endDate").datepicker({ dateFormat: "dd/mm/yy" }).val();//datepicker({ dateFormat: 'dd/MM/yyyy' });
		$("#startDate").datepicker({ dateFormat: "dd/mm/yy" }).val();//datepicker({ dateFormat: 'dd/MM/yyyy' }); 
		 $('#endTime').timepicker({ 'scrollDefault': 'now' });
		 $('#startTime').timepicker({ 'scrollDefault': 'now' }); 
		 $("#examTime").datepicker({ dateFormat: "dd/mm/yy" }).val();
	});
</script>