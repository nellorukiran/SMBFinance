<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>

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
			<form:form action="userAdminDelete" id="userAdminDeleteForm" name="userAdminDeleteForm" method="post">
				<div class="fieldDiv">
					<select name="user_type" id="user_type" style="width:272px;">
							<option value="">-- Select --</option>
				        	<option value="ADMIN">ADMIN</option>
                          	<option value="USER">USER</option>
				     </select>
				</div>
				<div class="fieldDiv">
					<select id="user_name" name="user_name" style="width:272px;" autocomplete="off">
				        <option value=""></option>			        
				     </select>
				</div>
				<div class="adminSubmitDiv"><input type="submit" value="Register" onclick="userAdminDeleteForm();" name="button"></div>
			</form:form>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<!-- end #content -->

<script type='text/javascript'>
function userAdminDeleteForm(){
	alert("fddf");
	 $("#userAdminDeleteForm").submit();
}
$('#user_type').change(function(){
	var user_type = $('#user_type').val();
	var url="<%=scriptName%>/getAdminUserList?user_type="+user_type+"";
	$.ajax({
		type:	"post",
		url:	url,		
		success:function(data){
			var obj ="";
			try{
				obj = JSON.parse(data);
			}catch(error){}	
			var user_name = document.getElementById("user_name");
			user_name.options[0] = new Option("----- Select -----","");			
			for(var i = 0; i< obj.length;i++){				
				user_name.options[i+1] = new Option(obj[i].USER_NAME,obj[i].USER_NAME);			
			}
		}
	});	
});

</script>