<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<div style="width:705px;float:left;margin-left: 85px;">
<form method="POST" id="cheetyUpdateForm">
	<div style="width:705px;float:left;">
		<div id="updateResultDiv" style="display:none;width:705px;float:left;font-family: arial;margin-left:210px;"></div>
		<div style="width:350px;float:left;">
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">CHEETY CODE :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="CH_CODE" value="<%=request.getAttribute("CH_CODE")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">MEMBER CODE :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="MEM_CODE" value="<%=request.getAttribute("MEM_CODE")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">CHEETY AMOUNT :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="CHEETY_AMOUNT" value="<%=request.getAttribute("CHEETY_AMOUNT")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">TOTAL MONTHS :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="TOT_MONTHS" value="<%=request.getAttribute("TOT_MONTHS")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">TOTAL MEMBERS :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="TOT_MEM" value="<%=request.getAttribute("TOT_MEM")%>" autocomplete="off">
				</div>
			</div>
		</div>
		<div style="width:350px;float:left;">
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">PER MONTH :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="TOT_DUE" value="<%=request.getAttribute("TOT_DUE")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">DUE AMOUNT :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="DUE_AMOUNT" value="<%=request.getAttribute("DUE_AMOUNT")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">PATA AMOUNT :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="PATA_AMOUNT" value="<%=request.getAttribute("PATA_AMOUNT")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">TOOPU AMOUNT :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="TOPU_AMOUNT" value="<%=request.getAttribute("TOPU_AMOUNT")%>" autocomplete="off">
				</div>
			</div>
			<div class="fieldOutDiv">
				<div class="fieldLblDiv">DATE :</div>
				<div class="fieldTxtDiv">
					<input type="text" name="PATA_DATE" value="<%=request.getAttribute("PATA_DATE")%>" autocomplete="off">
				</div>
			</div>
		</div>
		<div class="submitButtonDiv"><input id="cheetyUpdate" type="button" value="Update" name="commit"></div>
	</div>
</form>
</div>
<script type='text/javascript'>
$('#cheetyUpdate').click(function(){	
	var data = $('#cheetyUpdateForm').serialize();
	var url="<%=scriptName%>/cheetyUpdate";	
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