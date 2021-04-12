<%@ taglib prefix="form" uri="/struts-tags" %>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="registerSaveDiv">			
				<div class="headDivMsg">
					<form:if test="hasActionErrors()">
						<div class="errorMsg">
							<form:actionerror/>
						</div>
					</form:if>				
				</div>
				<div class="createUseDiv"><a href="userRegister">Create User</a></div>	
				<div class="registerLeftLast">
					<div class="fieldDivLast">
						<div class="lableFont">CUSTOME ID :</div>
						<div class="lableFontRes"><%=request.getParameter("cus_id")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">ADDRESS :</div>
						<div class="lableFontRes"><%=request.getParameter("address")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">ITEM  NAME :</div>
						<div class="lableFontRes"><%=request.getParameter("item_name")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">MODEL :</div>
						<div class="lableFontRes"><%=request.getParameter("model_name")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">SALED AMOUNT :</div>
						<div class="lableFontRes"><%=request.getParameter("saled_price")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">ADVANCE :</div>
						<div class="lableFontRes"><%=request.getParameter("advance")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">INTREST AMOUNT :</div>
						<div class="lableFontRes"><%=request.getParameter("intrest_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">TOTAL DUES :</div>
						<div class="lableFontRes"><%=request.getParameter("tot_dues")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">DUE TIME :</div>
						<div class="lableFontRes"><%=request.getParameter("due_time")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">PENALTY :</div>
						<div class="lableFontRes"><%=(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))?"0":request.getParameter("penalty")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">TOTAL PROFIT :</div>
						<div class="lableFontRes"><%=request.getParameter("tot_profit")%></div>
					</div>
					
				</div><!-- registerLeft -->
				<div class="registerRightLast">
					<div class="fieldDivLast">
						<div class="lableFont">CUS NAME :</div>
						<div class="lableFontRes"><%=request.getParameter("cus_name")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">BUY DATE :</div>
						<div class="lableFontRes"><%=request.getParameter("buy_date")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">SHOP NAME :</div>
						<div class="lableFontRes"><%=request.getParameter("shop_name")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">ACTUAL PRICE :</div>
						<div class="lableFontRes"><%=request.getParameter("buy_price")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">PROFIT :</div>
						<div class="lableFontRes"><%=request.getParameter("profit")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">BAL AMOUNT :</div>
						<div class="lableFontRes"><%=request.getParameter("bal_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">DUE AMOUNT :</div>
						<div class="lableFontRes"><%=request.getParameter("due_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">PER DUE AMT :</div>
						<div class="lableFontRes"><%=request.getParameter("per_due_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">NEXT DUE :</div>
						<div class="lableFontRes"><%=request.getParameter("next_due_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">DOC CHARGES :</div>
						<div class="lableFontRes"><%=request.getParameter("doc_amt")%></div>
					</div>
					<div class="fieldDivLast">
						<div class="lableFont">PHONE :</div>
						<div class="lableFontRes"><%=request.getParameter("phone")%></div>						
					</div>
				</div><!-- registerRight -->
			</div><!-- registerSaveDiv -->
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->