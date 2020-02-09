<%@include file="/WEB-INF/jspf/header.jspf"%>
<h2>Update Contact Information</h2>

<form action="updatedInfo" method="GET">
	Current email: ${email}<br>
	<p>Email</p>
	<input type="text" name="email" /> 
	<br> <br> <input type="submit" />
</form>