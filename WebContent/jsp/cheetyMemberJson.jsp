<%@ page import="org.json.JSONArray"%>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
JSONArray obj = (JSONArray)request.getAttribute("JSONOBJ");
out.println(obj);
%>