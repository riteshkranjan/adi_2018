package com.uca.string.search;

public class KMP extends StringSearch {

	public KMP(String text) {
		super(text);
	}

	private int[][] dfa;

	@Override
	public int search(String pattern) {
		int m = pattern.length();
		preprocess(pattern, m);
		int i = 0, j = 0;
		for (; j < m && i < text.length(); i++) {
			j = dfa[text.charAt(i)][j];
		}
		if (j == m)
			return i - m;
		return -1;
	}

	private void preprocess(String pattern, int m) {
		int R = 256;
		dfa = new int[R][m];
		dfa[pattern.charAt(0)][0] = 1;
		for (int x = 0, j = 1; j < m; j++) {
			for (int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][x];
			dfa[pattern.charAt(j)][j] = j + 1;
			x = dfa[pattern.charAt(j)][x];
		}
	}
}
