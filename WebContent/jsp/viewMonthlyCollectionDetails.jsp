<%@page import="java.sql.Date"%>
<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
String contextPath = request.getContextPath();
String resultType = (String)request.getAttribute("resultType");
resultType = (resultType != null)?resultType:"";
%>
<link rel="stylesheet" href="<%=contextPath%>/css/smb.css"/>
<%if(resultType.equals("YES") && resultType.length()>0) {
	ResultSet r= (ResultSet)request.getAttribute("resultset");
	String fromDate = (String)request.getAttribute("fromDate");
	String toDate = (String)request.getAttribute("toDate");
%>
<div class="regOutDiv">
	<div class="regInnerDiv-profit-colloction">
			<div class="headDiv">COLLECTION DETAILS</div>
			<div style="width:595px;float:left;">
				<div style="width:595px;float:left;">
					<div style="width:305px;float:left;">
						<div style="width:83px;margin-left: 27px;float:left;color: blue;font-size: 14px;font-weight: bold;font-family: arial;">From Date :</div>
						<div style="width:115px;float:left;color: #7d0f33;font-size: 14px;font-weight: bold;font-family: arial;"><%=fromDate %></div>
					</div>
					<div style="width:285px;float:left;">
					    <div style="width:147px;float:left;text-align: right;color: blue;font-size: 14px;font-weight: bold;font-family: arial;">To Date :</div>
						<div style="width:119px;float:left;margin-left: 5px;color: #7d0f33;font-size: 14px;font-weight: bold;font-family: arial;"><%=toDate %></div>
					</div>
				</div>
			</div>
			<div class="profit-OutDiv-collection">
				<%
					int cus_id=0 ,paid_amt=0,sno=0;
					String cus_name="";
					Date paid_date =null;
					float tot_due=0f;
				%>
				<div class="profit-result-Out-collection">
					<table border="1">		
						<tr>
						    <td class="td-header-sno">S NO</td>
							<td class="td-header">CUS ID</td>
							<td class="td-header-name">CUS NAME</td>
							<td class="td-header-paid">DUE AMOUNT</td>
							<td class="td-header-date">PAID DATE</td>
						</tr>
						<tr>
							<%
							while(r.next()){
								cus_id = r.getInt("cus_id");
								cus_name = r.getString("cus_name");
								paid_amt = r.getInt("PAID_AMT"); 
								paid_date =(Date)r.getObject("PAY_DATE"); 
								SimpleDateFormat dt2 = new SimpleDateFormat("dd-MM-yyyy");								
								tot_due+=paid_amt;
								sno++;
								//out.println("CUS ID ::"+cus_id+"<==>DATE OF PURCH ::"+purchaseDate+"ITEM_NAME ::"+item_name+"<==>BUY PRICE ::"+buy_price+"<==>SALED PRICE ::"+saled_price+"<==>PROFIT ITME ::"+profit_on_item+"<==>DOCUMNET CHARGES ::"+doc_amt+"<==> INTREST AMT ::"+itrest_amt+"<==> TOTAL PROFIT ::"+tot_profit+"<BR>");
							%>
							<td class="tr-header-sno"><%=sno%></td>
							<td class="tr-header"><%=cus_id%></td>
							<td class="tr-header-name"><%=cus_name%></td>
							<td class="tr-header-paid"><%=paid_amt%></td>
							<td class="tr-header-date"><%=dt2.format(paid_date)%></td>
						</tr>
						<%} %>
						<tr>
						    <td></td>
							<td colspan="2" class="profit-tot">
								<B>TOTAL COLLECTION</B>
							</td>
							<td style="font-weight: bold;text-align:center;color:#7d0f33;"><%=tot_due%></td>
							<td></td>
						</tr>
					</table>
				</div><!-- profit-result-Out -->
			</div><!-- profit-OutDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<%}else{%>
      <div style="width:500px;float:left; padding-left: 170px; padding-top: 160px;">
      	<div style="width:500px;color:#1e90ff;font-size: 20px;font-weight: bold;font-family: arial;margin-left:100px;margin-top:50px;">No data available for selected dates .</div>
      </div>
<%}%>