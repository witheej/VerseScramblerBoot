<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sixteen-Verse Walkthrough</title>
</head>
<body>


<div class="container">
		<div class="jumbotron tight-padding">
			<div>
				<h3>Genesis 1:31</h3>
			</div>
			<div id="wordBank" class="well tight-padding">And God saw everything that he had made, and behold, it was very good. And there was evening and there was morning, the sixth day.
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








</body>
</html>