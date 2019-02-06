package com.uca.dp;

import java.util.Stack;
/*
 * find longest common subsequence in 2 given strings 
 * ex "bel", "abcefgl" has LCS = "bel" 
 * ex "bxl", "abcefgl" has LCS = "bl" 
 * 
 */

public class LCS {

	private String txt1;
	private String txt2;
	private int[][] lcs;

	public LCS(String txt1, String txt2) {
		this.txt1 = txt1;
		this.txt2 = txt2;
		lcs = new int[txt1.length() + 1][txt2.length() + 1];
		for (int i = 1; i <= txt1.length(); i++)
			for (int j = 1; j <= txt2.length(); j++)
				lcs[i][j] = txt1.charAt(i - 1) == txt2.charAt(j - 1) ? 1 + lcs[i - 1][j - 1]
						: Math.max(lcs[i - 1][j], lcs[i][j - 1]);
	}

	public int getCount() {
		return lcs[txt1.length()][txt2.length()];
	}

	public String getLCS() {
		Stack<Character> s = new Stack<>();
		int i = txt1.length();
		int j = txt2.length();
		while (i > 0 && j > 0) {
			if (lcs[i][j] > Math.max(lcs[i - 1][j], lcs[i][j - 1])) {
				s.push(txt1.charAt(i - 1));
				i--;
				j--;

			} else if (lcs[i - 1][j] > lcs[i][j - 1]) {
				i--;
			} else
				j--;
		}
		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			sb.append(s.pop());
		}
		return sb.toString();
	}

}
