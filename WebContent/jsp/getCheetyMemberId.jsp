<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<%
String mem_id = "";
try{
	mem_id =request.getAttribute("MEM_ID").toString();
	mem_id = (mem_id != null)?mem_id : "";
}catch(Exception e){}
if(mem_id.length() > 0){;
%>
<div style="width:705px;float:left;margin-left: 300px;">
	<form method="POST" id="memberInsertForm">
		<div id="updateResultDiv" style="display:none;width:705px;float:left;font-family: arial;"></div>
		<div class="fieldDiv">
			<input type="text" name="CH_ID" value="<%=request.getParameter("CH_ID")%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="MEM_ID" value="<%=mem_id%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="MEM_NAME" value="<%=request.getParameter("MEM_NAME")%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="MEM_TYPE" value="<%=request.getParameter("MEM_TYPE")%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="ADDRESS" value="<%=request.getParameter("ADDRESS")%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="MEM_DATE" value="<%=request.getParameter("MEM_DATE")%>">
		</div>
		<div class="fieldDiv">
			<input type="text" name="PHONE" value="<%=request.getParameter("PHONE")%>">
		</div>
		<div class="submitFieldDiv"><input id="insertMember" type="button" value="Update" name="commit"></div>
	</form>
</div>
<%
}
%>
<script type='text/javascript'>
$('#insertMember').click(function(){	
	var data = $('#memberInsertForm').serialize();
	var url="<%=scriptName%>/cheetyMemberInsert";
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