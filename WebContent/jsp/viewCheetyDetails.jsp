<%@ page import="java.sql.ResultSet" %>
<%
String contextPath = request.getContextPath();
String scriptName = (String)application.getAttribute("SCRIPT-CONTEXT");
%>
<%
ResultSet rs = (ResultSet)request.getAttribute("resultSet");
Object CreateDate =(Object)request.getAttribute("CreateDate");
CreateDate = ( CreateDate != null) ? CreateDate : "";
String OwnerName =(String)request.getAttribute("OwnerName");
OwnerName = ( OwnerName != null) ? OwnerName : "";
if(OwnerName.length() > 15)OwnerName = OwnerName.substring(0, 15);
Object CheetyAmt =(Object)request.getAttribute("CheetyAmt");
CheetyAmt = ( CheetyAmt != null) ? CheetyAmt : "";
Object TotMonths =(Object)request.getAttribute("TotMonths");
TotMonths = ( TotMonths != null) ? TotMonths : "";
Object TotMembers =(Object)request.getAttribute("TotMembers");
TotMembers = ( TotMembers != null) ? TotMembers : "";
%>
<div style="width:890px;float:left;">
	<div style="width:889px;float:left;padding:5px;">
		<div style="width:889px;float:left;color: green;font-weight: bold;">
			<div class="cheetyViewHeader"><font color="red">CHEETY :</font><%=CheetyAmt%></div>
			<div class="cheetyViewHeader"><font color="red">DATE :</font><%=CreateDate%></div>
			<div class="cheetyViewHeader"><font color="red">MEMBERS :</font><%=TotMembers%></div>
			<div class="cheetyViewHeader"><font color="red">MONTHS :</font><%=TotMonths%></div>
			<div class="cheetyViewHeader"><font color="red">OWNER :</font><%=OwnerName%></div>
		</div>
		<div style="width:889px;float:left;color: blue;font-weight: bold;">
			<div class="view-cheety-field1">NAME</div>
			<div class="view-cheety-field">DATE</div>
			<div class="view-cheety-field2">STATUS</div>
			<div class="view-cheety-field">PATA NUM</div>
			<div class="view-cheety-field">PATA AMT</div>
			<div class="view-cheety-field">TOPU AMT</div>
			<div class="view-cheety-field">DUE AMT</div>
			<div class="view-cheety-field">TAKE AMT</div>
			<div class="view-cheety-field">PHONE</div>
		</div>
	<%
	while(rs.next()){
		int MEM_ID = rs.getInt("MEMBER_ID");
		MEM_ID = (MEM_ID > 0)?MEM_ID:0;
		String NAME = rs.getString("MEMBER_NAME");
		NAME = (NAME != null)?NAME:"";
		if(NAME.length() > 19)NAME = NAME.substring(0, 19);
		String DATE = rs.getString("PATA_DATE");
		DATE = (DATE == null)?"-":DATE;
		int PATA_NUMBER = rs.getInt("PATA_NUMBER");
		PATA_NUMBER = (PATA_NUMBER > 0)?PATA_NUMBER:0;
		String STATUS = rs.getString("STATUS");
		STATUS = (STATUS != null)?STATUS:"";
		int PATA_AMOUNT = rs.getInt("PATA_AMOUNT");
		PATA_AMOUNT = (PATA_AMOUNT > 0)?PATA_AMOUNT:0;
		int TOPU_AMOUNT = rs.getInt("TOPU_AMOUNT");
		TOPU_AMOUNT = (TOPU_AMOUNT > 0)?TOPU_AMOUNT:0;
		int DUE_AMOUNT = rs.getInt("DUE_AMOUNT");
		DUE_AMOUNT = (DUE_AMOUNT > 0)?DUE_AMOUNT:0;
		int TAKE_OF_AMOUNT = rs.getInt("TAKE_OF_AMOUNT");
		TAKE_OF_AMOUNT = (TAKE_OF_AMOUNT > 0)?TAKE_OF_AMOUNT:0;
		String PHONE = rs.getString("PHONE");
		PHONE = (PHONE != null)?PHONE:"";
	%>
		<div style="width:889px;float:left;">
			<div class="view-cheety-field1"><%=NAME%></div>
			<div class="view-cheety-field"><%=DATE%></div>
			<div class="view-cheety-field2"><%=STATUS%></div>
			<div class="view-cheety-field"><%=PATA_NUMBER%></div>	
			<div class="view-cheety-field"><%=PATA_AMOUNT%></div>
			<div class="view-cheety-field"><%=TOPU_AMOUNT%></div>
			<div class="view-cheety-field"><%=DUE_AMOUNT%></div>
			<div class="view-cheety-field"><%=TAKE_OF_AMOUNT%></div>
			<div class="view-cheety-field"><%=PHONE%></div>
		</div>
	
	<%
	} 
	%>
	<div id="cheetydownload" class="download-cheety_div">DOWNLOAD CHEETY DETAILS</div>
	<div id="cheetydownloadMsg"></div>
	</div>
</div>
<script type='text/javascript'>
$('#cheetydownload').click(function(){
	var cheety_id = $('#ch_code').val();
	var url="<%=scriptName%>/cheetydownload?cheety_id="+cheety_id+"";	
	$.ajax({
		type:	"get",
		url:	url, 
		success:function(msg){
			$('#cheetydownloadMsg').html(msg);
		}
	});
});
</script>