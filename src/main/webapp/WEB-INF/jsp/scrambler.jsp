<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<link rel="stylesheet" href="css/main.css" type="text/css">
<script type="text/javascript">
	(function($, window, document) {

		$(function() {

			var $wordBank = $("#wordBank");
			var $answerSpace = $("#answerSpace");
			var $clearAll = $("#clearAll");
			var $submitAnswer = $("#submitAnswer");
			var $response = $("#response");
			var $solution = $("#solution");

			//Prevent #wordBank and #answerSpace from expanding/shrinking when words are moved around
			$answerSpace.height($wordBank.height());
			$wordBank.height($wordBank.height());

			//Click words in #wordBank to append them inside #answerSpace
			$wordBank.on("click", "button", function() {
				$(this).animateAppendTo("#answerSpace", 100);
			});

			//Click words in #answerSpace to send them back to #wordBank
			$answerSpace.on("click", "button", function() {
				$(this).animateAppendTo('#wordBank', 100);
			});

			//"Clear All" button sends all words in #answerSpace back into #wordBank
			$clearAll.on("click", function() {
				$("#answerSpace button").click();
			});

			//Checks answer and displays result
			$submitAnswer.on("click", function() {
				var ans = "";
				$("#answerSpace button").each(function() {
					ans += $(this).text() + " ";
				});
				var sol = $solution.text();
				if (ans.trim() == sol.trim()) {
					$response.text("Correct!");
				} else {
					$response.text("Wrong.");
				}
			});

		});

		//Animation for sending words from one place to another
		$.fn.animateAppendTo = function(sel, speed) {
			var $this = this, newEle = $this.clone(true).appendTo(sel), newPos = newEle.position();
			newEle.hide();
			$this.css("position", "absolute").animate(newPos, speed, function() {
				newEle.show();
				$this.remove();
			});
			return newEle;
		};

	}(window.jQuery, window, document));
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div>
				<h3>Word Bank:</h3>
			</div>
			<div id="wordBank" class="well">
				<c:forEach items="${scrambledVerse}" var="i"><button class="btn btn-default scrambleWord">${i}</button></c:forEach>
			</div>
		</div>
		<div class="jumbotron jumbotron-answer">
			<div>
				<h3>Your Answer:</h3>
			</div>
			<div id="answerSpace" class="well"></div>
			<div>
				<button id="submitAnswer"
					class="btn btn-success">Submit!</button>
				<button id="clearAll" class="btn btn-danger">Clear All</button>
				<span id="response"></span>
			</div>
		</div>
	</div>
	<span id="solution"><c:out value="${answerVerse}"/></span>
</body>
</html>