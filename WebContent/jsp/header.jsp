
<%
String contextPath = request.getContextPath(); 
String scriptName = (String)application.getAttribute("script.Name");
String userType =(String) session.getAttribute("USER_TYPE");
userType = (userType !=null)? userType : "";
String userNameApp =(String) session.getAttribute("FIRST_NAME");
userNameApp = (userNameApp !=null)? userNameApp : "";
if(userNameApp.length() <= 0){
%>
<script>
	var url = "<%=scriptName%>/";
    window.location.href = url;
</script>
<%
}
%>
<div class="headerOut">
	<div class="headerIn">
	 	<a href="homeLink" class="styleImg"><img class="styleImg" src="<%=contextPath%>/images/SMB-Logo2.png" alt="" width="210" height="111"/></a>
	</div>
	<div style="width:200px;float:left;margin-top:95px">
		<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#E8EEED;float:left;font-weight: bold;">Logged in by &nbsp;&nbsp; </div>
		<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#E8EEED;font-weight: bold;"><%=userType%> </div>
	</div>
	<div style="width:95px;float:left;margin-top:95px;margin-left:120px">
		<div style="font-family: italic,Helvetica,sans-serif;font-style: italic;font-size: 13px;color:#E8EEED;float:left;font-weight: bold;">Hi &nbsp;&nbsp;</div>
		<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#E8EEED;font-weight: bold;"><%=userNameApp.toUpperCase()%></div>
	</div>
	<div style="width:55px;float:left;margin-top:95px;margin-left:111px">
		<a href="logoutAction" style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#E8EEED;font-weight: bold;">Logout</a>
	</div>
	<div style="width:150px;float:left;">
	 	<a href="homeLink" class="styleImg" style="float:left"><img class="styleImg" src="<%=contextPath%>/images/lord/ganesh.png" alt="" width="210" height="111"/></a>
	</div>
</div>
