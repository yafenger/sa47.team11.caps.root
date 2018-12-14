<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="container">
	<form:form class="form-horizontal" method="POST"
		modelAttribute="user"
		action="${pageContext.request.contextPath}/student/create.html">


		<form:hidden path="userid" />
		<!-- First Mid Name -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="firstMidName"> <spring:message
					code="fieldLabel.firstMidName" />
			</label>
			<div class="col-sm-4">
				<form:input path="firstMidName" required="required"
					oninput="this.setCustomValidity('')"
					oninvalid="this.setCustomValidity('Please Enter First Mid Name.')"
					class="form-control" placeholder="First Mid Name" />
			</div>
		</div>
		<!-- Last Name -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="lastName"> <spring:message
					code="fieldLabel.lastName" />
			</label>
			<div class="col-sm-4">
				<form:input path="lastName" required="required"
					oninput="this.setCustomValidity('')"
					oninvalid="this.setCustomValidity('Please Enter Last Name')"
					class="form-control" placeholder="Last Name" />
			</div>
		</div>
		<!-- email -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="email"> <spring:message
					code="fieldLabel.email" />
			</label>
			<div class="col-sm-4">
				<form:input path="email" required="required"
					onchange="this.setCustomValidity('')"
					oninvalid="this.setCustomValidity('Please Enter Email')"
					class="form-control" placeholder="email" />
			</div>
		</div>
		<!-- password -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="password"> <spring:message
					code="fieldLabel.password" />
			</label>
			<div class="col-sm-4">
				<form:input type="password" path="password" required="required"
					onchange="this.setCustomValidity('')"
					oninvalid="this.setCustomValidity('Please Enter Password')"
					class="form-control" placeholder="password" />
			</div>
		</div>
		<!-- status -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="status"> Status
			</label>
			<div class="col-sm-4">
				<form:input path="status" value="active" disabled="true"
					class="form-control" />
			</div>
		</div>
		<!-- dateJoined -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="dateJoined"> Date Joined </label>
			<div class="col-sm-4">
				<form:input id="dateJoined" path="dateJoined" required="required" class="form-control"
					placeholder="Joined Date" />
			</div>
		</div>
		<!-- Button -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<form:button name="submit" class="btn btn-default" type="submit">Submit</form:button>
				<form:button name="reset" class="btn btn-default" type="reset">Reset</form:button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		 $("#dateJoined").datepicker({ dateFormat: "dd/mm/yy" }).val();
	});
</script>