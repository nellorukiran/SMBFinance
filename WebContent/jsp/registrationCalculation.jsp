<%@ taglib prefix="form" uri="/struts-tags" %>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<form action="paymentSave" method="post">
			<div class="headDiv">REGISTRATION CALCULATION FORM</div>
			<div class="headDivMsg">
				<form:if test="hasActionErrors()">
					<div class="errorMsg">
						<form:actionerror/>
					</div>
				</form:if>
			</div>	
			<div class="registerLeft">
				<div class="fieldLableDiv">
					<label class="lableFont">CUSTOME ID :</label>
					<input type="text" name="cus_id" value="<%=request.getParameter("cus_id")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">ADDRESS :</label>
					<input type="text" name="address" value="<%=request.getParameter("address")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">ITEM NAME :</label>
					<input type="text" name="item_name" value="<%=request.getParameter("item_name")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">MODEL NAME :</label>
					<input type="text" name="model_name" value="<%=request.getParameter("model_name")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">SALED PRICE :</label>
					<input type="text" name="saled_price" value="<%=request.getParameter("saled_price")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">ADVANCE :</label>
					<input type="text" name="advance" value="<%=request.getParameter("advance")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">INTREST :</label>
					<input type="text" name="intrest_amt" value="<%=request.getAttribute("intrest_amt")%>">
				</div>	
				<div class="fieldLableDiv">
					<label class="lableFont">PENALTY :</label>
					<input type="text" name="penalty" value="<%=(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))?"0":request.getParameter("penalty")%>">
				</div>			
				<div class="fieldLableDiv">
					<label class="lableFont">TOTAL DUES :</label>
					<input type="text" name="tot_dues" value="<%=request.getParameter("tot_dues")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">DOCUMENT AMT :</label>
					<input type="text" name="doc_amt" value="<%=request.getAttribute("doc_amt")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">DUE TIME :</label>
					<input type="text" name="due_time" value="<%=request.getParameter("due_time")%>">
				</div>
			</div><!-- registerLeft -->
			<div class="registerRight">
				<div class="fieldLableDiv">
					<label class="lableFont">CUS NAME :</label>
					<input type="text" name="cus_name" value="<%=request.getParameter("cus_name")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">BUY DATE :</label>
					<input type="text" name="buy_date"value="<%=request.getParameter("buy_date")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">SHOP NAME :</label>
					<input type="text" name="shop_name" value="<%=request.getParameter("shop_name")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">ACTUAL PRICE :</label>
					<input type="text" name="buy_price" value="<%=request.getParameter("buy_price")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">PROFIT :</label>
					<input type="text" name="profit" value="<%=request.getAttribute("profit")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">BAL AMOUNT :</label>
					<input type="text" name="bal_amt" value="<%=request.getAttribute("bal_amt")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">DUE AMOUNT :</label>
					<input type="text" name="due_amt" value="<%=request.getAttribute("due_amt")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">NEXT DUE :</label>
					<input type="text" name="next_due_amt" value="<%=request.getAttribute("next_due_amt")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">PER DUE :</label>
					<input type="text" name="per_due_amt" value="<%=request.getAttribute("per_due_amt")%>">
				</div>
				<div class="fieldLableDiv">
					<label class="lableFont">TOTAL PROFIT :</label>
					<input type="text" name="tot_profit" value="<%=request.getAttribute("tot_profit")%>">
				</div>
				
				<div class="fieldLableDiv">	
					<label class="lableFont">PHONE :</label>
					<input type="text" name="phone" value="<%=request.getParameter("phone")%>">
				</div>
			</div><!-- registerRight -->
			<div class="headDiv"><input type="submit" value="Register" name="commit"></div>
			</form>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->