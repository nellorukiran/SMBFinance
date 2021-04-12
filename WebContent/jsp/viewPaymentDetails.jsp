<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<%
String contextPath = request.getContextPath();
String yes = (String)request.getAttribute("yes");
yes = (yes != null)?yes:"";
%>
<link rel="stylesheet" href="<%=contextPath%>/css/smb.css"/>
<%if(yes.length()>0) {
	ResultSet rs= (ResultSet)request.getAttribute("resultset");
%>
<div class="show-payment-details-out">
	<div class="show-payment-details-in">
		<div class="show-payment-details-header">
			<div class="show-payment-details-header-cusid">CUS ID</div>
			<div class="show-payment-details-header-name">NAME</div>
			<div class="show-payment-details-header-address">ADDRESS</div>
			<div class="show-payment-details-header-date">BUY DATE</div>
			<div class="show-payment-details-header-item">ITEM</div>
			<div class="show-payment-details-header-due">DUE AMT</div>
			<div class="show-payment-details-header-dues">TOT DUES</div>
			<div class="show-payment-details-header-perdue">PER DUE</div>
			<div class="show-payment-details-header-penalty">PENALTY</div>
			<div class="show-payment-details-header-nextdue">NEXT DUE</div>
			<div class="show-payment-details-header-duetime">DUE TIME</div>
			<div class="show-payment-details-header-phone">PHONE</div>
		</div>
		<%
		int cus_id=0,due_amt=0,per_due=0,tot_dues=0,penalty=0,next_due=0;
		String name="",address="",item="",date="",due_time="",phone=""; 
		while(rs.next()) {
			cus_id = rs.getInt("cus_id");
			cus_id = (cus_id > 0)?cus_id:0;
			name = rs.getString("cus_name");
			name = (name.equals("null"))?"No Data":name;
			due_amt = rs.getInt("due_amt");
			due_amt = (due_amt > 0)?due_amt:0;
			per_due = rs.getInt("per_due_amt");
			per_due = (per_due > 0)?per_due:0;
			tot_dues = rs.getInt("tot_dues");
			tot_dues = (tot_dues > 0)?tot_dues:0;
			penalty = rs.getInt("penality");
			penalty = (penalty > 0)?penalty:0;
			next_due = rs.getInt("next_due_amt");
			next_due = (next_due > 0)?next_due:0;
			address = rs.getString("address");
			address = (address.equals("null"))?"No Data":address;
			item = rs.getString("item_name");
			item = (item.equals("null"))?"No Data":item;
			date = rs.getString("buy_date");
			date = (date.equals("null"))?"No Date":date;
			due_time = rs.getString("due_time");
			due_time = (due_time.equals("null"))?"No Data":due_time;
			phone = rs.getString("phone");
			phone = (phone.equals("null"))?"No Data":phone;
		%>
		<div class="show-payment-details-result-out">
			<div class="show-payment-details-result-cusid"><%=cus_id%></div>
			<div class="show-payment-details-result-name"><%=name%></div>
			<div class="show-payment-details-result-address"><%=address%></div>
			<div class="show-payment-details-result-date"><%=date%></div>
			<div class="show-payment-details-result-item"><%=item%></div>
			<div class="show-payment-details-result-due"><%=due_amt%></div>
			<div class="show-payment-details-result-dues"><%=tot_dues%></div>
			<div class="show-payment-details-result-perdue"><%=per_due%></div>
			<div class="show-payment-details-result-penalty"><%=penalty%></div>
			<div class="show-payment-details-result-nextdue"><%=next_due%></div>
			<div class="show-payment-details-result-duetime"><%=due_time%></div>
			<div class="show-payment-details-result-phone"><%=phone%></div>
		</div>
		<%}%>
	</div>
</div>
<%
rs.close();
}%>