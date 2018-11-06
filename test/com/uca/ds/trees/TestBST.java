package com.uca.ds.trees;

import java.util.List;
import java.util.Random;

public class TestBST {

	public static void main(String[] args) {

		AVL<Integer, String> avl = new AVL<>();
		avl.add(7, "seven");
		avl.add(6, "six");
		avl.add(5, "five");
		avl.add(3, "three");
		avl.add(2, "two");
		avl.add(1, "one");

		assert avl.height() == 3;
		assert avl.get(1).equals("one");
		assert avl.get(8) == null;

		LLRBT<Integer, String> llrbt = new LLRBT<>();
		llrbt.add(7, "seven");
		llrbt.add(6, "six");
		llrbt.add(5, "five");
		llrbt.add(3, "three");
		llrbt.add(2, "two");
		llrbt.add(1, "one");

		assert llrbt.height() == 4;
		assert llrbt.get(1).equals("one");
		assert llrbt.get(8) == null;

		BST<Integer, String> bst = new BST<>();

		bst.add(1, "one");
		bst.add(2, "two");
		bst.add(3, "three");
		bst.add(5, "five");
		bst.add(6, "six");
		bst.add(7, "seven");

		assert bst.height() == 6;
		assert bst.get(1).equals("one");
		assert bst.get(8) == null;

		assert bst.getRank(1) == 0;
		assert bst.getRank(2) == 1;

		testTopView();
		testTravelsals();
		//assert bst.diffOfOddEven() == -6 : "sum of diff test failed";
		
		BST<String, String> b2 = new BST<String, String>();
		b2.add("aa", "sdf");
		System.out.println(bst.diffOfOddEven());
		
		System.out.println("all test cases passed");
		testRun("BST");
		testRun("AVL");
		testRun("RBT");
	}

	private static void testTopView() {
		BST<Integer, String> bst = new BST<>();
		bst.add(5, "five");
		bst.add(7, "seven");
		bst.add(6, "six");
		bst.add(2, "two");
		bst.add(3, "three");
		bst.add(1, "one");
		compareList(bst.topView(), 1, 2, 5, 7);
	}

	private static void testTravelsals() {
		BST<Integer, String> bst = new BST<>();
		bst.add(5, "five");
		bst.add(7, "seven");
		bst.add(6, "six");
		bst.add(2, "two");
		bst.add(3, "three");
		bst.add(1, "one");
		compareList(bst.inOrder(), 1, 2, 3, 5, 6, 7);
		compareList(bst.preOrder(), 5, 1, 2, 3, 6, 7);
		compareList(bst.postOrder(), 1, 2, 3, 6, 7, 5);
		compareList(bst.levelOrder(), 5, 2, 7, 1, 3, 6);
		compareList(bst.zigzagOrder(), 5, 2, 7, 6, 3, 1);
		
	}

	private static void testRun(String name) {
		Tree<Integer, String> random;
		Tree<Integer, String> sorted;
		switch (name) {
		case "BST": {
			random = new BST<>();
			sorted = new BST<>();
			break;
		}
		case "AVL": {
			random = new AVL<>();
			sorted = new AVL<>();
			break;
		}
		default: {
			random = new LLRBT<>();
			sorted = new LLRBT<>();
		}
		}

		Random r = new Random();
		for (int i = 100; i > 0; i--) {
			random.add(r.nextInt(Integer.MAX_VALUE), "somevalue");
			sorted.add(i, "somevalue");
		}
		System.out.println(name + " height with random input = " + random.height());
		System.out.println(name + " height with sorted input = " + sorted.height());
	}

	private static void compareList(List<Integer> actual, int... expected) {
		assert actual.size() == expected.length : String.format("size did not match. expected = %d while actual was %d",
				expected.length, actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assert actual.get(i) == expected[i] : String.format(
					"index at %d did not match. expected = %d while actual = %d", i, expected[i], actual.get(i));
		}
	}
}
