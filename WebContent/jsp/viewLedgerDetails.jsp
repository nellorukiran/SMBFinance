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
		<div class="show-profit-details-outDiv">
			<div class="headDivBetween">VIEW LEDGER DETAILS</div>
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
						<input id="getLedgerDetails" type="button" value="Search" />
					</div>
					</form>
			</div><!-- profit-InDiv -->
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
$('.show-userdetails-profit').click(function(){	
	$('.show-profit-details-outDiv').show();
	$('.show-collection-details-outDiv').hide();
	$('#show-due-details-OutDiv').hide();
	$('#dueHistoryResultDiv').hide();

});
$('#getLedgerDetails').click(function(){	
	$('#show-userdetails-outerDiv').hide();
	$('#show-userdetails-closeDiv').hide();
	$('#show-userdetails-innerDiv').hide();	
	$('#show-due-details-OutDiv').hide();
	$('.show-profit-details-outDiv').show();
	var frmDate = $('#fromDate').val();
	var tDate = $('#toDate').val();
	$('#outMsgLoading').show();
	var url="<%=scriptName%>/getLedgerBetweenDates?fromDate="+frmDate+"&toDate="+tDate+"";
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
