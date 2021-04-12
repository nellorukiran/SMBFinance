<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>

<div class="regOutDiv">
	<div class="cheety-out-div">
		<div class="cheety-in-div">
			<div class="viewCheetyOut">				
				<select id="ch_code" name="ch_code" style="width:272px;" autocomplete="off">
			        <option value=""></option>			        
			     </select>
			</div>
			<div class="viewSubmitFieldDiv"><input type="button" id="viewCheety" value="View" name="commit"></div>
		</div><!-- cheety-in-div -->
		<div id="cheety-view-result1"></div>
	</div> <!-- cheety-out-div -->
</div>
<script type='text/javascript'>
$(document).ready(function(){	
	var url="<%=scriptName%>/createCheetyMember";
	$.ajax({
		type:	"post",
		url:	url, 		
		success:function(msg){	
			var obj ="";
			try{
				obj = JSON.parse(msg);
			}catch(error){alert(error);}					
			var option_str = document.getElementById("ch_code");			
			option_str.options[0] = new Option("----- Select -----","");				
			for(var i = 0; i< obj.length;i++){				
				option_str.options[i+1] = new Option(obj[i].CHEETY_NAME,obj[i].CHEETY_ID);				
			}			
		}
	});
});
$('#viewCheety').click(function(){	
	var chCodeVal = $('#ch_code').val();
	var url="<%=scriptName%>/viewCheetyDetails?chCodeVal="+chCodeVal+"";
	$.ajax({
		type:	"post",
		url:	url,
		success:function(msg){
			$('#cheety-view-result1').html(msg);
		}
	});	
});
</script>