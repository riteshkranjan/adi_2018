package com.uca.ds.trees;

import java.util.Iterator;

public class AVL<K extends Comparable<K>, V> implements Iterable<K> {

	private Node root = null;
	
	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		private int diff;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.diff = 0;
		}
	}
	
	public void add(K key, V value) {
		root = add(root, key,  value);
	}
	
	private Node add(Node n, K key, V value) {
		if(n == null)
			return new Node(key, value);
		int cmp = key.compareTo(n.key);
		if(cmp == 0) {
			n.value = value;
		}
		if(cmp < 0) {
			n.left = add(n.left, key, value);
		}
		if(cmp > 0) {
			n.right = add(n.right, key, value);
		}
		n.diff = height(n.left) - height(n.right);
		if(n.diff < -1) {
			if(n.right.diff > 0) {
				n.right = rightRotation(n.right);
			}
			n = leftRotation(n);
		}
		if(n.diff > 1) {
			if(n.left.diff < 0) {
				n.left = leftRotation(n.left);
			}
			n = rightRotation(n);
		}
		return n;
	}
	
	private int height(Node n) {
		if(n == null) {
			return 0;
		}
		return 1 + Math.max(height(n.left), height(n.right));
	}
	
	private Node leftRotation(Node n) {
		Node temp = n.right;
		n.right = temp.left;
		temp.left = n;
		return temp;
	}
	
	private Node rightRotation(Node n) {
		Node temp = n.left;
		n.left = temp.right;
		temp.right = n;
		return temp;
	}
	
	
	public int getHeight() {
		return height(root);
	}
	
	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}