(function($, window, document) {

		$(function() {
			
			//HOME.JSP
			
			var $bookSelection = $('#bookSelection');
			var $chapterSelection = $('#chapterSelection');
			var $verseSelection = $('#verseSelection');

			$bookSelection.val('noBookSelected')
			$chapterSelection.val("");
			$verseSelection.val("");
			$chapterSelection.prop('disabled', true);
			$verseSelection.prop('disabled', true);

			$bookSelection.on('change', onBookChange);

			$chapterSelection.on('change', onChapterChange);

			function onChapterChange() {
				if ($bookSelection.val() == "") {
					$verseSelection.val("");
					$verseSelection.prop('disabled', true);
				} else {
					var b = encodeURIComponent($bookSelection
							.val());
					var c = $chapterSelection.val();
					var url = '/versesInChapter?b='
							+ b + '&c=' + c;
					$
							.get(
									url,
									function(data, status) {
										$verseSelection
												.html("");
										var z;
										for (z = 1; z <= parseInt(
												data.limit, 10); z++) {
											$('<option>')
													.val(z)
													.text(z)
													.appendTo(
															'#verseSelection');
										}

									}, 'json');
					$verseSelection.val('1');
					$verseSelection.prop('disabled', false);
				}
			}

			function onBookChange() {
				if ($bookSelection.val() == 'noBookSelected') {
					$chapterSelection.val("");
					$chapterSelection.html("");
					$verseSelection.val("");
					$verseSelection.html("");
					$chapterSelection.prop('disabled', true);
					$verseSelection.prop('disabled', true);
				} else {
					$chapterSelection.html("");
					var C = numberOfChaptersInSelectedBook();
					var z;
					for (z = 1; z <= C; z++) {
						$('<option>').val(z).text(z).appendTo(
								'#chapterSelection');
					}
					$chapterSelection.val('1');
					$chapterSelection.prop('disabled', false);
					$chapterSelection.change();
				}
			}

			function numberOfChaptersInSelectedBook() {
				switch ($bookSelection.val()) {
				case 'Obadiah':
				case 'Philemon':
				case '2 John':
				case '3 John':
				case 'Jude':
					return 1;
				case 'Haggai':
					return 2;
				case 'Joel':
				case 'Nahum':
				case 'Habakkuk':
				case 'Zephaniah':
				case '2 Thessalonians':
				case 'Titus':
				case '2 Peter':
					return 3;
				case 'Ruth':
				case 'Jonah':
				case 'Malachi':
				case 'Philippians':
				case 'Colossians':
				case '2 Timothy':
					return 4;
				case 'Lamentations':
				case '1 Thessalonians':
				case 'James':
				case '1 Peter':
				case '1 John':
					return 5;
				case 'Galatians':
				case 'Ephesians':
				case '1 Timothy':
					return 6;
				case 'Micah':
					return 7;
				case 'Song of Songs':
					return 8;
				case 'Amos':
					return 9;
				case 'Ezra':
				case 'Esther':
					return 10;
				case 'Ecclesiastes':
				case 'Daniel':
					return 12;
				case 'Nehemiah':
				case '2 Corinthians':
				case 'Hebrews':
					return 13;
				case 'Hosea':
				case 'Zechariah':
					return 14;
				case 'Mark':
				case 'Romans':
				case '1 Corinthians':
					return 16;
				case 'Judges':
				case 'John':
					return 21;
				case '1 Kings':
				case 'Revelation':
					return 22;
				case 'Joshua':
				case '2 Samuel':
				case 'Luke':
					return 24;
				case '2 Kings':
					return 25;
				case 'Leviticus':
					return 27;
				case 'Matthew':
				case 'Acts':
					return 28;
				case '1 Chronicles':
					return 29;
				case '1 Samuel':
				case 'Proverbs':
					return 31;
				case 'Deuteronomy':
					return 34;
				case 'Numbers':
				case '2 Chronicles':
					return 36;
				case 'Exodus':
					return 40;
				case 'Job':
					return 42;
				case 'Ezekiel':
					return 48;
				case 'Genesis':
					return 50;
				case 'Jeremiah':
					return 52;
				case 'Isaiah':
					return 66;
				case 'Psalms':
					return 150;
				default:
					alert('Error in chapter validation switch statement.');
				}

			}
			
			//SCRAMBLER.JSP

			var $wordBank = $("#wordBank");
			var $answerSpace = $("#answerSpace");
			var $clearAll = $("#clearAll");
			var $submitAnswer = $("#submitAnswer");
			var $response = $("#response");
			var $solution = $("#solution");
			var $scramblerReferenceForm = $("#scramblerReferenceForm");

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
				    bootbox.confirm({
				        message: "Success! Continue to the next verse?",
				        buttons: {
				            confirm: {
				                label: 'Yes',
				                className: 'btn-success'
				            },
				            cancel: {
				                label: 'No',
				                className: 'btn-danger'
				            }
				        },
				        callback: function (result) {
				            console.log('This was logged in the callback: ' + result);
				            if(result){
				            	document.getElementById("homeForm").submit();
				            }
				        }
				    });
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