package com.uca.string.search;

import java.util.Arrays;

public class BoyerMoore extends StringSearch {

	public BoyerMoore(String text) {
		super(text);

	}

	private int[] patIndex;

	@Override
	public int search(String pattern) {
		int m = pattern.length();
		preprocess(pattern, m);
		int i = m - 1, j = m - 1;
		for (; i < text.length();) {
			//print(text, pattern, i - j);
			int c = 0;
			while (text.charAt(i - c) == pattern.charAt(j - c)) {
				c++;
				if (c == m)
					return i - m + 1;
			}
			j = m - 1;
			if (patIndex[text.charAt(i - c)] == m) {
				c = patIndex[text.charAt(i - c)] - c;
			} else
				c = patIndex[text.charAt(i - c)];
			if (c == m)
				if (c < 0)
					throw new RuntimeException("txt should never go back");
			//System.out.println("shifting by " + c);
			i += c;
		}
		return -1;
	}

	private void preprocess(String pattern, int m) {
		patIndex = new int[256];
		char[] arr = pattern.toCharArray();
		Arrays.fill(patIndex, arr.length);
		for (int i = 0; i < m; i++) {
			patIndex[arr[i]] = m - i - 1;
		}
	}

}
