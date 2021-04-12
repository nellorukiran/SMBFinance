<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<%
String contextPath = request.getContextPath();
String yes = (String)request.getAttribute("yes");
yes = (yes != null)?yes:"";
%>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">	
			<%if(yes.length() > 0){
				ResultSet rs= (ResultSet)request.getAttribute("resultset");
				String USER_TYPE="",FIRST_NAME="",LOGIN_ID="",USER_PASSWORD="",CREATE_DATE="";
				int dueNo= 1;
			%>
			<div class="payment-history-result-out">
				 <div class="view-admin-headRoleDiv">ADMIN ROLE DETAILS</div>
				<div class="view-admin-result-in">
					<div class="view-admin-Header-header">
						<div class="view-admin-Header-sno">S NO</div>
						<div class="resultHeader">FIRST NAME</div>
						<div class="resultHeader">LOGIN ID</div>
						<div class="resultHeader">USER TYPE</div>
						<div class="resultHeader">PASSWORD</div>
						<div class="resultHeaderLast">CREATE DATE</div>
					</div>
					<%
					while(rs.next()) {
						USER_TYPE = rs.getString("USER_TYPE");
						USER_TYPE = (USER_TYPE != null)?USER_TYPE:"";
						FIRST_NAME = rs.getString("FIRST_NAME");
						FIRST_NAME = (FIRST_NAME != null)?FIRST_NAME:"";
						LOGIN_ID = rs.getString("USER_NAME");
						LOGIN_ID = (LOGIN_ID != null)?LOGIN_ID:"";
						USER_PASSWORD = rs.getString("USER_PASSWORD");
						USER_PASSWORD = (USER_PASSWORD != null)?USER_PASSWORD:"";
						CREATE_DATE = rs.getString("CREATE_DATE");
						CREATE_DATE = (CREATE_DATE != null)?CREATE_DATE:"";
					%>
					<div class="view-admin-Header-result">
						<div class="view-admin-result-sno"><%=dueNo%></div>
						<div class="resultFeild"><%=FIRST_NAME%></div>
						<div class="resultFeild"><%=LOGIN_ID%></div>
						<div class="resultFeild"><%=USER_TYPE%></div>
						<div class="resultFeild"><%=USER_PASSWORD%></div>
						<div class="resultFeildLast"><%=CREATE_DATE%></div>
					</div>
					<% 
					dueNo++;
					} 
					%>
				</div>
			</div><!-- payment-history-result-out -->
			<%}%>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->