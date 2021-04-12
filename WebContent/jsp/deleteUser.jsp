<%@ taglib prefix="form" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();
%>
<link rel="stylesheet" href="<%=contextPath%>/css/CalendarControl.css"/>
<script type="text/javascript" src="<%=contextPath%>/js/CalendarControl.js"></script>
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div class="registerDiv">
			<div class="update-payment-search-out">
				<form action="deleteDetails" method="post">
					<div class="headDiv">PAYMENT UPDATION</div>
					<div class="update-payment-search-inner">
						<div style="width:320px;float:left;">
							<div class="update-payment-userId">User Id</div>
							<div class="update-payment-textbox"><input type="text" value="" placeholder="User Id" name="cus_id" /></div>
						</div>
						<div class="update-payment-textbox1">
							<select name="deleteUser" style="width:150px;">
	                          	<option value="all">All</option>
	                          	<option value="user">User</option>
					     	</select>
						</div>
						<div class="update-payment-submit"><input type="submit" value="Search"></div>
					</div>
				</form>
			</div><!-- update-payment-search-out -->
			<div class="headDivMsg">
				<form:if test="hasActionErrors()">
					<div class="errorMsg">
						<form:actionerror/>
					</div>
				</form:if>
			</div>			
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->