<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath(); 
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<div id="cheety-type-div">
<form method="post" id="cheetyTypeForm">
	<div class="fieldDiv">
		<input type="text" name="CH_RANGE" value="" placeholder="Cheety Amount or Range">
	</div>
	<div class="fieldDiv">
		<input type="text" name="TOT_MEMBERS" value="" placeholder="Total Members">
	</div>
	<div class="fieldDiv">
		<input type="text" name="CH_DATE" value="" onclick="showCalendarControl(this);" placeholder="Cheety Date">
	</div>
	<div class="fieldDiv">
		<input type="text" name="WONER_NAME" value="" placeholder="Owner Name">
	</div>
	<div class="fieldDiv">
		<input type="text" name="TOT_MONTHS" value="" placeholder="Total Months">
	</div>
	<div class="fieldDiv"><input type="button" id="getCheetyId" value="Register" name="commit"></div>
</form>
</div>
<script type='text/javascript'>
$('.bodyDiv').css('min-height','410px');
$('.layoutOuterDiv').css('min-height','740px');
$('#getCheetyId').click(function(){
	var url="<%=scriptName%>/getCheetyId";
	var data = $('#cheetyTypeForm').serialize();
	$.ajax({
		type:	"post",
		url:	url, 
		data: data,
		success:function(data){
			$('.cheety-type-create-out').html(data);
		}
	});
});
</script>