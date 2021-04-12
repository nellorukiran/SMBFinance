<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("script.Name");
//out.println(scriptName);
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>

<div class="show-userdetails-outDiv">
	<div class="show-userdetails-innerDiv">

		<div class="show-profit-details-outDiv">
		<form method="POST" id="createDailyLedgerForm">
			<div class="headDivBetween">DAILY LEDGER CREATION</div>
				<div id="dailyLedgerMessage" style="float:left;margin-left: 217px;margin-bottom: 15px;"></div>
				<div id="ledger-create-out-div" style="display:block;">
					<div class="fieldDiv">
						<input type="text" name="cus_id" id="cus_id" placeholder="Customer Id" autocomplete="off">
					</div>
					<div class="fieldDiv">
						<select name="trans_type" id="trans_type" style="width:272px;" autocomplete="off">
					        <option value="CASH">Loan A/C Cash Paid</option>
					        <option value="CHECK">Loan A/C Check Paid</option>
				     	</select>
					</div>
					<div class="fieldDiv">
						<input type="text" name="trans_date" id="trans_date" onclick="showCalendarControl(this);" placeholder="Date of Paid" autocomplete="off">
					</div>
					<div class="fieldDiv">
						<select name="trans_mode" id="trans_mode" style="width:272px;" autocomplete="off">
					        <option value="Dr">Debit</option>
					        <option value="Cr">Credit</option>
				     	</select>
					</div>
					<div class="fieldDiv">
						<input type="text" name="trans_amt" id="trans_amt" placeholder="Transaction Amount" autocomplete="off">
					</div>
					<div class="ledgerSubmitFieldDiv"><input type="button" id="createDailyLedger" value="Create" name="commit"></div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type='text/javascript'>
$('#createDailyLedger').click(function(){	
	var data = $('#createDailyLedgerForm').serialize();
	var url="<%=scriptName%>/createDailyLedger";
	$.ajax({
		type:	"post",
		url:	url, 
		data: data,
		success:function(data){	
			var msg="";
			if(data.trim() =="Ledger Inserted Successfully"){
				try{
				msg="<div style=\"float:left;\"><b><font color='green' size='2'>"+data+"</font>";
				$('#cus_id').val("").change();
				$('#trans_type').val("cash").change();
				$('#trans_date').val("").change();
				$('#trans_mode').val("D").change();
				$('#trans_amt').val("").change();
				}catch(error){alert(error);}
			}else{
				msg="<div style=\"float:left;\"><b><font color='red' size='2'>"+data+"</font>";
			}
			$('#dailyLedgerMessage').html(msg);	
		}
	});
});
</script>