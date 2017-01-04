package com.joshwithee.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshwithee.model.BibleInfo;
import com.joshwithee.model.HomeForm;
import com.joshwithee.util.VerseScramblerBootUtil;

@Service
public class VerseParser {

	@Autowired
	private VerseScramblerBootUtil util;

	private BibleInfo bibleInfo = new BibleInfo();

	public String[] getScrambledVerse(HomeForm hf) {
		String verseText = getVerseText(hf.getBook(), hf.getChapter(), hf.getVerse());
		String[] parsedVerse = arrayifySingleVerse(verseText, setDifficulty(hf.getMode()));
		return util.scrambleItems(parsedVerse);
	}

	public String getAnswerText(HomeForm hf) {
		return getVerseText(hf.getBook(), hf.getChapter(), hf.getVerse());
	}

	public String getScriptureReference(HomeForm hf) {
		return hf.getBook() + " " + hf.getChapter() + ":" + hf.getVerse();
	}

	public HomeForm getNextVerse(HomeForm hf) {
		String book = hf.getBook();
		int chapter = hf.getChapter();
		int verse = hf.getVerse();

		char next;
		HomeForm resultForm = new HomeForm();

		if (verse == bibleInfo.getChapterVerses(book, chapter)) {
			if (chapter == bibleInfo.getBookChapters(book)) {
				next = 'b'; // Go to first chapter of book
			} else {
				next = 'c'; // Go to first verse of next chapter
			}
		} else {
			next = 'v'; // Go to next verse
		}

		switch (next) {
		case 'b':
			chapter = 1;
			verse = 1;
			break;
		case 'c':
			chapter++;
			verse = 1;
			break;
		case 'v':
			verse++;
			break;
		default:
			break;
		}

		resultForm.setBook(book);
		resultForm.setChapter(chapter);
		resultForm.setVerse(verse);
		resultForm.setMode(hf.getMode());

		return resultForm;

	}

	private String getVerseText(String book, int chapter, int verse) {
		ESVService esvService = new ESVService();
		String verseText = "";
		try {
			verseText += esvService.getPassage(book, chapter + ":" + verse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (verseText.matches("^ERROR.*")) {
			System.out.println("Method getVerseText says: Invalid reference.");
			return "INVALID VERSE REFERENCE";
		} else {
			System.out.println("Method getVerseText says: Valid reference.");
		}

		return cleanVerse(verseText);
	}

	private String getReferenceString(HomeForm hf) {
		String ref = hf.getBook() + " " + hf.getChapter() + ":" + hf.getVerse();

		return ref;
	}

	// The ESV API returns verses with verse numbers in square brackets and
	// "ESV" at the end.
	// This method removes those.
	public String cleanVerse(String verse) {
		return verse.replaceAll("\\[\\d+(:\\d+)?\\]", "").replaceAll("\\s*\\(ESV\\)", "").replaceAll("\\s+", " ")
				.trim();
	}

	public String[] arrayifySingleVerse(String verse) {
		String result = cleanVerse(verse);
		String[] items = result.split(" ");
		return items;
	}

	// This method arrayifies a verse, putting n words in each string
	public String[] arrayifySingleVerse(String verse, int n) {

		System.out.println(verse);
		String result = cleanVerse(verse);
		String[] parts = result.split(" ");
		System.out.println("Length of string array: " + parts.length);
		for (int i = 0; i < parts.length; i = i + 1) {
			System.out.println("==" + parts[i] + "==");

		}

		ArrayList<String> listItems = new ArrayList<String>();
		for (int i = n - 1; i < parts.length; i = i + n) {

			String temp = "";
			for (int m = n - 1; m >= 0; m--) {
				temp += parts[i - m];
				if (m != 0) {
					temp += " ";
				}
			}

			listItems.add(temp);
		}
		int z = parts.length % n;
		if (z > 0) {
			String temp2 = "";
			for (int t = z; t > 0; t--) {
				temp2 += parts[parts.length - t];
				if (t > 1) {
					temp2 += " ";
				}
			}

			listItems.add(temp2);
		}
		System.out.println(listItems.toString());
		util.trimStringsInArrayList(listItems);
		System.out.println(listItems.toString());
		util.removeBlanksFromArrayList(listItems);
		System.out.println(listItems.toString());
		for (int i = 0; i < listItems.size(); i++) {
			System.out.println("---" + listItems.get(i) + "---");
		}
		String[] items = listItems.toArray(new String[listItems.size()]);
		return items;

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