<%@ taglib prefix="form" uri="/struts-tags" %>
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
   			<div class="update-payment-update-outdiv">
				<div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">CUS ID :</div>  
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="cus_id" value="<%=request.getAttribute("cus_id")%>" readonly/>
		        	</div>
			     </div>
			     <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">CUSTMER NAME :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="cus_name" value="<%=request.getAttribute("cus_name")%>" readonly/>
		        	</div>
			     </div>
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">NEXT DUE :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="next_due_amt" value="<%=request.getAttribute("next_due_amt")%>" readonly/>
		        	</div>
		        </div>
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">BAL DUE :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="bal_due_amt" value="<%=request.getAttribute("due_amt")%>" readonly/>
		        	</div>
		        </div>
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">BAL DUES :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="tot_dues" value="<%=request.getAttribute("tot_dues")%>" readonly/>
		        	</div>
		        </div>
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">PENALTY :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="penality" value="<%=request.getAttribute("penality")%>" readonly/>
		        	</div>
		        </div>
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-lable">PHONE :</div>
		        	<div class="update-payment-update-flied-lable-out">
		        		<input class="color" type="text" name="phone" value="<%=request.getAttribute("phone")%>" readonly/>
		        	</div>
		        </div>
			</div><!-- update-payment-update-outdiv -->
   		</div><!-- registerDiv -->
   		</div><!-- regInnerDiv -->
   	</div><!-- regOutDiv -->