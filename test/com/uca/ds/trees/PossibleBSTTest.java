package com.uca.ds.trees;

public class PossibleBSTTest {

	public static void main(String[] args) {
		int n = 8;
		int[] res = new int[n + 1];
		assert PossibleBST.solveUsingRecursion(n, res) == 1430;
		assert PossibleBST.solveUsingDP(n) == 1430;
	}

}
