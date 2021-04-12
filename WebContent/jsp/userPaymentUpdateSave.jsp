<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String yes = (String)request.getAttribute("yes");
yes = (yes != null)?yes:"";
%>
<div class="regOutDiv">
 	<div class="regInnerDiv">
 		<div class="registerDiv">
 		<%
 			if(yes.length() < 0 || yes.equals("")){
 		%>
 			<div class="headDiv">UPDATE DUE FOR NEXT MONTH</div>
 				<div class="update-payment-update-outdiv1">
					<form action="updateUserDueDetails" method="post" id="updateUserDueDetailsForm" name="updateUserDueDetailsForm">
						<input type="hidden" name="due_date" id="due_date" value="<%=request.getAttribute("due_date")%>"/>
						<input type="hidden" name="phone" id="phone" value="<%=request.getAttribute("phone")%>"/>
						<input type="hidden" name="cus_id" id="cus_id" value="<%=request.getParameter("cus_id")%>"/>
				     <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">CUS NAME :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" style="background-color:lightgray;" readonly type="text" name="cus_name" id="cus_name" value="<%=request.getParameter("cus_name")%>"/>
			        	</div>
				     </div>
				     <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">PAID DUE :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" style="background-color:lightgray;" readonly name="paid_amt" id="paid_amt" value="<%=request.getAttribute("paid_amt")%>"/>
			        	</div>
			        </div>
				     <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">BAL DUE :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" style="background-color:lightgray;" readonly name="due_amt" id="due_amt" value="<%=request.getAttribute("due_amt")%>"/>
			        	</div>
			        </div>
			        <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">PER DUE :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" name="per_due_amt" style="background-color:lightgray;" readonly id="per_due_amt" value="<%=request.getAttribute("per_due_amt")%>"/>
			        	</div>
			        </div>
			        <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">PENALTY :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" id="penalty" name="penalty" value="<%=(request.getAttribute("penalty") == null || request.getAttribute("penalty").equals(""))?"0":request.getAttribute("penalty")%>"/>
		        		</div>
        			</div>
			        
			        <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">NEXT DUE :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" name="next_due_amt" id="next_due_amt" value="<%=request.getAttribute("next_due_amt")%>"/>
			        	</div>
			        </div>
			        <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-lable">BAL DUES :</div>
			        	<div class="update-payment-update-flied-lable-out">
			        		<input class="color" type="text" name="tot_dues" id="tot_dues" value="<%=request.getAttribute("tot_dues")%>"/>
			        	</div>
			        </div>
			        
			        <div class="update-payment-update-flied-out">
			        	<div class="update-payment-update-flied-submit1">
			        		<input type="submit" id="x" value="Click for Update" onclick="updateUserDueDetails();"/>
			        	</div>
			        </div>
				</form>
			</div><!-- update-payment-update-outdiv -->
	 		<%
	 		}
 			else{
	 		%>
	 		<div class="userUpdateDelete-out-div-link">
		 		<div class="userUpdation"><a href="userUpdation">Next Update</a></div>
		 		<div class="userDelete-Link"><a href="userUpdateDelete">Delete</a></div>
	 		</div>
	 		<div class="update-payment-update-outdiv-one">
	 			<div class="headDivSuccessMsg">
					<form:if test="hasActionErrors()">
						<div class="errorMsg">
							<form:actionerror/>
						</div>
					</form:if>
				</div> 
				<div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">CUS ID :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getParameter("cus_id")%></div>
		        	</div>
			     </div>
			     <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">CUS NAME :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getParameter("cus_name")%></div>
		        	</div>
			     </div>
			     <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">PAID AMOUNT :</div> 
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getAttribute("paid_amt")%></div>
		        	</div>
		        </div>
			     <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">BAL AMOUNT :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getAttribute("due_amt")%></div>
		        	</div>
		        </div>
		         <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">BAL DUES :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getAttribute("tot_dues")%></div>
		        	</div>
		        </div>
		         <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">PER DUE :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getAttribute("per_due_amt")%></div>
		        	</div>
		        </div>
		        <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">PENALTY :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=(request.getAttribute("penalty") == null || request.getAttribute("penalty").equals(""))?"0":request.getAttribute("penalty")%></div>
	        		</div>
       			</div>
		        <div class="update-payment-update-flied-out-one">
		        	<div class="update-payment-update-flied-lable-one">NEXT DUE :</div>
		        	<div class="update-payment-update-flied-lable-out-one">
		        		<div class="lableFontRes"><%=request.getAttribute("next_due_amt")%></div>
		        	</div>
		        </div>
			</div><!-- update-payment-update-outdiv -->
	 		<%}%>
 		</div><!-- registerDiv -->
 	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<script>



function updateUserDueDetails(){
	$("#updateUserDueDetailsForm").validate();
	 $("#updateUserDueDetailsForm").submit();
}
$("#updateUserDueDetailsForm").validate({
	errorClass: "regError",
	rules: {
		cus_id:{	
			required:true,
			number:true
		},
		cus_name:{	
			required:true
		},
		due_amt:{	
			required:true
		},
		next_due_amt:{	
			required:true
		},
		tot_dues:{	
			required:true
		},
		penalty:{	
			required:true
		},
		phone:{	
			required:true
		}
	},
	messages: {
		cus_id: {					
			required: "Please enter Customer id.",		
			number: "Please enter valid number."
		},
		cus_name: {					
			required: "Please enter Customer name."
		},
		due_amt: {					
			required: "Please enter due amount.",		
			number: "Please enter valid number."
		},
		next_due_amt: {					
			required: "Please enter next due amount.",		
			number: "Please enter valid number."
		},
		phone: {					
			required: "Please enter phone no.",		
			number: "Please enter valid number."
		}
	}
});