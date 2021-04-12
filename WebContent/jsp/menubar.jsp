<%
boolean isUserAdmin= false ;
String contextPath = request.getContextPath();
String userType =(String) application.getAttribute("USER_TYPE");
userType = (userType !=null)? userType : "";
if(userType.length() > 0 && userType.equals("ADMIN")){
	isUserAdmin = true ;
}
%>
<div class="menuOuterDiv">
	<div class="menuInnerDiv">
		<div class="fallowDiv">
			<table border="0" cellpadding="0" cellspacing="0" width="97%" height="30" >
            <tr>
              <td width="70%" class="fallowStyle" height="30">&nbsp;Follow&nbsp;Me</td>
              <td width="10%" style="padding-left: 4; padding-right: 0 ;padding-top: 1" height="30"><a href="http://www.manoramaonline.com/rssfeed/news.xml" target="_balnk"><img border="0" src="<%=contextPath %>/images/rss_icon.jpg" width="28" height="25"></a></td>
              <td width="10%" style="padding-left: 4; padding-right: 0 ;padding-top: 1" height="30"><a href="http://twitter.com/nellorukiran" target="_balnk"><img border="0" src="<%=contextPath %>/images/twitter.jpg" width="28" height="25"></a></td>
              <td width="10%" style="padding-left: 4; padding-right: 0;padding-top: 1" height="30"><a href="https://www.facebook.com/nellorukiran" target="_balnk"><img border="0" src="<%=contextPath %>/images/facebook.png" width="28" height="25"></a></td>
            </tr>
          </table>
		</div>
		<div class="menuDiv">
			<div class="menu"><a style="text-decoration:none;" href="homeLink">HOME</a></div>
			<div class="menu"><a href='userRegister'>CREATION</a></div>
			<div class="menu"><a href='userUpdation'>UPDATION</a></div>
			<div class="menu"><a href='viewUserDetailsPage'>VIEW</a></div>
			<div class="menu"><a href='userDownload'>DOWNLOAD</a></div>
			<% if(isUserAdmin){%>
			<div class="menu"><a href='userDelete'>DELETE</a></div>
			<% }%>
			<div class="menu"><a href='showCheety'>CHEETY</a></div>
		</div>
	</div>
</div>
