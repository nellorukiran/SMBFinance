<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<%
String ch_id = "";
try{
	ch_id =request.getAttribute("CH_ID").toString();
	ch_id = (ch_id != null)?ch_id : "";
}catch(Exception e){}
if(ch_id.length() > 0){;
%>
<form method="POST" id="cheetyInsertForm">
	<div id="updateResultDiv" style="display:none;width:705px;float:left;font-family: arial;"></div>
	<div class="fieldDiv">
		<input type="text" name="CH_ID" value="<%=ch_id%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="CH_NAME" value="<%=request.getParameter("CH_NAME")%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="CH_RANGE" value="<%=request.getParameter("CH_RANGE")%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="OWNER_NAME" value="<%=request.getParameter("OWNER_NAME")%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="CH_DATE" value="<%=request.getParameter("CH_DATE")%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="TOT_MEMBERS" value="<%=request.getParameter("TOT_MEMBERS")%>">
	</div>
	<div class="fieldDiv">
		<input type="text" name="TOT_MONTHS" value="<%=request.getParameter("TOT_MONTHS")%>">
	</div>
	<div class="submitFieldDiv"><input id="cheetyInsert" type="button" value="Update" name="commit"></div>
</form>
<%
}
%>
<script type='text/javascript'>
$('#cheetyInsert').click(function(){	
	var data = $('#cheetyInsertForm').serialize();
	var url="<%=scriptName%>/cheetyPackageInsert";
	$.ajax({
		type:	"post",
		url:	url,
		data: data,
		success:function(data){
			$('#updateResultDiv').show();	
			$('#updateResultDiv').html(data);
			$('.submitButtonDiv').hide();
		}
	});	
});
</script>