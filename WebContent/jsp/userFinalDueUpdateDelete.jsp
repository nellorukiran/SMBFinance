<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String yes = (String)request.getAttribute("yes");
yes = (yes != null)?yes:"";
%>
<div class="regOutDiv">
 	<div class="regInnerDiv">
 		<div class="registerDiv1">
 			<div class="headDiv">FINAL DUE UPDATE AND DELETE</div>
	 		
	 		<div class="userUpdation-delete"><a href="userUpdation">Next Update</a></div>
	 		<div class="update-payment-update-outdiv-one1">
	 			<div class="headDivSuccessMsgDelete">
					<form:if test="hasActionErrors()">
						<div class="errorMsg">
							<form:actionerror/>
						</div>
					</form:if>
				</div>
				<div class="update-payment-update-leftDiv">
					<div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">CUS ID :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=request.getParameter("cus_id")%></div>
			        	</div>
				     </div>
				     <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">CUS NAME :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontResName"><%=request.getParameter("cus_name")%></div>
			        	</div>
				     </div>
				     <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">BAL DUE :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=request.getParameter("due_amt")%></div>
			        	</div>
			        </div>
			        <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">NEXT DUE :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=request.getParameter("next_due_amt")%></div>
			        	</div>
			        </div>
		        </div>
		        <div class="update-payment-update-rightDiv">
			        <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">BAL DUES :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=request.getParameter("tot_dues")%></div>
			        	</div>
			        </div>
			        <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">PENALTY :</div>
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))?"0":request.getParameter("penalty")%></div>
		        		</div>
	       			</div>
			        <div class="update-payment-update-flied-out-one">
			        	<div class="update-payment-update-flied-lable-one">PHONE :</div> 
			        	<div class="update-payment-update-flied-lable-out-one">
			        		<div class="lableFontRes"><%=request.getParameter("phone")%></div>
			        	</div>
			        </div>
		        </div>
			</div><!-- update-payment-update-outdiv -->
	 
 		</div><!-- registerDiv -->
 	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<script>



function userUpdateDeleteDueDetails(){
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