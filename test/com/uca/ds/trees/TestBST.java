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
		bst.add(5, "five");
		bst.add(7, "seven");
		bst.add(6, "six");
		bst.add(2, "two");
		bst.add(3, "three");
		bst.add(1, "one");
		
		System.out.println("printing top view");
		List<Integer> result = bst.topView();
		System.out.println(result.toString());
		System.out.println("done with top view");
		
		assert bst.height() == 6;
		assert bst.get(1).equals("one");
		assert bst.get(8) == null;

		assert bst.getRank(1) == 0;
		assert bst.getRank(2) == 1;

		System.out.println("all test cases passed");
		testRun("BST");
		testRun("AVL");
		testRun("RBT");

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
		for (int i = 5; i > 0; i--) {
			random.add(r.nextInt(5), "somevalue");
			//sorted.add(i, "somevalue");
		}
		System.out.println(name + " height with random input = " + random.height());
		System.out.println(name + " height with sorted input = " + sorted.height());
	}
}
