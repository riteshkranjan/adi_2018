package com.uca.string.search;

public class Test {

	public static void main(String[] args) {
		testBoyerMoore();
		testBoyerKmp();
	}

	private static void testBoyerMoore() {
		String text = "abacadabrabracabrabadabrabrabracadababac";
		StringSearch k = new BoyerMoore(text);
		run(k, text, "ababac", "racabra", "xyz");

		text = "abacadabrabracabrabababrabrabracadababac";
		k = new BoyerMoore(text);
		run(k, text, "ababab");
	}
	private static void testBoyerKmp() {
		String text = "abacadabrabracabrabadabrabrabracadababac";
		StringSearch k = new KMP(text);
		run(k, text, "ababac", "racabra", "xyz");
		
		text = "abacadabrabracabrabababrabrabracadababac";
		k = new KMP(text);
		run(k, text, "ababab");
	}

	private static void run(StringSearch k, String text, String... pattern) {
		for (String p : pattern) {
			assert k.search(p) == text.indexOf(p);
		}
	}

}
