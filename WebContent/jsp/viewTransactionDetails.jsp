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
<div class="show-transaction-details-out">
	<div class="show-transaction-details-in">
		<div class="show-transaction-details-header">
			<div class="show-payment-details-header-cusid">CUS ID</div>
			<div class="show-payment-details-header-name">NAME
				<div class="show-payment-details-triangle">
					<div class="arrow-up"></div>
					<div class="arrow-down"></div>
				</div>
			</div>
			<div class="show-payment-details-header-address">ADDRESS</div>
			<div class="show-payment-details-header-date">BUY DATE</div>
			<div class="show-payment-details-header-item">ITEM</div>
			<div class="show-transaction-details-header-buyprice">BUY PRICE</div>
			<div class="show-transaction-details-header-saleprice">SALE PRICE</div>
			<div class="show-transaction-details-header-advance">ADVANCE</div>
			<div class="show-transaction-details-header-balamt">BAL AMT</div>
			<div class="show-transaction-details-header-balamt">INTR AMT</div>
			<div class="show-payment-details-header-due">DUE AMT</div>
			<div class="show-payment-details-header-dues">TOT DUES</div>
			<div class="show-payment-details-header-perdue">PER DUE</div>
			<div class="show-payment-details-header-penalty">PENALTY</div>
			<div class="show-payment-details-header-duetime">DUE TIME</div>
			<div class="show-payment-details-header-phone">PHONE</div>
		</div>
		<%
		int cus_id=0,due_amt=0,per_due=0,tot_dues=0,penalty=0,tot_price=0,buy_price=0,advance=0,bal_amt=0,int_amt=0;
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
			penalty = rs.getInt("penalty");
			penalty = (penalty > 0)?penalty:0;
			buy_price = rs.getInt("buy_price");
			buy_price = (buy_price > 0)?buy_price:0;
			tot_price = rs.getInt("tot_price");
			tot_price = (tot_price > 0)?tot_price:0;
			advance = rs.getInt("advance");
			tot_price = (tot_price > 0)?tot_price:0;
			bal_amt = rs.getInt("bal_amt");
			bal_amt = (bal_amt>0)?bal_amt:0;
			int_amt = rs.getInt("intrest_amt");
			int_amt = (int_amt>0)?int_amt:0;
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
		<div class="show-transaction-details-result-out">
			<div class="show-payment-details-result-cusid"><%=cus_id%></div>
			<div class="show-payment-details-result-name"><%=name%></div>
			<div class="show-payment-details-result-address"><%=address%></div>
			<div class="show-payment-details-result-date"><%=date%></div>
			<div class="show-payment-details-result-item"><%=item%></div>
			<div class="show-transaction-details-result-buyprice"><%=buy_price%></div>
			<div class="show-transaction-details-result-saleprice"><%=tot_price%></div>
			<div class="show-transaction-details-result-advance"><%=advance%></div>
			<div class="show-transaction-details-result-balamt"><%=bal_amt%></div>
			<div class="show-transaction-details-result-balamt"><%=int_amt%></div>
			<div class="show-payment-details-result-due"><%=due_amt%></div>
			<div class="show-payment-details-result-dues"><%=tot_dues%></div>
			<div class="show-payment-details-result-perdue"><%=per_due%></div>
			<div class="show-payment-details-result-penalty"><%=penalty%></div>
			<div class="show-payment-details-result-duetime"><%=due_time%></div>
			<div class="show-payment-details-result-phone"><%=phone%></div>
		</div>
		<%}%>
	</div>
</div>
<%
rs.close();
}
%>