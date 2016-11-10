package com.joshwithee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.joshwithee.service.VerseParser;
import com.joshwithee.util.VerseScramblerBootUtil;

public class VerseParserTest {

	public VerseParser vp = new VerseParser();

	public VerseScramblerBootUtil util = new VerseScramblerBootUtil();

	@Before
	public void setup() {

	}

	@Test
	public void cleanVerseTest() {
		String testThisString = "\t   ___ddddd[234:123423423]s\nbbbbbb\n\t\n   sss \t\n(ESV)end\n";
		String checkThisString = vp.cleanVerse(testThisString);
		assertEquals("___ddddds bbbbbb sssend", checkThisString);
	}

	@Test
	public void removeBlanksFromArrayListTest() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("   \t\n  ");
		a.add("");
		a.add("   .   ");
		a.add(" ");
		util.removeBlanksFromArrayList(a);
		assertEquals(1, a.size());
	}

	@Test
	public void trimStringsInArrayListTest() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("   \t\n  ");
		a.add("");
		a.add("   .   ");
		a.add(" ");
		util.trimStringsInArrayList(a);
		String r = "";
		for (String s : a) {
			r += s;
		}
		assertEquals(".", r);
	}

}
