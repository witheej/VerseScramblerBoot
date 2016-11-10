<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verse Scrambler</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div>
				<h3>Word Bank:</h3>
			</div>
			<div id="wordBank" class="well">
				<c:forEach items="${scrambledVerse}" var="i">
					<button class="btn btn-default scrambleWord">${i}</button></c:forEach>
			</div>
		</div>
		<div class="jumbotron jumbotron-answer">
			<div>
				<h3>Your Answer:</h3>
			</div>
			<div id="answerSpace" class="well"></div>
			<div>
				<button id="submitAnswer" class="btn btn-success">Submit!</button>
				<button id="clearAll" class="btn btn-danger">Clear All</button>
				<span id="response"></span>
			</div>
		</div>
	</div>
	<span id="solution"><c:out value="${answerVerse}" /></span>
</body>
</html>