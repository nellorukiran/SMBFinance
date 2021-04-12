<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<%
String contextPath = request.getContextPath();
String resultType = (String)request.getAttribute("resultType");
resultType = (resultType != null)?resultType:"";
%>
<link rel="stylesheet" href="<%=contextPath%>/css/smb.css"/>
<%if(resultType.equals("YES") && resultType.length()>0) {
	ResultSet r= (ResultSet)request.getAttribute("resultset");
%>
<div class="regOutDiv">
	<div class="regInnerDiv-profit">
		<div class="registerDiv">
			<div class="profit-OutDiv">
				<%
					int cus_id=0 ,trans_amount=0,led_id=0,debt_amt=0,credit_amt=0,tot_dedit=0,tot_credit=0;
					String trans_type="",trans_mode="",debt_amtStr="",credit_amtStr=""; 
					Date trans_date =null;
				%>
				<div class="profit-result-Out">
					<table border="1">		
						<tr>
							<td class="td-header">LEDGER NO</td>
							<td class="td-header-ld">CUS ID</td>
							<td class="td-header-ld">TRANS DATE</td>
							<td class="td-header-ld">PAYM TYPE</td>
							<td class="td-header-ld">DEDIT</td>
							<td class="td-header-ld">CREDIT</td>
						</tr>
						<tr>
							<%
							while(r.next()){
								led_id = r.getInt("LED_ID");
								cus_id = r.getInt("CUS_ID");
								trans_date = r.getDate("TRANS_DATE"); 
								trans_type = r.getString("TRANS_TYPE"); 
								trans_mode = r.getString("TRANS_MODE"); 
								trans_amount = r.getInt("TRANS_AMOUNT"); 
								if(trans_mode.trim().equals("Dr")){
									debt_amtStr=""+trans_amount;
									debt_amt=trans_amount;
								}
								else {
									debt_amtStr="";
								}
								if(trans_mode.trim().equals("Cr")){
									credit_amtStr=""+trans_amount;
									credit_amt=trans_amount;
								}
								else {
									credit_amtStr="";
								}
								SimpleDateFormat dt2 = new SimpleDateFormat("dd-MM-yyyy");
								tot_dedit+=debt_amt;
								tot_credit+=credit_amt;
								//out.println("CUS ID ::"+cus_id+"<==>DATE OF PURCH ::"+purchaseDate+"ITEM_NAME ::"+item_name+"<==>BUY PRICE ::"+buy_price+"<==>SALED PRICE ::"+saled_price+"<==>PROFIT ITME ::"+profit_on_item+"<==>DOCUMNET CHARGES ::"+doc_amt+"<==> INTREST AMT ::"+itrest_amt+"<==> TOTAL PROFIT ::"+tot_profit+"<BR>");
							%>
							<td class="tr-header"><%=led_id%></td>
							<td class="tr-header"><%=cus_id%></td>
							<td class="tr-header"><%=dt2.format(trans_date)%></td>
							<td class="tr-header"><%=trans_type%></td>
							<td class="tr-header"><%=debt_amtStr%></td>
							<td class="tr-header"><%=credit_amtStr%></td>
						</tr>
						<%} %>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="font-weight: bold;text-align:center;"><%=tot_dedit%></td>
							<td style="font-weight: bold;text-align:center;"><%=tot_credit%></td>
						</tr>
					</table>
				</div><!-- profit-result-Out -->
			</div><!-- profit-OutDiv -->
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<%}else{%>
      <div style="width:500px;float:left; padding-left: 170px; padding-top: 160px;">
      	<div style="width:500px;color:#1e90ff;font-size: 20px;font-weight: bold;font-family: arial;margin-left:100px;margin-top:50px;">No data available for selected dates .</div>
      </div>
<%}%>