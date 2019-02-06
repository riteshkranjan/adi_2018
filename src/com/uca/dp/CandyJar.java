package com.uca.dp;

/*
 * A jar has n number of candies (0>n> 10^^9). Find in how many ways this jar can be
 * emptied. Possible moves are take out 1 or 3 candies
 * ex : n = 4  return 3 ({1,3}, {3,1}, {1,1,1,1})
 * output can be more that 10^^9 so print output after taking % 1000000007
 */
public class CandyJar {
	
	private static final int DIV = 1000000007;
    private final int n;
    private int[] m;

    public CandyJar(int n) {
          this.n = n;
          this.m = new int[n];
          this.m[0] = 1;
          this.m[1] = 1;
          this.m[2] = 1;
    }

    public int solve() {
          for (int i = 3; i < n; i++) {
                m[i] = (m[i - 1] % DIV + m[i - 3] % DIV) % DIV;
          }
          return (m[n - 1] % DIV + m[n - 3] % DIV) % DIV;
    }


}
