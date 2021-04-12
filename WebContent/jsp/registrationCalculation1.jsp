<!doctype html>
<html lang="en">
<%
String contextPath = request.getContextPath();
%>
<head>
<meta charset="utf-8">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
</head>
<body>
<div class="fieldDiv">
	<input type="text" name="buy_date" onclick="showCalendarControl(this);" placeholder="Your Desired Buyed Date">
</div>
</body>
</html>