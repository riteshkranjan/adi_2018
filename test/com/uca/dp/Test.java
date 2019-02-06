package com.uca.dp;

public class Test {

	public static void main(String[] args) {
		testBitConverse();
		testCandyJar();
		testFibonnacci();
		testPossibleBST();
		testValidParenthesis();
	}

	private static void testPossibleBST() {
		PossibleBST b = new PossibleBST();
		assert b.getPossibleBSTCount(3) == 5;

	}

	private static void testValidParenthesis() {
		ValidParenthesis b = new ValidParenthesis();
		assert b.getValidParenthesisCount(3) == 5;

	}

	private static void testFibonnacci() {
		Fibonnacci f = new Fibonnacci();
		assert f.getFib(3) == 3;

	}

	private static void testCandyJar() {
		CandyJar c = new CandyJar(4);
		assert c.solve() == 3;

	}

	private static void testBitConverse() {
		BitConverse b = new BitConverse();
		assert b.solve(11) == 13;
		assert b.solve(5) == 6;
	}

}
