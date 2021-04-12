<%@ taglib prefix="form" uri="/struts-tags" %>

	<form:if test="hasActionErrors()">
		<form:actionerror/>
	</form:if>
