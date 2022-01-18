<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();
String cus_id=request.getParameter("cus_id");
cus_id = (cus_id !=null)?cus_id:"";
String yes=(String)request.getAttribute("yes");
yes = (yes !=null)?yes:"";
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="update-payment-search-out">
				<form action="getUserDetailsDateUpdation" name="getUserDetailsForm" id="getUserDetailsForm" method="post">
					<div class="headDiv">DATE UPDATION</div>
					<div class="headDivMsg">
						<form:if test="hasActionErrors()">
							<div class="errorMsg">
								<form:actionerror/>
							</div>
						</form:if>
					</div>
					<div class="update-payment-search-inner">
						<div class="update-payment-userId1">Customer Id</div>
						<div class="update-payment-textbox2"><input type="text" value="<%=cus_id%>" placeholder="Customer Id" name="cus_id" /></div>
						<div class="update-payment-submit1"><input type="submit" value="Search" onclick="getUserDetails();"></div>
					</div>
				</form>
				
			</div><!-- update-payment-search-out -->
			
			<%if(cus_id.length()>0 && yes.length() > 0){%>
				<div class="update-payment-update-outdiv">
				<form action="updateNewDate" method="post" name="getUserDetailsUpdationForm" id="getUserDetailsUpdationForm">	
				<input type="hidden" name="oldPenality" id="oldPenality" value="<%=(request.getAttribute("penalty") == null || request.getAttribute("penalty").equals(""))?"0":request.getAttribute("penalty")%>"/>		
		        <div class="update-payment-update-InDiv">
		        	
		        		<div class="update-payment-update-flied-out">
		        			<div class="update-payment-update-flied-lable">CUS ID :</div>
		        			<div class="update-payment-update-flied-lable-out">
		        				<input class="color" style="background-color:lightgray;" type="text" name="cus_id" value="<%=cus_id%>" readonly/>
		        			</div>
		        		</div>
		        		
		        		<div class="update-payment-update-flied-out">
		        			<div class="update-payment-update-flied-lable">CUS NAME :</div>
		        			<div class="update-payment-update-flied-lable-out">
		        				<input class="color" style="background-color:lightgray;" type="text" name="cus_name" value="<%=request.getAttribute("cus_name")%>" readonly/>
		        			</div>
		        		</div>
		        		<div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PURCHASE DATE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" style="background-color:lightgray;" type="text" name="purchase_date"	value="<%=request.getAttribute("purchase_date")%>" readonly/>
				        	</div>
				        </div>
				        <div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">NEW DATE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" name="new_date" onclick="showCalendarControl(this);" placeholder="Enter New Date"/>
				        	</div>
				        </div>
				       
				       
		        	
		        </div>
		        
		        
		        
		        
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-submit">
		        		<input type="submit" id="x" value="Click for Update" onclick="getUserDetailsUpdation();"/>
		        	</div>
		        </div>
		     </form>
			</div> <!-- update-payment-search-out -->        
			<%}%>
			
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<script>


function getUserDetails(){
	$("#getUserDetailsForm").validate();
	 $("#getUserDetailsForm").submit();
}
$("#getUserDetailsForm").validate({
	errorClass: "regError",
	rules: {
		cus_id:{	
			required:true,
			number:true
		}
	},
	messages: {
		cus_id: {					
			required: "Please enter Customer id.",		
			number: "Please enter valid number."
		}
	}
});
function getUserDetailsUpdation(){
	$("#getUserDetailsUpdationForm").validate();
	 $("#getUserDetailsUpdationForm").submit();
}
$("#getUserDetailsUpdationForm").validate({
	errorClass: "regError",
	rules: {
		paid_amt:{	
			required:true,
			number:true
		},
		due_date:{	
			required:true
		}
	},
	messages: {
		paid_amt: {					
			required: "Please enter Due amount.",		
			number: "Please enter valid number."
		},
		due_date: {					
			required: "Please enter Due date."
		}
	}
});
</script>