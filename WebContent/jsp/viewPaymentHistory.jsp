<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
		<div class="headDivMsg">
			<form:if test="hasActionErrors()">
				<div class="errorMsg">
					<form:actionerror/>
				</div>
			</form:if>
		</div>
			<%if(yes.length() > 0){
				ResultSet rs= (ResultSet)request.getAttribute("resultset");
				String cusName = (String)request.getAttribute("cusName");
				String address = (String)request.getAttribute("address");
				int dueNo =1,paid_amt=0,bal_amt=0;
				String due_date ="";
				Date paid_date = null;
			%>
			<div class="payment-history-result-out">
				<div class="payment-history-result-in">
					<div class="payment-history-result-header">
					      
			      		<div style="float:left;width:220px;font-family: arial;color: deeppink;font-weight: bold;">
			      		    <%=cusName%>
			      		</div>
			      		<div style="float:left;width:394px;text-align: right;font-family: arial;color: deeppink;font-weight: bold;">
			      			<%=address%>
			      		</div>
					     
					</div>
					<div class="payment-history-result-header">
						<div class="resultHeader">DUE NO</div>
						<div class="resultHeader">DUE AMOUNT</div>
						<div class="resultHeader">PAID DATE</div>
						<div class="resultHeaderLast">BAL DUE</div>
					</div>
					<%
					while(rs.next()) {
						paid_amt = rs.getInt("paid_amt");
						paid_amt = (paid_amt > 0)?paid_amt:0;
						paid_date = (Date)rs.getObject("PAY_DATE");
						due_date = (due_date.equals("null"))?"No Date":due_date;
						SimpleDateFormat dt2 = new SimpleDateFormat("dd-MM-yyyy");
						
						bal_amt = rs.getInt("bal_due");
					%>
					<div class="payment-history-result-header">
						<div class="resultFeild"><%=dueNo%></div>
						<div class="resultFeild"><%=paid_amt%></div>
						<div class="resultFeild"><%=dt2.format(paid_date)%></div>
						<div class="resultFeildLast"><%=bal_amt%></div>
					</div>
					<% 
					dueNo++;
					} 
					%>
				</div>
			</div><!-- payment-history-result-out -->
			<%}else{%>
			<% }%>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->