<%@ taglib prefix="form" uri="/struts-tags" %>

<%
String contextPath = request.getContextPath();
%> 
<div class="regOutDiv">
	<div class="adminRegInnerDiv">
		<div class="adminRegisterDiv">
		   <div class="headRoleDiv">ADMIN ROLE CREATION</div>
					<div class="headRoleDivMsg">
						<form:if test="hasActionErrors()">
							<div class="errorMsg">
								<form:actionerror/>
							</div>
						</form:if>
					</div>
			<form:form action="adminRegistration" id="adminRegistrationForm" name="adminRegistrationForm" method="post">
				<div class="fieldDiv">
					<input type="text" name="first_name" id="first_name" value="<%=(request.getParameter("first_name") != null)?request.getParameter("user_name"):""%>" placeholder="Enter Firstname">
				</div>
				<div class="fieldDiv">
					<input type="text" name="user_name" id="user_name" value="<%=(request.getParameter("user_name") != null)?request.getParameter("user_name"):""%>" placeholder="Enter Username">
				</div>
				<div class="fieldDiv">
					<select name="user_type" id="user_type" style="width:272px;">
							<option value="">-- Select --</option>
				        	<option value="ADMIN">ADMIN</option>
                          	<option value="USER">USER</option>
				     </select>
				</div>
				<div class="fieldDiv">
					<input type="password" name="user_password" id="user_password" value="<%=(request.getParameter("user_password") != null)?request.getParameter("user_password"):""%>" placeholder="Enter Password">
				</div>
				<div class="fieldDiv">
					<input type="password" name="confirm_password" id="confirm_password" value="<%=(request.getParameter("confirm_password") != null)?request.getParameter("confirm_password"):""%>" placeholder="Enter Confirm Password">
				</div>
				<div class="adminSubmitDiv"><input type="submit" value="Register" onclick="validateAdminRegistrationForm();" name="commit"></div>
			</form:form>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<!-- end #content -->

<script>
function validateAdminRegistrationForm(){
	$("#adminRegistrationForm").validate();
	 $("#adminRegistrationForm").submit();
}
$("#adminRegistrationForm").validate({
	errorClass: "regError",
	rules: {
		first_name:{	
			required:true
		},
		user_name:{	
			required:true
		},
		user_type:{	
			required:true
		},
		user_password:{	
			required:true						
		},
		confirm_password:{	
			required:true						
		}
	},
	messages: {
		first_name: {					
			required: "Please enter first name."
		},
		user_name: {					
			required: "Please enter user name."
		},
		user_type: {					
			required: "Please select user type."
		},
		user_password: {					
			required: "Please enter password."			
		},
		confirm_password: {					
			required: "Please enter confirm password."			
		}
	}
});
</script>