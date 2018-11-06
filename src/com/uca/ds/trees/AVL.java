package com.uca.ds.trees;

import java.util.Iterator;

public class AVL<K extends Comparable<K>, V> implements Tree<K, V> {

	private Node root = null;

	@Override
	public int height() {
		return height(root);
	}

	private int height(Node n) {
		if (n == null)
			return 0;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	private int balance(Node n) {
		if (n == null)
			return 0;
		return n.balance;
	}

	@Override
	public V get(K key) {
		Node n = get(root, key);
		return n == null ? null : n.val;
	}

	private Node get(Node n, K key) {
		if (n == null)
			return null;
		int cmp = key.compareTo(n.key);
		if (cmp == 0)
			return n;
		return cmp > 0 ? get(n.right, key) : get(n.left, key);
	}

	@Override
	public void add(K key, V val) {
		//System.out.println("inserting " + key);
		root = add(root, key, val);
		//System.out.println("\troot is " + root.key);
	}

	private Node add(Node n, K key, V val) {
		if (n == null)
			return new Node(key, val);
		int cmp = key.compareTo(n.key);
		if (cmp == 0)
			n.val = val;
		else if (cmp > 0)
			n.right = add(n.right, key, val);
		else
			n.left = add(n.left, key, val);
		n.balance = 1 + Math.max(balance(n.left), balance(n.right));
		int diff = balance(n.left) - balance(n.right);
		if (diff > 1) {
			if (key.compareTo(n.left.key) > 0) {
				n.left = leftRotate(n.left);
			}
			n = rightRotate(n);
		}
		if (diff < -1) {
			if (key.compareTo(n.right.key) < 0) {
				n.right = rightRotate(n.right);
			}
			n = leftRotate(n);
		}
		return n;

	}

	private Node leftRotate(Node n) {
		//System.out.println("\tleft rotating on " + n.key);
		Node t = n.right;
		n.right = t.left;
		t.left = n;
		n.balance = 1 + Math.max(balance(n.left), balance(n.right));
		t.balance = 1 + Math.max(balance(t.left), balance(t.right));
		return t;
	}

	private Node rightRotate(Node n) {
		//System.out.println("\tright rotating on " + n.key);
		Node t = n.left;
		n.left = t.right;
		t.right = n;
		n.balance = 1 + Math.max(balance(n.left), balance(n.right));
		t.balance = 1 + Math.max(balance(t.left), balance(t.right));
		return t;
	}

	private class Node {
		private K key;
		private V val;
		private Node left = null;
		private Node right = null;
		private int balance;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			this.balance = 1;
		}
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
