<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String contextPath = request.getContextPath();
%>
<head> 
<title>SMB Finance | Home</title>
<link rel="stylesheet" href="<%=contextPath%>/css/smb.css"/>
<link rel="stylesheet" href="<%=contextPath%>/css/style.css"/>
<link rel="stylesheet" href="<%=contextPath%>/css/styles.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/validation.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/script.js"></script>
 
</head>
<body class="bodybgImg"> 
<div class="layOutBackGroundDiv">
	<div class="layoutOuterDiv">
		<div class="headerDiv">
			<tiles:insertAttribute name="myHeader" />
		</div>
		<div class="menuBarDiv">
			<tiles:insertAttribute name="myMenu" />
		</div>
		<div class="bodyOuterDiv">
			<div class="sideBarDiv">
				<tiles:insertAttribute name="mySidebar"/>
			</div>
			<div class="bodyDiv">
				<tiles:insertAttribute name="myBody" />
			</div>
		</div>
		<div class="footerLayDiv">
				<tiles:insertAttribute name="myFooter" />
		</div>
	</div>
</div>
</body>
</html>