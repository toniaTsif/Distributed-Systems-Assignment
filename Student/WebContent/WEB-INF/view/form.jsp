<%@include file="/WEB-INF/jspf/header.jspf"%>

<h2>Form</h2>

<form action="processForm" method="GET">
	<p>Fill in the following form.</p>
	
	<p>Email</p>
	<input type="text" name="email" />

	<p>Year of Studies</p>
	<input type="number" name="studies" />

	<p>Years in Accommodation</p>
	<input type="number" name="accomodation" />

	<p>Income</p>
	<input type="number" name="income" />

	<p>How many siblings who study do you have?</p>
	<input type="number" name="siblings" />

	<p>Are your parents unemployed?</p>
	<input type="radio" name="parents" value="true">Yes<br>
	<input type="radio" name="parents" value="false">No<br> 
	
	<p>Do you live in a different city than the city of the university?</p>
	<input type="radio" name="city" value="true">Yes<br>
	<input type="radio" name="city" value="false">No<br>
	
	<br> <br> <input type="submit" value="Submit"/>
	<button type="cancel" onclick="javascript:window.location='http://localhost:8080/Assignment/student/form;">Cancel</button>

</form>
