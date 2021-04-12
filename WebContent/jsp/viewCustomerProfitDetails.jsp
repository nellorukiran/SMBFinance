<%@page import="java.sql.Date"%>
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
					int cus_id=0 ,buy_price=0,saled_price=0,advance=0,profit_on_item=0,count=0,tot_dues=0;
					String item_name="";
					float tot_profit=0f,doc_amt=0f,itrest_amt=0f;
					Date purchaseDate =null;
					float tot_doc=0f,tot_intrest=0f,tot_profits=0f;
				%>
				<div class="profit-result-Out">
					<table border="1">		
						<tr>
							<td class="td-header">CUS ID</td>
							<td class="td-header">DATE</td>
							<td class="td-header-item">ITEM</td>
							<td class="td-header">BUYED</td>
							<td class="td-header">SALED</td>
							<td class="td-header">PROFIT</td>
							<td class="td-header">DOCUMET</td>
							<td class="td-header">DUES</td>
							<td class="td-header">INTREST</td>
							<td class="td-header">PROFIT</td>
						</tr>
						<tr>
							<%
							while(r.next()){
								cus_id = r.getInt("cus_id");
								tot_dues = r.getInt("tot_dues");
								purchaseDate = r.getDate("purchase_date"); 
								item_name = r.getString("item_name"); 
								buy_price = r.getInt("buy_price"); 
								saled_price = r.getInt("tot_price"); 
								profit_on_item = r.getInt("profit");
								doc_amt = r.getFloat("doc_amt");
								itrest_amt = r.getFloat("intrest_amt"); 
								tot_profit = r.getFloat("tot_profit"); 
								
								tot_doc+=doc_amt;
								tot_intrest+=itrest_amt;
								tot_profits+=tot_profit;
								count++;
								//out.println("CUS ID ::"+cus_id+"<==>DATE OF PURCH ::"+purchaseDate+"ITEM_NAME ::"+item_name+"<==>BUY PRICE ::"+buy_price+"<==>SALED PRICE ::"+saled_price+"<==>PROFIT ITME ::"+profit_on_item+"<==>DOCUMNET CHARGES ::"+doc_amt+"<==> INTREST AMT ::"+itrest_amt+"<==> TOTAL PROFIT ::"+tot_profit+"<BR>");
							%>
							<td class="tr-header"><%=cus_id%></td>
							<td class="tr-header"><%=purchaseDate%></td>
							<td class="td-header-item-res"><%=item_name%></td>
							<td class="tr-header"><%=buy_price%></td>
							<td class="tr-header"><%=saled_price%></td>
							<td class="tr-header"><%=profit_on_item%></td>
							<td class="tr-header"><%=doc_amt%></td>
							<td class="tr-header"><%=tot_dues%></td>
							<td class="tr-header"><%=itrest_amt%></td>
							<td class="tr-header"><%=tot_profit%></td>
						</tr>
						<%} %>
						<tr>
							<td></td>
							<td></td>
							<td colspan="2" class="profit-tot">
								<B>FILES</B>
							</td>
							<td style="font-weight: bold;text-align:center;"><%=count%></td>
							<td class="profit-tot"><B>PROFIT</B></td>
							<td style="font-weight: bold;text-align:center;"><%=tot_doc%></td>
							<td></td><td ><B><%=tot_intrest%></B></td>
							<td style="font-weight: bold;text-align:center;"><%=tot_profits%></td>
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