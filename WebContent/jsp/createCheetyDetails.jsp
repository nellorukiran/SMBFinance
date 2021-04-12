<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();  
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>

<div class="regOutDiv">
	<div class="cheety-out-div">
		<div class="downloadOutDiv">
			<div class="download-monthly">Cheety Creation</div>
			<div class="download-monthly">Member Creation</div>
			<div class="download-monthly">Updation</div>
		</div><!-- cheety-in-div -->
		<div id="cheety-message-out-div"></div>
		<div id="cheety-create-out-div" style="display:block;">
			<form method="post" id="createCheetyForm">
				<div class="fieldDiv">
					<input type="text" name="CH_NAME" value="" placeholder="Cheety Name" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="CH_RANGE" value="" placeholder="Cheety Amount or Range" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="OWNER_NAME" value="" placeholder="Owner Name" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="CH_DATE" value="" onclick="showCalendarControl(this);" placeholder="Date of Started" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="TOT_MEMBERS" value="" placeholder="Cheety Total Members" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="TOT_MONTHS" value="" placeholder="Cheety Total Months" autocomplete="off">
				</div>
				<div class="submitFieldDiv"><input type="button" id="getCheetyId" value="Register" name="commit"></div>
			</form>
		</div>
		<div id="cheety-member-out-div" style="display:none;">
			<form method="post" id="createCheetyMemberForm">
				<div class="fieldDiv">				
					<select id="CH_ID" name="CH_ID" style="width:272px;" autocomplete="off">
				        <option value=""></option>			        
				     </select>
				</div>
				<div class="fieldDiv">				
					<select name="MEM_TYPE" style="width:272px;" autocomplete="off">
				        <option value="member">Member</option>
				        <option value="owner">Owner</option>
				     </select>
				</div>
				<div class="fieldDiv">
					<input type="text" name="MEM_NAME" value="" placeholder="Members Name" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="ADDRESS" value="" placeholder="Members Address" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="MEM_DATE" value="" onclick="showCalendarControl(this);" placeholder="Members Date" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="PHONE" value="" placeholder="Members Phone" autocomplete="off">
				</div>
				<div class="submitFieldDiv"><input type="button" id="getCheetyMemberId" value="Register" name="commit"></div>
			</form>
		</div>
		<div id="cheety-member-out-result-div" style="display:none;"></div>
		<div id="cheety-update-out-div" style="display:none;">
			<form method="POST" id="cheetyCalculationForm">
				<div class="fieldDiv">				
					<select id="ch_code" name="ch_code" style="width:272px;" autocomplete="off">
				        <option value=""></option>			        
				     </select>
				</div>
				<div class="fieldDiv">				
					<select id="mem_code" name="mem_code" style="width:272px;" autocomplete="off">
				        <option value=""></option>
				     </select>
				</div>
				<div class="fieldDiv">
					<input type="text" name="TAKE_AMT" value="" placeholder="Pata Amount" autocomplete="off">
				</div>
				<div class="fieldDiv">
					<input type="text" name="TAKE_DATE" onclick="showCalendarControl(this);" placeholder="Pata Of Date" autocomplete="off">
				</div>
				<div class="submitFieldDiv"><input type="button" id="cheetyCalculate" value="Register" name="commit"></div>
			</form>
		</div>	
		<div id="cheety-update-out-result-div" style="display:none;"></div>
	</div> <!-- cheety-out-div -->
</div>
<script type='text/javascript'>
$('.cheety-type').click(function(){
	$('.cheety-type').css('background-color','#00FFFF');
	$('.cheety-member').css('background-color','#DDA0DD');
	$('.cheety-update').css('background-color','#DDA0DD');
	$('#cheety-create-out-div').show();
	$('#cheety-update-out-div').hide();	
	$('#cheety-member-out-div').hide();
	$('#cheety-member-out-result-div').hide();
	$('#cheety-update-out-result-div').hide();
});
$('.cheety-member').click(function(){
	$('.cheety-type').css('background-color','#DDA0DD');
	$('.cheety-member').css('background-color','#00FFFF');
	$('.cheety-update').css('background-color','#DDA0DD');
	$('#cheety-member-out-div').show();
	$('#cheety-update-out-div').hide();	
	$('#cheety-create-out-div').hide();
	$('#cheety-update-out-result-div').hide();
	$('#cheety-member-out-result-div').hide();
	var url="<%=scriptName%>/createCheetyMember";
	$.ajax({
		type:	"post",
		url:	url, 		
		success:function(msg){	
			var obj ="";
			try{
				obj = JSON.parse(msg);
			}catch(error){}			
			var option_str = document.getElementById("CH_ID");			
			option_str.options[0] = new Option("----- Select -----","");				
			for(var i = 0; i< obj.length;i++){				
				option_str.options[i+1] = new Option(obj[i].CHEETY_NAME,obj[i].CHEETY_ID);				
			}			
		}
	});
});
$('.cheety-update').click(function(){	
	$('.cheety-type').css('background-color','#DDA0DD');
	$('.cheety-member').css('background-color','#DDA0DD');
	$('.cheety-update').css('background-color','#00FFFF');
	$('#cheety-update-out-div').css('margin-left','300px');
	$('#cheety-member-out-div').hide();
	$('#cheety-update-out-div').show();	
	$('#cheety-create-out-div').hide();
	$('#cheety-update-out-result-div').hide();
	$('#cheety-member-out-result-div').hide();
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
$('#ch_code').change(function(){
	var chCodeVal = $('#ch_code').val();
	var url="<%=scriptName%>/createCheetyMemberIdList?chCodeVal="+chCodeVal+"";
	$.ajax({
		type:	"post",
		url:	url,		
		success:function(data){
			var obj ="";
			try{
				obj = JSON.parse(data);
			}catch(error){}	
			var option_str = document.getElementById("mem_code");
			option_str.options[0] = new Option("----- Select -----","");			
			for(var i = 0; i< obj.length;i++){				
				option_str.options[i+1] = new Option(obj[i].MEM_NAME,obj[i].MEM_ID);			
			}
		}
	});	
});
$('#getCheetyMemberId').click(function(){	
	var data = $('#createCheetyMemberForm').serialize();
	var url="<%=scriptName%>/getCheetyMemberId";
	$.ajax({
		type:	"post",
		url:	url,
		data: data,
		success:function(data){
			$('#cheety-member-out-div').hide();
			$('#cheety-member-out-result-div').show();
			$('#cheety-member-out-result-div').html(data);
		}
	});	
});
$('#getCheetyId').click(function(){	
	var data = $('#createCheetyForm').serialize();
	var url="<%=scriptName%>/getCheetyId";
	$.ajax({
		type:	"post",
		url:	url,
		data: data,
		success:function(data){			
			$('#cheety-create-out-div').html(data);
		}
	});	
});
$('#cheetyCalculate').click(function(){	
	var data = $('#cheetyCalculationForm').serialize();
	var url="<%=scriptName%>/cheetyCalculation";
	$.ajax({
		type:	"post",
		url:	url,
		data: data,
		success:function(data){	
			$('#cheety-update-out-div').hide();	
			$('#cheety-update-out-result-div').show();
			$('#cheety-update-out-div').css('margin-left','300px');
			$('#cheety-update-out-result-div').html(data);			
		}
	});	
});
</script>