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
import javax.servlet.http.HttpServletResponse;

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

	@Autowired
	private VerseParser verseParser;

	@RequestMapping({ "", "/", "/home" })
	public String startHome(Model model, HttpServletRequest request) {
		HomeForm form = new HomeForm();
		model.addAttribute("homeForm", form);
		return "home";
	}

	@RequestMapping(value = "/scrambler")
	public String getScrambler(Model model, @ModelAttribute("homeForm") HomeForm homeForm, HttpServletRequest request,
			ESVService esvService) {
		request.setAttribute("reference", verseParser.getScriptureReference(homeForm));
		request.setAttribute("mode", homeForm.getMode());
		request.setAttribute("answerVerse", verseParser.getAnswerText(homeForm));
		request.setAttribute("scrambledVerse", verseParser.getScrambledVerse(homeForm));
		model.addAttribute("homeForm", verseParser.getNextVerse(homeForm));
		return "scrambler";
	}

}
