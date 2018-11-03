package com.uca.ds.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BST<K extends Comparable<K>, V> implements Tree<K, V> {

	private Node root = null;

	private class NodeWithCordinate {
		private Node node;
		private int cordinate;

		public NodeWithCordinate(Node node, int c) {
			this.node = node;
			this.cordinate = c;
		}
	}
	
	public class SortedKey implements Comparable<SortedKey>{
		private K k;
		private int cordinate;
		public SortedKey(K k, int cordinate) {
			this.k = k;
			this.cordinate = cordinate;
		}
		@Override
		public int compareTo(SortedKey o) {
			if(this.cordinate == o.cordinate) return 0;
			if(this.cordinate>o.cordinate) return +1;
			return -1;
		}
	}
	public List<K> topView() {
		List<K> result = new ArrayList<>();
		List<SortedKey> view = topView(root);
		Collections.sort(view);
		for(SortedKey key : view) {
			result.add(key.k);
		}
		return result;
	}

	public List<SortedKey> topView(Node n) {
		List<SortedKey> result= new ArrayList<>();
		Queue<NodeWithCordinate> q = new LinkedList<>();
		Set<Integer> visitedCordinate = new HashSet<>();
		q.add(new NodeWithCordinate(n, 0));
		while (!q.isEmpty()) {
			NodeWithCordinate temp = q.poll();
			if (!visitedCordinate.contains(temp.cordinate)) {
				//System.out.println(temp.node.key);
				result.add(new SortedKey(temp.node.key, temp.cordinate));
				visitedCordinate.add(temp.cordinate);
			}
			if (temp.node.left != null) {
				q.add(new NodeWithCordinate(temp.node.left, temp.cordinate - 1));
			}
			if (temp.node.right != null) {
				q.add(new NodeWithCordinate(temp.node.right, temp.cordinate + 1));
			}
		}
		
		return result;

	}

	@Override
	public void add(K key, V val) {
		root = add(root, key, val);
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
		n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
		return n;
	}

	private int sizeOf(Node n) {
		return n == null ? 0 : n.N;
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
	public int height() {
		return height(root);
	}

	private int height(Node n) {
		if (n == null)
			return 0;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private class Node {
		private K key;
		private V val;
		private Node left = null;
		private Node right = null;
		private int N;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			this.N = 1;
		}

	}

	public int getRank(K key) {
		return getRank(root, key);
	}

	private int getRank(Node n, K key) {
		if (n == null)
			return -1;
		int cmp = key.compareTo(n.key);
		if (cmp == 0)
			return sizeOf(n.left);
		if (cmp < 0)
			return getRank(n.left, key);
		return 1 + sizeOf(n.left) + getRank(n.right, key);
	}

}
