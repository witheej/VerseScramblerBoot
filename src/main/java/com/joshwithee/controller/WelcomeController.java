/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.joshwithee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joshwithee.model.BibleInfo;
import com.joshwithee.model.HomeForm;
import com.joshwithee.service.VerseParser;

@Controller
public class WelcomeController {

	@Autowired
	private VerseParser verseParser;

	@RequestMapping({ "", "/", "/home" })
	public String startHome(Model model, HttpServletRequest request) {
		HomeForm form = new HomeForm();
		model.addAttribute("homeForm", form);
		return "home";
	}

	@RequestMapping(value = "/scrambler")
	public String getScrambler(Model model, @ModelAttribute("homeForm") HomeForm homeForm, HttpServletRequest request) {
		if (homeForm.getChapter() == 0 || homeForm.getVerse() == 0
				|| "noBookSelected".equals(homeForm.getBook() + "")) {
			return "redirect:home";
		}
		request.setAttribute("reference", verseParser.getScriptureReference(homeForm));
		request.setAttribute("mode", homeForm.getMode());
		request.setAttribute("answerVerse", verseParser.getAnswerText(homeForm));
		request.setAttribute("scrambledVerse", verseParser.getScrambledVerse(homeForm));
		model.addAttribute("homeForm", verseParser.getNextVerse(homeForm));
		return "scrambler";
	}

	@RequestMapping(value = "/walkthrough")
	public String getWalkthrough(HttpServletRequest request) {
		return "walkthrough";
	}

	@RequestMapping(value = "/andrew")
	public String andrew(HttpServletRequest request) {
		return "andrew";
	}

	@RequestMapping(value = "/andrewSubmit")
	public String getAndrew(@RequestParam(value = "andrew-verse-refs", required = false) String z, BibleInfo bibleInfo,
			HttpServletRequest request) {
		System.out.println(z);
		List<String> arr = new ArrayList<String>();

		if (z != null) {
			String y = z.replaceAll("\\s+", "").toLowerCase();
			String[] splittedRefs = y.split(",");
			for (String s : splittedRefs) {
				if (s.matches("[123]?[a-z]+1?[0-9]{1,2}:1?[0-9]{1,2}(-1?[0-9]{1,2})?")) {
					String v = verseParser.getVerseText(s);
					System.out.println("Verse text ----- " + v);

					String rawBookName = s.replaceAll("\\d+:\\d+(-\\d+)?", "");
					String chapterAndVerse = s.replaceAll(rawBookName, "");
					String bookName;
					if (bibleInfo.ABBREVIATIONMAP.containsKey(rawBookName)) {
						bookName = bibleInfo.ABBREVIATIONMAP.get(rawBookName).replaceAll("([123])", "$1 ");
					} else {
						bookName = rawBookName.replaceAll("([123])", "$1 ");
					}

					arr.add(bookName + " " + chapterAndVerse);
					arr.add(v);
				} else {
					arr.add("BAD SYNTAX >>> " + s + " <<<");
				}
			}
		}

		request.setAttribute("AndrewVerses", arr);

		return "andrew";
	}

}
