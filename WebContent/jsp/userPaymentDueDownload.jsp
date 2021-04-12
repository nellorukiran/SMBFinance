<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("script.Name");
%>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="update-payment-search-out">
				<div class="downloadOutDiv">
					
					<div class="downloadInDiv" id="orderByCusId">
						<img class="styleImg" src="<%=contextPath%>/images/excel-icon.png" alt="" width="25" height="25"/>
						ORDER BY CUSTMER ID
				    </div>
					<div class="downloadInDiv" id="orderByAddrress">
						<img class="styleImg" src="<%=contextPath%>/images/excel-icon.png" alt="" width="25" height="25"/>
						ORDER BY ADDRESS
					</div>
					
				</div>
			</div> <!-- update-payment-search-out -->        
		</div><!-- registerDiv -->
		<div id="headDivMsg" class="headDivMsg"></div>
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->

<script type='text/javascript'>
$('#orderByCusId').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=CustomerId";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){			
			$('#headDivMsg').html(msg);
		}
	});
});
$('#orderByAddrress').click(function(){
	var url="<%=scriptName%>/userDownloadDeatils?inputVal=Address";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#headDivMsg').html(msg);
		}
	});
});
</script>