
<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("script.Name");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div id="show-due-details-OutDiv">
	<form action="saveProduct" name="saveProductForm" method="post" id="saveProductForm">
		<div class="show-due-details-InDiv">
			<div class="productDivMsg">
				<form:if test="hasActionErrors()">
					<div class="errorMsg">
						<form:actionerror/>
					</div>
				</form:if>
			</div>
			<div class="profit-lable-name">Product Name :</div>
			<div class="profit-lable-input">
				<input type="text" size="20" name="prod_name" id="prod_name" />
			</div>
			<div class="view-user-due-button" >
				<input onclick="saveProduct()" type="button" value="Search" />
			</div>
		</div><!-- profit-lable-Out -->
		<div class="errorMsgCus" id="errorMsgCus" style="display:none;"></div>
	</form>
</div>
<script>
function saveProduct(){
	$("#saveProductForm").validate();
	 $("#saveProductForm").submit();
}
$("#saveProductForm").validate({
	errorClass: "regError",
	rules: {
		prod_name:{	
			required:true
		}
	},
	messages: {
		prod_name: {					
			required: "Please enter Product name."
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