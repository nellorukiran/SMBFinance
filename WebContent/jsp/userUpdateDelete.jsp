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
				<form action="getUserDetailsForDelete" name="getUserDeleteForm" id="getUserDeleteForm" method="post">
					<div class="headDeleteDiv">USER UPDATE & DELETE</div>
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
				<form action="finalUpdateDelete" method="post" name="getUserDetailsDeleteForm" id="getUserDetailsDeleteForm">	
				<input type="hidden" name="oldPenality" id="oldPenality" value="<%=(request.getAttribute("penalty") == null || request.getAttribute("penalty").equals(""))?"0":request.getAttribute("penalty")%>"/>		
		        <div class="update-payment-update-InDiv">
		        	<div class="update-payment-update-leftDiv">
		        		<div class="update-payment-update-flied-out">
		        			<div class="update-payment-update-flied-lable">CUS ID :</div>
		        			<div class="update-payment-update-flied-lable-out">
		        				<input class="color" style="background-color:lightgray;" type="text" name="cus_id" value="<%=cus_id%>" readonly/>
		        			</div>
		        		</div>
		        		<div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">TOT DUE AMT:</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" style="background-color:lightgray;" type="text" name="due_amt" value="<%=request.getAttribute("due_amt")%>" readonly/>
				        	</div>
				        </div>
						
						<div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PER DUE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" style="background-color:lightgray;" type="text" name="per_due_amt"	value="<%=request.getAttribute("per_due_amt")%>" readonly/>
				        	</div>
				        </div>
				        <div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PAYMENT DUE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" style="background-color:lightgray;" type="text" name="next_due_amt" value="<%=request.getAttribute("next_due_amt")%>" readonly/>
				        	</div>
				        </div>
						<div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">DUE DATE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" size="20" name="due_date" id="due_date" onclick="showCalendarControl(this);" readonly/>
				        	</div>
				        </div>
				       				         
				        
		        	</div>
		        	<div class="update-payment-update-rightDiv">
		        		<div class="update-payment-update-flied-out">
		        			<div class="update-payment-update-flied-lable">CUS NAME :</div>
		        			<div class="update-payment-update-flied-lable-out">
		        				<input class="color" style="background-color:lightgray;" type="text" name="cus_name" value="<%=request.getAttribute("cus_name")%>" readonly/>
		        			</div>
		        		</div>
						 <div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">TOTAL DUES :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" name="tot_dues" value="<%=request.getAttribute("tot_dues")%>"/>
				        	</div>
				        </div>
		        		<div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PENALTY :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" name="penalty" style="background-color:lightgray;" readonly id="penalty" value="<%=(request.getAttribute("penalty") == null || request.getAttribute("penalty").equals(""))?"0":request.getAttribute("penalty")%>"/>
				        	</div>
				        </div>
				         <div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PAID DUE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" name="paid_amt" id="paid_amt" value=""/>
				        	</div>
				        </div>
				         
				       
				        <div class="update-payment-update-flied-out">
				        	<div class="update-payment-update-flied-lable">PHONE :</div>
				        	<div class="update-payment-update-flied-lable-out">
				        		<input class="color" type="text" name="phone" value="<%=request.getAttribute("phone")%>"/>
				        	</div>
				        </div>
				       
		        	</div>
		        </div>		        
		        <div class="update-payment-update-flied-out">
		        	<div class="update-payment-update-flied-submit">
		        		<input type="submit" id="x" value="Update and Delete" onclick="getUserDetailsDelete();"/>
		        	</div>
		        </div>
		     </form>
			</div> <!-- update-payment-search-out -->        
			<%}%>
			
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<script>


function getUserDelete(){
	$("#getUserDeleteForm").validate();
	 $("#getUserDeleteForm").submit();
}
$("#getUserDeleteForm").validate({
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
function getUserDetailsDelete(){
	$("#getUserDetailsDeleteForm").validate();
	 $("#getUserDetailsDeleteForm").submit();
}
$("#getUserDetailsDeleteForm").validate({
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