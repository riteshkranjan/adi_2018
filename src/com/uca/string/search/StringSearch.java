package com.uca.string.search;

public abstract class StringSearch {
	protected abstract int search(String pattern);

	protected String text;

	public StringSearch(String text) {
		this.text = text;
	}
	
	public static void print(String txt, String p, int c) {
		System.out.println(txt);
		if (c < 0)
			c = txt.length();
		while (c-- > 0)
			System.out.print(" ");
		System.out.println(p);
	}

}
