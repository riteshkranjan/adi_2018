package com.uca.dp;

/*
 * Return no of possible valid parenthesis having n pairs of parenthesis
 * ex: 3  returns 5 
 *    ()()(), ()(()), (())(), (()()), ((()))
 */

public class ValidParenthesis {
	
	public int getValidParenthesisCount(int n) {
		int[] res = new int[n + 1];
		res[0] = 1;
		res[1] = 1;
		for (int i = 2; i <= n; i++) {
			int ans = 0;
			for (int k = 1; k <= i; k++) {
				ans += res[k - 1] * res[i - k];
			}
			res[i] = ans;
		}
		return res[n];
	}


}
