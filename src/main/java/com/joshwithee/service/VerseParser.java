package com.joshwithee.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshwithee.util.VerseScramblerBootUtil;

@Service
public class VerseParser {

	@Autowired
	VerseScramblerBootUtil util;

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

}