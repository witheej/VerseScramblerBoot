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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joshwithee.model.HomeForm;
import com.joshwithee.service.ESVService;
import com.joshwithee.service.VerseParser;

@Controller
public class WelcomeController {

	// @Value("${application.message:Hello World}")
	// private String message = "Hello World";
	//
	// @RequestMapping("/")
	// public String welcome(Map<String, Object> model) {
	// model.put("time", new Date());
	// model.put("message", this.message);
	// return "welcome";
	// }

	@Autowired
	private ESVService esvService = new ESVService();

	@Autowired
	private VerseParser verseParser = new VerseParser();

	@RequestMapping({ "", "/", "/home" })
	public String startHome(Model model, HttpServletRequest request) {
		HomeForm form = new HomeForm();
		model.addAttribute("submittedVerse", form);
		return "home";
	}

	@RequestMapping(value = "/scrambler")
	public String getScrambler(@ModelAttribute("submittedVerse") HomeForm submittedVerse, HttpServletRequest request,
			ESVService esvService) {

		String book = submittedVerse.getBook();
		int chapter = submittedVerse.getChapter();
		int verse = submittedVerse.getVerse();
		String mode = submittedVerse.getMode();
		int groupSize = setDifficulty(mode);

		String verseText = "";
		try {
			verseText += esvService.getPassage(book, chapter + ":" + verse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (verseText.matches("^ERROR.*")) {
			System.out.println("Method getScrambler says: Invalid reference.");
			return "redirect:home.html";
		} else {
			System.out.println("Method getScrambler says: Valid reference.");
		}

		String[] parsedVerse = verseParser.arrayifySingleVerse(verseText, groupSize);
		String answerVerse = verseParser.cleanVerse(verseText);

		request.setAttribute("answerVerse", answerVerse);
		request.setAttribute("parsedVerse", parsedVerse);

		if (parsedVerse.length > 1) {
			String[] scrambledVerse = verseParser.scrambleItems(parsedVerse);
			request.setAttribute("scrambledVerse", scrambledVerse);
		} else {
			request.setAttribute("scrambledVerse", parsedVerse);
		}

		return "scrambler";
	}

	private int setDifficulty(String mode) {
		int result = 1;

		if (mode != null) {
			if ("easy".equals(mode))
				result = 3;
			else if ("normal".equals(mode))
				result = 2;
			else if ("hard".equals(mode))
				result = 1;
			else {
				result = 5;
			}

		}

		return result;
	}

}
