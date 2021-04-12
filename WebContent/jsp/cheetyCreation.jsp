<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("script.Name");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div class="regOutDiv">
	<div class="cheety-out-div">
		<div class="cheety-in-div">
			<div class="download-monthly">Cheety Type Creation</div>
			<div class="download-monthly">Cheety Member Creation</div>
			<div class="download-monthly">Cheety Updation</div>
		</div>
		<div class="cheety-type-create-out">
			
		</div>
		<div class="cheety-type-create-out1">
			
		</div>
	</div> <!-- download-out -->        
</div><!-- regOutDiv -->

<script type='text/javascript'>
$('.bodyDiv').css('min-height','410px');
$('.layoutOuterDiv').css('min-height','740px');
$('.cheety-type').click(function(){
	$('.cheety-type').css('background-color','#EE178C');
	$('.cheety-member').css('background-color','#ccccff');
	$('.cheety-update').css('background-color','#ccccff');
	var url="<%=scriptName%>/createCheetyType";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.cheety-type-create-out').html(msg);
		}
	});
});
$('.cheety-member').click(function(){
	$('.cheety-member').css('background-color','#EE178C');
	$('.cheety-type').css('background-color','#ccccff');
	$('.cheety-update').css('background-color','#ccccff');
	var url="<%=scriptName%>/createCheetyMember";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.cheety-type-create-out').html(msg);
		}
	});
});
$('.cheety-update').click(function(){
	$('.cheety-update').css('background-color','#EE178C');
	$('.cheety-type').css('background-color','#ccccff');
	$('.cheety-member').css('background-color','#ccccff');
	var url="<%=scriptName%>/updateCheetyDue";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.cheety-type-create-out').html(msg);
		}
	});
});
</script>