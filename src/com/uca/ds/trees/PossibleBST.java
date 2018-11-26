package com.uca.ds.trees;

/*
 * question : Count the number of possible Binary Search Trees with n keys
 */
public class PossibleBST {
	
	public static int solveUsingRecursion(int n, int[] res) {
		if (n == 0 || n == 1)
			return 1;
		if (res[n] != 0)
			return res[n];
		int ans = 0;
		for (int k = 1; k <= n; k++) {
			ans += solveUsingRecursion(k - 1, res) * solveUsingRecursion(n - k, res);
		}
		res[n] = ans;
		return ans;
	}
	
	public static int solveUsingDP(int n) {
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
