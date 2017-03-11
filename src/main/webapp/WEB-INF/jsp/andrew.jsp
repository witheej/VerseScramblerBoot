<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Andrew's Page</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" type="text/css">

</head>
<body>
<div class="container-fluid">
	<div class="jumbotron">
	<div>
		<h3>List verse references separated by commas:</h3>
		<pre>gen5:12,ex10:1-100,1pet2:5,rev12:3-5</pre>
		<h3>Spaces are acceptable.</h3>
		<h3>Full book names or abbreviations are acceptable.</h3>
	</div>



	<form action="andrewSubmit">
		<input type="text" name="andrew-verse-refs" size="150" /> <br> <input
			type="submit" />
	</form>
	</div>
	</div>
	<div class="container-fluid">
	<div class="jumbotron">
		<span id="andrew-verse-output"> <c:forEach
				items="${AndrewVerses}" var="i">
				<p>${i}</p>
			</c:forEach>
		</span>
	</div>
	</div>
</body>
</html>