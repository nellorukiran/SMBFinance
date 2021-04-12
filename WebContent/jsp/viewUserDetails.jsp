<style>
#show-userdetails-innerDiv {
	background-color: #FFFFFF;
	border: 2px solid; 
	border-radius: 15px 15px 15px 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);color: #000;  
	display: none;
	height: 490px;
	left: 310px;	
	top: 351px;
	width: 1254px;
	z-index: 1020;	
	position: fixed;
	/*position:absolute;*/	
	top: 50%;
    left: 60%;
	margin-top: -230px; 
    margin-left: -755px;
    overflow-x: auto;
    overflow-y: auto;
}
#show-userdetails-outerdiv {
	float:left;
	background-color: #000000;	
	display:none;
	height: 100%;
	width: 100%;	
	left: 0;
	top: 0;
	margin: 0;	
	padding: 0;
	overflow: hidden;			
	filter:alpha(opacity=50);	
	opacity: 0.5;
	position: fixed;
	*position:absolute;	
	z-index: 1019;
	opacity: 0.8;
}
#show-userdetails-profit-innerDiv {
	background-color: #FFFFFF;
	border: 2px solid; 
	border-radius: 15px 15px 15px 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);color: #000;  
	display: none;
	height: 490px;
	left: 310px;	
	top: 351px;
	width: 873px;
	z-index: 1020;	
	position: fixed;
	/*position:absolute;*/	
	top: 50%;
    left: 60%;
	margin-top: -230px; 
    margin-left: -572px;
    overflow-x: auto;
    overflow-y: auto;
}
#show-userdetails-profit-outerdiv {
	float:left;
	background-color: #000000;	
	display:none;
	height: 100%;
	width: 100%;	
	left: 0;
	top: 0;
	margin: 0;	
	padding: 0;
	overflow: hidden;			
	filter:alpha(opacity=50);	
	opacity: 0.5;
	position: fixed;
	*position:absolute;	
	z-index: 1019;
	opacity: 0.8;
}
#show-userdetails-profit-closeDiv{
	z-index:1100;    
	/*width:3%;*/
	float:right;	
	vertical-align:top;
	cursor:pointer; 
	/*margin-right: 3px;
    margin-top: -15px;*/
    
}
#show-userdetails-closeDiv{
	z-index:1100;    
	/*width:3%;*/
	float:right;	
	vertical-align:top;
	cursor:pointer; 
	/*margin-right: 3px;
    margin-top: -15px;*/
    
}
</style>
<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("script.Name");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div class="show-userdetails-outDiv">
	<div class="show-userdetails-innerDiv">
		<div class="show-userdetails-in-outDiv">
			<div class="show-userdetails-transation">Transaction Details</div>
			<div class="show-userdetails-payment">Payment Details</div>
			<div class="show-userdetails-profit">Monthly Files</div>
			<div class="show-user-details-due-history">Monthly Collection</div>
			<div class="show-user-details-due">Due History</div>
		</div>
		<div class="show-profit-details-outDiv">
			<div class="headDivBetween">NEW REGISTER FILES</div>
			<div class="show-profit-details-InDiv">
					<form action="">
					<div class="profit-lable-Out">
						<div class="profit-lable-name">From Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="fromDate" id="fromDate" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div class="profit-lable-Out">
						<div class="profit-lable-name">To Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="toDate" id="toDate" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div style="width:650px;float:left;text-align:center;margin: 10px;">
						<input id="getProfitDetails" type="button" value="Search" />
					</div>
					</form>
			</div><!-- profit-InDiv -->
		</div>
		<div class="show-collection-details-outDiv" style="display:none;">
			<div class="headDivBetween">COLLECTION DETAILS</div>
			<div class="show-profit-details-InDiv">
					<form action="">
					<div class="profit-lable-Out">
						<div class="profit-lable-name">From Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="fromDate" id="fromDate1" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div class="profit-lable-Out">
						<div class="profit-lable-name">To Date :</div>
						<div class="profit-lable-input">
							<input class="color" type="text" size="20" name="toDate" id="toDate1" onclick="showCalendarControl(this);" readonly/>
						</div>
					</div><!-- profit-lable-Out -->
					<div style="width:650px;float:left;text-align:center;margin: 10px;">
						<input id="getCollectionDetails" type="button" value="Search" />
					</div>
					</form>
			</div><!-- profit-InDiv -->
		</div>
		<div id="show-due-details-OutDiv" style="display:none;">
		
		<form name="getUserDueDetailsForm" method="post" id="getUserDueDetailsForm">
			<div class="show-due-details-InDiv">
				<div class="profit-lable-name">User Id :</div>
				<div class="profit-lable-input">
					<input type="text" size="20" name="cus_id" id="cus_id" />
				</div>
				<div class="view-user-due-button" >
					<input id="getDueDetails" type="button" value="Search" />
				</div>
			</div><!-- profit-lable-Out -->
			<div class="errorMsgCus" id="errorMsgCus" style="display:none;"></div>
		</form>
		</div>
		<div class="dueHistoryResultDiv" id="dueHistoryResultDiv" style="display:none;"></div>
		<div style="width:0px;margin-left:396px;margin-top:20px;display:none;" id="outMsgLoading">
			<img width="100" height="100" alt="loading" src="<%=contextPath%>/images/ajax-loader1.gif">
		</div>
		<div class="downloadResultDiv" id="outMsgDiv"></div>
	</div> <!-- download-out -->        
