<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("script.Name");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div class="regOutDiv">
	<div class="download-out">
		<!-- <div class="downloadOutDiv">
			<div class="download-monthly">Monthly Payment</div>
			<div class="download-finance">Finance Payment</div>
			<div class="download-profit">Profit Details</div>
		</div> -->
		<center>
		<br><br><br><br>
		<h3>Monthly Reports</h2>
		
		<table >
			<tr>
				<td><a style="font-family: arial;font-size: 15px;font-weight: bold;text-decoration: none;color:blue;" href="userDownloadDeatilsCusId">Order By Customer ID</a></td>
			</tr>
			<tr>
				<td><a style="font-family: arial;font-size: 15px;font-weight: bold;color:blue;" href="userDownloadDeatilsAddress">Order By Address</a></td>
			</tr>
		</table>
		</center>
		<!-- <div class="download-order-outdiv" style="display:none;">
			<div class="download-order-id11"></div>
			<div class="download-order-id11"><a style="font-family: arial;font-size: 20px;font-weight: bold;text-decoration: none;color:blue;" href="userDownloadDeatilsAddress">ORDER BY ADDRESS</a></div>
		</div> -->
		<!-- <div class="download-order-finance-out" style="display:none;">
			<div class="download-order-finance-id">ORDER BY CUSTOMER ID</div>
			<div class="download-order-finance-address">ORDER BY ADDRESS</div>
		</div> -->
		<%-- <div class="download-order-profit-out" style="display:none;">
			<div class="profit-InDiv">
					<form action="">
					<div class="profit-lable-Out">
						<div class="profit-lable-name">From Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="fromDate" id="searchDateFrom" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div class="profit-lable-Out">
						<div class="profit-lable-name">To Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="toDate" id="searchDateTo" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div style="width:650px;float:left;text-align:center;margin: 10px;">
						<input class="profitDetails" id="x" type="button" value="Search" />
					</div>
					</form>
				</div><!-- profit-InDiv -->
		</div> --%>
		<div id="headDivMsg" class="download-disMsg" style="display:none;"></div>
	</div> <!-- download-out -->        
</div><!-- regOutDiv -->
<script type='text/javascript'>
$('.download-monthly').click(function(){
	$('.download-order-outdiv').show();;
	$('.download-order-finance-out').hide();
	$('.download-order-product-out').hide();
	$('.download-order-profit-out').hide();
	$('.download-disMsg').hide();
	$('.download-monthly').css('background-color','#EE178C');
	$('.download-profit').css('background-color','#ccccff');
	$('.download-finance').css('background-color','#ccccff');
	$('.download-product').css('background-color','#ccccff');
});
$('.download-finance').click(function(){
	$('.download-order-finance-out').show();
	$('.download-order-outdiv').hide();
	$('.download-order-product-out').hide();
	$('.download-order-profit-out').hide();
	$('.download-disMsg').hide();
	$('.download-monthly').css('background-color','#ccccff');
	$('.download-profit').css('background-color','#ccccff');
	$('.download-finance').css('background-color','#EE178C');
	$('.download-product').css('background-color','#ccccff');
});
$('.download-profit').click(function(){
	$('.download-order-profit-out').show();
	$('.download-order-product-out').hide();
	$('.download-order-outdiv').hide();
	$('.download-order-finance-out').hide();
	$('.download-disMsg').hide();
	$('.download-monthly').css('background-color','#ccccff');
	$('.download-profit').css('background-color','#EE178C');
	$('.download-finance').css('background-color','#ccccff');
	$('.download-product').css('background-color','#ccccff');
});
$('.profitDetails').click(function(){
	var frmDate = $('#searchDateFrom').val();
	var tDate = $('#searchDateTo').val();
	var url="<%=scriptName%>/userDownloadProfitDeatils?fromDate="+frmDate+"&toDate="+tDate+"";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.download-disMsg').show();
			$('#headDivMsg').html(msg);
		}
	});
});
$('.download-order-id').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=CustomerId&inputType=monthly&folderType=Monthly Details";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.download-disMsg').show();
			$('#headDivMsg').html(msg);
		}
	});
});
$('.download-order-address').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=Address&inputType=monthly&folderType=Monthly Details";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.download-disMsg').show();
			$('#headDivMsg').html(msg);
		}
	});
}); 
$('.download-order-finance-id').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=CustomerId&inputType=finance&folderType=Finance Details";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.download-disMsg').show();
			$('#headDivMsg').html(msg);
		}
	});
});
$('.download-order-finance-address').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=Address&inputType=finance&folderType=Finance Details";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('.download-disMsg').show();
			$('#headDivMsg').html(msg);
		}
	});
});
</script>