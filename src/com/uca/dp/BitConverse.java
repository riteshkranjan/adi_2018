package com.uca.dp;

/*
 * 
Question : given number n, you have to convert to 0 in minimum no of steps.
Steps allowed are : 
1.	Flip right most bit 
2.	Bit at i can be flipped if i+1 bit is 1 and there is no 1’s afterwards (going left to right) 

Example : n = 6  returns 4 (110 -> 010 -> 011 -> 001 -> 000)  

 */

public class BitConverse {
	public static void main(String[] args) {
		int n = 11;
		BitConverse b = new BitConverse();
		System.out.println(b.solve(n));

	}

	public int solve(int n) {

		int m = n;
		int c = 0;
		while (m != 0) {
			c++;
			m = m >> 1;
		}
		int[] a = new int[(int) Math.pow(2, c)];
		a[0] = 0;
		a[1] = 1;
		for (int i = 2; i <= n; i++)
			solve(i, a, -1);
		return a[n];
	}

	public int solve(int n, int[] a, int parent) {
		if (n == 0)
			return 0;
		if (a[n] != 0)
			return a[n];
		int ans = Integer.MAX_VALUE;
		int x = n ^ 1;
		if (x != n && x != parent) {
			ans = solve(x, a, n);
		}

		int len = getFirstOnBit(n);
		int y = (int) Math.pow(2, len + 1);
		if (y < n) {
			y = n ^ y;
			if (y != x && y != n && y != parent) {
				ans = Math.min(ans, solve(y, a, n));
			}
		}
		a[n] = ans == Integer.MAX_VALUE ? 0 : ans + 1;
		return a[n];
	}

	public int getFirstOnBit(int n) {
		int count = 0;
		while (n != 0 && ((n & 1) == 0)) {
			n = n >> 1;
			count++;
		}
		return count;
	}
}
