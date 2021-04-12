<%@ taglib prefix="form" uri="/struts-tags" %>

<%
String contextPath = request.getContextPath();
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
  
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<form:form action="paymentCalculation" id="paymentCalculationForm" name="paymentCalculationForm" method="post">
			<div class="headDiv">CUSTOMER REGISTRATION FORM</div>	
			<div class="headDivMsg">
				<form:if test="hasActionErrors()">
					<div class="errorMsg">
						<form:actionerror/>
					</div>
				</form:if>
			</div>			
			<div class="registerLeft">
				<div class="fieldDiv">
					<input type="text" name="cus_id" id="cus_id" value="<%=(request.getParameter("cus_id") != null)?request.getParameter("cus_id"):""%>" placeholder="Enter Registration Id">
				</div>
				<div class="regError"></div>
				<div class="fieldDiv">
					<input type="text" name="address" id="address" value="<%=(request.getParameter("address") != null)?request.getParameter("address").toUpperCase():""%>" placeholder="Enter Customer Address">
				</div>
				<div class="fieldDiv">
					<input type="text" name="shop_name" id="shop_name" value="<%=(request.getParameter("shop_name") != null)?request.getParameter("shop_name").toUpperCase():""%>" placeholder="Enter Buyed Shop">
				</div>
				<div class="fieldDiv">
					<input type="text" name="model_name" id="model_name" value="<%=(request.getParameter("model_name") != null)?request.getParameter("model_name").toUpperCase():""%>" placeholder="Enter Product Model">
				</div>
				<div class="fieldDiv">								
					<input type="text" name="saled_price" id="saled_price" value="<%=(request.getParameter("saled_price") != null)?request.getParameter("saled_price"):""%>" placeholder="Enter Saled Price">
				</div>
				<div class="fieldDiv">
					<input type="text" name="advance" id="advance" value="<%=(request.getParameter("advance") != null)?request.getParameter("advance"):""%>" placeholder="Enter Paid Advance">
				</div>
				<div class="fieldDiv">
					<select name="due_time" id="due_time" style="width:272px;">
				        	<option value="1TO10">1 TO 10</option>
                          	<option value="10TO15">10 TO 15</option>
                          	<option value="15TO20">15 TO 20</option>
                         	<option value="20TO25">20 TO 25</option>
                          	<option value="25TO30">25 TO 30</option>
				     </select>
				</div>
			</div><!-- registerLeft -->
			<div class="registerRight">
				<div class="fieldDiv">
					<input type="text" name="cus_name" id="cus_name" value="<%=(request.getParameter("cus_name") != null)?request.getParameter("cus_name").toUpperCase():""%>" placeholder="Enter Customer Name">
				</div>
				<div class="fieldDiv">
					<input type="text" name="buy_date" id="buy_date" value="<%=(request.getParameter("buy_date") != null)?request.getParameter("buy_date"):""%>" onclick="showCalendarControl(this);" placeholder="Enter Purchase Date">
				</div>
				<div class="fieldDiv">
					<input type="text" name="item_name" id="item_name" value="<%=(request.getParameter("item_name") != null)?request.getParameter("item_name").toUpperCase():""%>" placeholder="Enter Product Item">
				</div>
				<div class="fieldDiv">
					<input type="text" name="buy_price" id="buy_price" value="<%=(request.getParameter("buy_price") != null)?request.getParameter("buy_price"):""%>" placeholder="Enter Actul Price"> 
				</div>
				<div class="fieldDiv">
					<input type="text" name="tot_dues" id="tot_dues" value="<%=(request.getParameter("tot_dues") != null)?request.getParameter("tot_dues"):""%>" placeholder="Enter Total Dues">
				</div>
				<div class="fieldDiv">
					<input type="text" name="penalty" id="penalty" value="<%=(request.getParameter("penalty") != null)?request.getParameter("penalty"):""%>" placeholder="Enter Penality">
				</div>
				<div class="fieldDiv">
					<input type="text" name="phone" id="phone" value="<%=(request.getParameter("phone") != null)?request.getParameter("phone"):""%>" placeholder="Enter Customer Phone">
				</div>
			</div><!-- registerRight -->
			<div class="headDiv"><input type="submit" value="Register" onclick="validateRegistrationForm();" name="commit"></div>
			</form:form>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<!-- end #content -->
<script>


function validateRegistrationForm(){
	$("#paymentCalculationForm").validate();
	 $("#paymentCalculationForm").submit();
}
$("#paymentCalculationForm").validate({
	errorClass: "regError",
	rules: {
		cus_id:{	
			required:true,
			number:true
		},
		address:{	
			required:true						
		},
		shop_name:{	
			required:true						
		},
		model_name:{	
			required:true						
		},
		saled_price:{	
			required:true,
			number:true
		},
		advance:{	
			required:true,
			number:true
		},
		cus_name:{	
			required:true						
		},
		item_name:{	
			required:true						
		},
		buy_price:{	
			required:true,
			number:true
		},
		tot_dues:{	
			required:true,
			number:true
		},
		phone:{	
			required:true,
			number:true
		}
	},
	messages: {
		cus_id: {					
			required: "Please enter Customer id.",		
			number: "Please enter valid number."
		},
		address: {					
			required: "Please enter address.",			
		},
		shop_name: {					
			required: "Please enter shop name."			
		},
		model_name: {					
			required: "Please enter model name."			
		},
		saled_price: {					
			required: "Please enter saled price.",
			number: "Please enter valid number."
		},
		advance: {					
			required: "Please enter advance.",
			number: "Please enter valid number."
		},
		cus_name: {					
			required: "Please enter customer name."			
		},
		item_name: {					
			required: "Please enter item name."			
		},
		buy_price: {					
			required: "Please enter buyed price.",
			number: "Please enter valid number."
		},
		tot_dues: {					
			required: "Please enter total dues.",
			number: "Please enter valid number."
		},
		phone: {					
			required: "Please enter phone.",
			number: "Please enter valid number."
		}
	}
});
$('input').keyup(function(){
    this.value = this.value.toUpperCase();
});
$('input').onchange(function(){
    this.value = this.value.toUpperCase();
});
</script>