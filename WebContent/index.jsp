<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@ taglib prefix="form" uri="/struts-tags" %>

<head>
<style>
#regErrorMsg{
	color:red;
}
</style>
<%
String contextPath = request.getContextPath();
application.removeAttribute("USER_TYPE");
application.removeAttribute("USER_NAME");
%>
<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/default.css"/>
<link rel="stylesheet" href="css/smb.css"/>
<title>SMB Finance</title>

</head>
<body>

<%
Date dNow = null ;SimpleDateFormat ft = null;
dNow = new Date( );
ft = new SimpleDateFormat ("EEE MMM dd yyyy");
%>
<div id="header">
	<div id="logo">
		<img src="images/SMB-Logo2.png" alt="" width="200" height="110"/>
		
	</div>
	
</div>
<div id="content">
	<div id="sidebar">
		<div id="login" class="boxed">
			<h2 class="title">LOGIN</h2>
			<div class="content">
				<table><tr><td>
				<form:form  id="loginForm" name="loginForm" method="post" action="userLogin">					
					<form:if test="hasActionErrors()">
						<div class="errorMsg">
							<form:actionerror/>
						</div>
					</form:if>					
					<fieldset>
					<legend>Sign-In</legend>
					<label>User Name:
						<input type="text" name="username" id="username" value="" />
						<div class="errorMsg"><form:fielderror fieldName="username"/></div>
					</label>
					<label>Password:
						<input type="password" name="password" id="password" value="" />
						<div class="errorMsg"><form:fielderror fieldName="password"/></div>
					</label>					
					<label>
						<input type="button" id="inputsubmit1" onclick="checkValidUser()" name="button" value="Sign In" />
					</label>
					</fieldset>
				</form:form>				
				</td></tr></table>
			</div>
		</div>
		
	</div>
	<div id="main">
		<div id="welcome" class="post">
			<h2 class="title">Sree Mahalakshmi Bindhuhasini Finance </h2>
			<h3 class="date"><span class="month"><%=ft.format(dNow)%></span></h3>
			<div class="story">
				<img src="images/ganesh.jpg" alt="" width="100" height="100" class="left" />
				<p><font color="#E24BE0">SMB Finance service, is an Indian Non-Banking Financial Company (NBFC). 
The company deals in Dealers and Customers.
 They are a consumer focused company with emphasis on profitable growth and operational 
efficiency to deliver best results to all customers. </font></p>
			</div>
			<div class="meta">
				<p>Posted by Nelloru Kiran , It is for Daily Journal<br />
					Customer details | Update Details | Download Monthly Details</p>
			</div>
		</div>
		
	</div>
</div>
<div id="footer">
	<p id="legal">Copyright 2017. All rights reserved. Design by Kiran Nelloru.<br> Best Viewed 1024*768 resoluation</p>
</div>
</body>
<script>
function checkValidUser(){
	$("#loginForm").validate();
	 $("#loginForm").submit();
}
$("#loginForm").validate({
	errorClass: "errorMsg",
	rules: {
		username:{	
			required:true
		},
		password:{	
			required:true
		}
	},
	messages: {
		username: {					
			required: "Please enter username"
		},
		password: {					
			required: "Please enter password."
		}
	}
});
</script>
</html>
