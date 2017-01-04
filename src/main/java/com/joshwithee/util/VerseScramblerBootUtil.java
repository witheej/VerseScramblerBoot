package com.joshwithee.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class VerseScramblerBootUtil {

	public void removeBlanksFromArrayList(ArrayList<String> a) {
		Iterator<String> iter = a.iterator();
		while (iter.hasNext()) {
			if (iter.next().matches("\\s*")) {
				iter.remove();
			}
		}
	}

	public void trimStringsInArrayList(ArrayList<String> a) {
		for (int i = 0; i < a.size(); i++) {
			a.set(i, a.get(i).trim());
		}
	}

	// Scrambles an array of Strings
	public String[] scrambleItems(String[] items) {
		if (items.length > 1) {
			String[] result = new String[items.length];
			Random rnd = ThreadLocalRandom.current();
			String temp;
			int j;
			for (int i = 0; i < items.length; i++) {
				result[i] = items[i];
			}
			for (int i = 0; i < items.length; i++) {
				temp = result[i];
				j = rnd.nextInt(items.length - 1);
				result[i] = result[j];
				result[j] = temp;
			}
			return result;
		} else {
			return items;
		}
	}

	public String lowerCaseNoSpaces(String s) {
		return s.replaceAll("\\s+", "").toLowerCase();
	}

}