</div><!-- regOutDiv -->

<div id="show-userdetails-outerdiv" class="show-userdetails-outerdiv"></div>
<div id="show-userdetails-innerDiv" class="show-userdetails-innerDiv">
	<div id="show-userdetails-closeDiv" class="show-userdetails-closeDiv" style="display:none;">
		<img src='<%=contextPath%>/images/close-icon.png' alt='X' title='Close' width="25px" height="25px" style='cursor:pointer;'/>
	</div>
	<div id="viewUserDetails"></div>
</div>
<div id="show-userdetails-profit-outerdiv" class="show-userdetails-profit-outerdiv"></div>
<div id="show-userdetails-profit-innerDiv" class="show-userdetails-profit-innerDiv">
	<div id="show-userdetails-profit-closeDiv" class="show-userdetails-profit-closeDiv" style="display:none;">
		<img src='<%=contextPath%>/images/close-icon.png' alt='X' title='Close' width="25px" height="25px" style='cursor:pointer;'/>
	</div>
	<div id="viewUserDetails-profit"></div>
</div>
<script>
$(document).keyup(function(e) {
	  if (e.keyCode == 27) { closeSocialPopup(); }   // esc
});
function closeSocialPopup(){	
	$('#show-userdetails-outerDiv').hide();
	$('.show-collection-details-outDiv').hide();
	$('#show-userdetails-closeDiv').hide();
	$('#show-userdetails-innerDiv').hide();	
	$('#show-userdetails-profit-outerDiv').hide();
	$('#show-due-details-OutDiv').hide();
	$('#show-userdetails-profit-closeDiv').hide();
	$('#show-userdetails-profit-innerDiv').hide();	
	$('#viewUserDetails').hide();	
	$('#viewUserDetails-profit').hide();
	$('.show-profit-details-outDiv').show();
}
$('#show-userdetails-profit-closeDiv').click(function(){		
	closeSocialPopup();
});
$('#show-userdetails-closeDiv').click(function(){		
	closeSocialPopup();
});
$('#show-userdetails-outerDiv').click(function(){	
	closeSocialPopup();
}); 
$('#show-userdetails-profit-outerDiv').click(function(){	
	closeSocialPopup();
});
$('.show-userdetails-transation').click(function(){		
	$('#show-userdetails-profit-outerDiv').hide();
	$('#show-userdetails-profit-closeDiv').hide();
	$('#show-userdetails-profit-innerDiv').hide();
	$('#show-due-details-OutDiv').hide();
	$('#outMsgLoading').show();
	$('.show-profit-details-outDiv').hide();
	$('.payment-history-result-out').hide();
	$('.show-profit-details-outDiv').hide();
	$('.show-collection-details-outDiv').hide();
	$('#dueHistoryResultDiv').hide();
	var url="<%=scriptName%>/viewUserDetails?inputVal=TRANSACTION";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#show-userdetails-outerDiv').show();
			$('#show-userdetails-innerDiv').show();	
			$('#show-userdetails-closeDiv').show();
			$('#outMsgLoading').hide();
			$('#viewUserDetails').show();
			$('#viewUserDetails').html(msg);
		}
	});
	
});
$('.show-user-details-due').click(function(){	
	$('.show-profit-details-outDiv').hide();
	$('.show-collection-details-outDiv').hide();
	$('#show-due-details-OutDiv').show();
	$('#dueHistoryResultDiv').hide();
	
});
$('.show-user-details-due-history').click(function(){	
	$('.show-profit-details-outDiv').hide();
	$('.payment-history-result-out').hide();
	$('.show-collection-details-outDiv').show();
	$('#show-due-details-OutDiv').hide();
	$('#dueHistoryResultDiv').hide();
	
});
$('#getDueDetails').click(function(){	
	$('.show-profit-details-outDiv').hide();
	$('.show-collection-details-outDiv').hide();
	$("#getUserDueDetailsForm").validate();
	$('#show-due-details-OutDiv').show();
	$('#dueHistoryResultDiv').hide();
	$("#errorMsgCus").hide();
	var cus_id = $('#cus_id').val();
	if(cus_id.length > 0){
		$('#outMsgLoading').show();
		var url="<%=scriptName%>/viewPaymentHistoryDetails?inputVal="+cus_id+"";
		$.ajax({
			type:	"get",
			url:	url, 
			success:function(msg){
				$('#outMsgLoading').hide();
				$('#dueHistoryResultDiv').show();
				$('#dueHistoryResultDiv').html(msg);
			}
		});
	}else{
		$("#errorMsgCus").show();
		$("#errorMsgCus").html("Please Enter Cus id");
	}
});
$('.show-userdetails-payment').click(function(){		
	$('#show-userdetails-profit-outerDiv').hide();
	$('#show-userdetails-profit-closeDiv').hide();
	$('#show-userdetails-profit-innerDiv').hide();
	$('#show-due-details-OutDiv').hide();
	$('.show-profit-details-outDiv').hide();
	$('.payment-history-result-out').hide();
	$('#dueHistoryResultDiv').hide();
	$('#outMsgLoading').show();
	var url="<%=scriptName%>/viewUserDetails?inputVal=PAYMENTS";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#show-userdetails-outerDiv').show();
			$('#show-userdetails-innerDiv').show();	
			$('#show-userdetails-closeDiv').show();
			$('#outMsgLoading').hide();
			$('#viewUserDetails').show();
			$('#viewUserDetails').html(msg);
		}
	});
	
});
$('.show-userdetails-profit').click(function(){	
	$('.show-profit-details-outDiv').show();
	$('.show-collection-details-outDiv').hide();
	$('#show-due-details-OutDiv').hide();
	$('#dueHistoryResultDiv').hide();

});
$('#getProfitDetails').click(function(){	
	$('#show-userdetails-outerDiv').hide();
	$('#show-userdetails-closeDiv').hide();
	$('#show-userdetails-innerDiv').hide();	
	$('#show-due-details-OutDiv').hide();
	$('#dueHistoryResultDiv').hide();
	$('.show-profit-details-outDiv').show();
	var frmDate = $('#fromDate').val();
	var tDate = $('#toDate').val();
	$('#outMsgLoading').show();
	var url="<%=scriptName%>/viewCustomerProfitDetails?fromDate="+frmDate+"&toDate="+tDate+"";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#show-userdetails-profit-outerDiv').show();
			$('#show-userdetails-profit-innerDiv').show();	
			$('#show-userdetails-profit-closeDiv').show();
			$('#outMsgLoading').hide();
			$('#viewUserDetails-profit').show();
			$('#viewUserDetails-profit').html(msg);
		}
	});
	
});
$('#getCollectionDetails').click(function(){	
	$('#show-userdetails-outerDiv').hide();
	$('#show-userdetails-closeDiv').hide();
	$('#show-userdetails-innerDiv').hide();	
	$('#show-due-details-OutDiv').hide();
	$('#dueHistoryResultDiv').hide();
	$('.show-collection-details-outDiv').show();
	var frmDate1 = $('#fromDate1').val();
	var tDate1 = $('#toDate1').val();
	$('#outMsgLoading').show();
	var url="<%=scriptName%>/viewMonthlyCollectionDetails?fromDate="+frmDate1+"&toDate="+tDate1+"";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#show-userdetails-profit-outerDiv').show();
			$('#show-userdetails-profit-innerDiv').show();	
			$('#show-userdetails-profit-closeDiv').show();
			$('#outMsgLoading').hide();
			$('#viewUserDetails-profit').show();
			$('#viewUserDetails-profit').html(msg);
		}
	});
	
});
</script>
