package com.uca.dp;
/*
 * Return no of possible valid BST having n number of node (0 < n < 10^^5)
 * ex: 3  returns 5 
 *   		 1			1		 2		  3       3
              \			 \		/ \      /       /
               3 		  2	   1   3    2       1
              / 		 /             /		 \
             2   		3             1 		  2
             
 */
public class PossibleBST {
	
	public int getPossibleBSTCount(int n) {
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
