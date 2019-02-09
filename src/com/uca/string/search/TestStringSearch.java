package com.uca.string.search;


public class TestStringSearch {

	public static void main(String[] args) {
		String txt = "caaccacb";
		String p = "cacb";
		
		test(txt, p, new BoyerMoore(txt));
		txt = "abacadabrabracabrabababacadabrabrabracadababac";
		p ="racabra";
		test(txt, p, new BoyerMoore(txt));
		p ="ababab";
		test(txt, p, new BoyerMoore(txt));
		test(txt, p, new KMP(txt));
		test(txt, p, new RabinKarp(txt));
	}

	private static void test(String txt, String p, StringSearch b) {
		System.out.println("Testing algorithm "+b.getClass().getSimpleName());
		int c = b.search(p);
		System.out.println("found at index " + c);
		StringSearch.print(txt, p, c);
	}


}
