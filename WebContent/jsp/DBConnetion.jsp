<%@page import="java.text.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %> 
<%@ page import="java.io.*" %> 
<% 
Connection con = null;
try{
	String url = "jdbc:mysql://localhost:3306/mysql";
	Class.forName("com.mysql.jdbc.Driver"); 
	con = DriverManager.getConnection(url,"root","root");
	
	Format formatter=null;
	String formatedDate=null;
	java.util.Date date = new java.util.Date();
	formatter = new SimpleDateFormat("dd-MMM-yyyy:HH:mm:SS");
	formatedDate =formatter.format(date);

	String strQuery=null;
	Statement stmt = con.createStatement();
	String title="SREE MAHALAKSHMI BINDUHASINI FINANCE";
	String displayMsg="";
	String inputVal = request.getParameter("inputVal");
	String filename = "E:\\EXCEL\\USER_PAYMENT_DUE\\User_Payment_Details_OrderBy"+inputVal+"_"+formatedDate+".xls" ;
	String filename2 = "F:\\User_Payment_Details_OrderBy"+inputVal+"_"+formatedDate+".xls" ;
}catch(Exception e){}   
%>