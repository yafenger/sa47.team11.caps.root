<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<form:form class="form-horizontal" method="POST" modelAttribute="studentenrol"
action="${pageContext.request.contextPath}/enrol/edit/${studentenrol.getUser().getUserid()}/${studentenrol.getCourse().getCourseid()}.html" >
<center> 
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
			
	

</table>
</center>	

    
    
            <div class="form-group">
      <label class="control-label col-sm-2" for="dateRegistered">
      	StudentEnrolment ID
      </label>
      <div class="col-sm-4">
     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
     <form:input path="studentEnrolId" class="form-control" readonly="true" />
      </div>
    </div>
    
    
        <div class="form-group">
      <label class="control-label col-sm-2" for="dateRegistered">
      	Registration Date
      </label>
      <div class="col-sm-4">
     <!--    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"> -->
     <form:input path="dateRegistered" class="form-control" placeholder="date Registered" readonly="true" />
      </div>
    </div>
    
    
    <div class="form-group ">
      <label class="control-label col-sm-2" for="statusEnroll">
		Status of Enrolment
	</label>
      <div class="col-sm-4 md-form amber-textarea active-amber-textarea">          
       <!--  <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"> -->
      <%-- 	<form:textarea path="statusEnroll" class="md-textarea form-control z-depth-1" row="10"></form:textarea> --%>
      	<form:radiobutton path="statusEnroll" value="successful" label="successful" />
				<form:radiobutton path="statusEnroll" value="unsuccessful" label="unsuccessful" />
      </div>
    </div>

    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
       <!--  <button type="submit" class="btn btn-default">Submit</button> -->
       <form:button name="submit" class="btn btn-default" type="submit">Update</form:button>
      </div>
    </div>
  

  </form:form>

