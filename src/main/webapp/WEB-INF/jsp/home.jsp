<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verse Scrambler Homepage</title>
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
				<h1>Verse Scrambler!</h1>
				<p>Enter a verse and click "Submit" to begin.</p>
				<form:form id="referenceForm" commandName="homeForm"
					action="scrambler">
					<div class="row">
						<div class="col-xs-12">
							<label class="home-radio"> <form:radiobutton path="mode"
									value="veryeasy" /> Very Easy
							</label> <label class="home-radio"> <form:radiobutton path="mode"
									value="easy" checked="checked" /> Easy
							</label> <label class="home-radio"> <form:radiobutton path="mode"
									value="normal" /> Normal
							</label> <label class="home-radio"> <form:radiobutton path="mode"
									value="hard" /> Hard
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-4 col-md-3 home-input">
							<label for="bookSelection">Book:<form:select
									id="bookSelection" name="book" path="book" class="form-control">
									<option value="noBookSelected">Select A Book</option>
									<option value="Genesis">Genesis</option>
									<option value="Exodus">Exodus</option>
									<option value="Leviticus">Leviticus</option>
									<option value="Numbers">Numbers</option>
									<option value="Deuteronomy">Deuteronomy</option>
									<option value="Joshua">Joshua</option>
									<option value="Judges">Judges</option>
									<option value="Ruth">Ruth</option>
									<option value="1 Samuel">1 Samuel</option>
									<option value="2 Samuel">2 Samuel</option>
									<option value="1 Kings">1 Kings</option>
									<option value="2 Kings">2 Kings</option>
									<option value="1 Chronicles">1 Chronicles</option>
									<option value="2 Chronicles">2 Chronicles</option>
									<option value="Ezra">Ezra</option>
									<option value="Nehemiah">Nehemiah</option>
									<option value="Esther">Esther</option>
									<option value="Job">Job</option>
									<option value="Psalms">Psalms</option>
									<option value="Proverbs">Proverbs</option>
									<option value="Ecclesiastes">Ecclesiastes</option>
									<option value="Song of Solomon">Song of Solomon</option>
									<option value="Isaiah">Isaiah</option>
									<option value="Jeremiah">Jeremiah</option>
									<option value="Lamentations">Lamentations</option>
									<option value="Ezekiel">Ezekiel</option>
									<option value="Daniel">Daniel</option>
									<option value="Hosea">Hosea</option>
									<option value="Joel">Joel</option>
									<option value="Amos">Amos</option>
									<option value="Obadiah">Obadiah</option>
									<option value="Jonah">Jonah</option>
									<option value="Micah">Micah</option>
									<option value="Nahum">Nahum</option>
									<option value="Habakkuk">Habakkuk</option>
									<option value="Zephaniah">Zephaniah</option>
									<option value="Haggai">Haggai</option>
									<option value="Zechariah">Zechariah</option>
									<option value="Malachi">Malachi</option>
									<option value="Matthew">Matthew</option>
									<option value="Mark">Mark</option>
									<option value="Luke">Luke</option>
									<option value="John">John</option>
									<option value="Acts">Acts</option>
									<option value="Romans">Romans</option>
									<option value="1 Corinthians">1 Corinthians</option>
									<option value="2 Corinthians">2 Corinthians</option>
									<option value="Galatians">Galatians</option>
									<option value="Ephesians">Ephesians</option>
									<option value="Philippians">Philippians</option>
									<option value="Colossians">Colossians</option>
									<option value="1 Thessalonians">1 Thessalonians</option>
									<option value="2 Thessalonians">2 Thessalonians</option>
									<option value="1 Timothy">1 Timothy</option>
									<option value="2 Timothy">2 Timothy</option>
									<option value="Titus">Titus</option>
									<option value="Philemon">Philemon</option>
									<option value="Hebrews">Hebrews</option>
									<option value="James">James</option>
									<option value="1 Peter">1 Peter</option>
									<option value="2 Peter">2 Peter</option>
									<option value="1 John">1 John</option>
									<option value="2 John">2 John</option>
									<option value="3 John">3 John</option>
									<option value="Jude">Jude</option>
									<option value="Revelation">Revelation</option>
								</form:select></label>
						</div>
						<div class="col-xs-12 col-sm-3 col-md-2 home-input">
							<label for="chapterSelection">Chapter:<form:select
									id="chapterSelection" name="chapter" path="chapter" type="text"
									class="form-control" disabled="true">
									<option value="1">1</option>
								</form:select></label>
						</div>
						<div class="col-xs-12 col-sm-3 col-md-2 home-input">
							<label for="verseSelection">Verse:<form:select
									id="verseSelection" name="verse" path="verse" type="text"
									class="form-control" disabled="true">
									<option value="1">1</option>
								</form:select></label>
						</div>
						<div class="col-xs-12">
							<label for="submit">Submit:<input id="submit"
								name="submit" type="submit" value="Go!"
								class="form-control btn-primary" /></label>
						</div>
					</div>
				</form:form>
			<!-- 			<div> -->
			<!-- 				<a href="http://www.joshuawithee.com/walkthrough">Sixteen Verse Walkthrough</a> -->
			<!-- 			</div> -->
		</div>
	</div>
</body>
</html>