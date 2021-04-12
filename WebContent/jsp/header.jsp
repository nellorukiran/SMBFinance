
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
<div class="kiranStyle">
	<img class="styleImg" style="clear:both;" src="<%=contextPath%>/images/nellorukiran.JPG" alt="" width="80" height="70"/><br>
	<span style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#610b4b;">Kiran Nelloru <br>Software Developer<br>+919884544340</span><br>
</div>
<div style="width:900px;float:left;margin-left: 238px;">
<div style="width:300px;float:left;">
	<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#b04de6;float:left;font-weight: bold;">Logged in by &nbsp;&nbsp; </div>
	<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#7d0f33;font-weight: bold;"><%=userType%> </div>
</div>
<div style="width:300px;float:left;">
	<div style="font-family: italic,Helvetica,sans-serif;font-style: italic;font-size: 13px;color:#b04de6;float:left;font-weight: bold;">Hi &nbsp;&nbsp;</div>
	<div style="font-family: arial,Helvetica,sans-serif;font-size: 13px;color:#7d0f33;font-weight: bold;"><%=userNameApp.toUpperCase()%></div>
</div>
<div class="logoutDiv"><a href="logoutAction" style="text-decoration:none;">Logout</a></div>
</div>
</div>