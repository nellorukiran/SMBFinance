<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("script.Name");
%>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="update-payment-search-out">
				<div class="downloadOutDiv">
					<div class="downloadInDiv" id="shopName">
						<img class="styleImg" src="<%=contextPath%>/images/excel-icon.png" alt="" width="25" height="25"/>
						ORDER BY SHOP NAME
				    </div>
					<div class="downloadInDiv" id="productName">
						<img class="styleImg" src="<%=contextPath%>/images/excel-icon.png" alt="" width="25" height="25"/>
						ORDER BY PRODUCT
					</div>
				</div>
			</div> <!-- update-payment-search-out -->        
		</div><!-- registerDiv -->
		<div class="downloadResultDiv" id="outMsgDiv"></div>
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->

<script type='text/javascript'>
$('#shopName').click(function(){
	var url="<%=scriptName%>/jsp/downloadProductDeatils?inputVal=ShopName";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#outMsgDiv').html(msg);
		}
	});
});
$('#productName').click(function(){
	var url="<%=scriptName%>/jsp/downloadProductDeatils?inputVal=ProductName";
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#outMsgDiv').html(msg);
		}
	});
});
</script>