<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("script.Name");
%>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="update-payment-search-out">
				<div class="downloadOutDiv">
					<div class="downloadInDiv" id="ledgerDetails">
						<img class="styleImg" src="<%=contextPath%>/images/excel-icon.png" alt="" width="25" height="25"/>
						CLICK HERE TO DOWNLOAD
				    </div>
				</div>
			</div> <!-- update-payment-search-out -->        
		</div><!-- registerDiv -->
		<div class="downloadResultDiv" id="outMsgDiv"></div>
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->

<script type='text/javascript'>
$('#ledgerDetails').click(function(){
	//alert("hi");
	var url="<%=scriptName%>/downloadLedgerDeatils";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
		
			//$('#outMsgDiv').show();
			$('#outMsgDiv').html(msg);
		}
	});
});
</script>