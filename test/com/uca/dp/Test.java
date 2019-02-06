package com.uca.dp;

public class Test {

	public static void main(String[] args) {
		testBitConverse();
		testCandyJar();
		testFibonacci();
		testPossibleBST();
		testValidParenthesis();
		testLCS();
	}

	private static void testLCS() {
		LCS l = new LCS("abcdefgh", "bdg");
		assert l.getCount()==3;
		assert l.getLCS().equals("bdg");
		
		l = new LCS("abcdefgh", "bzg");
		assert l.getCount()==2;
		assert l.getLCS().equals("bg");
		
	}

	private static void testPossibleBST() {
		PossibleBST b = new PossibleBST();
		assert b.getPossibleBSTCount(3) == 5;

	}

	private static void testValidParenthesis() {
		ValidParenthesis b = new ValidParenthesis();
		assert b.getValidParenthesisCount(3) == 5;

	}

	private static void testFibonacci() {
		Fibonacci f = new Fibonacci();
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
