package com.uca.ds.trees;

import java.util.Random;

public class TestBST {

	public static void main(String[] args) {

		BST<Integer, String> bst = new BST<>();
		bst.add(5, "five");
		bst.add(2, "two");
		bst.add(1, "one");
		bst.add(3, "three");
		bst.add(6, "six");
		bst.add(7, "seven");
		System.out.println(bst.height());
		System.out.println(bst.get(1));
		System.out.println(bst.get(8));

		System.out.println(bst.getRank(1));//0
		System.out.println(bst.getRank(2));//1

	//	for (int i = 0; i < 100; i++)
		//	testRun();

	}

	private static void testRun() {
		BST<Integer, String> bst = new BST<>();

		Random r = new Random();
		for (int i = 100; i > 0; i--) {
			bst.add(i, "somevalue");
		}

		/*
		 * bst.add(1, "one"); bst.add(2, "two"); bst.add(5, "five"); bst.add(6, "six");
		 * bst.add(7, "seven");
		 */
		// System.out.println(bst.get(1));
		// System.out.println(bst.get(8));

		System.out.println(bst.height());
	}

}
