package com.uca.ds.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

	public class SortedKey implements Comparable<SortedKey> {
		private K k;
		private int cordinate;

		public SortedKey(K k, int cordinate) {
			this.k = k;
			this.cordinate = cordinate;
		}

		@Override
		public int compareTo(SortedKey o) {
			if (this.cordinate == o.cordinate)
				return 0;
			if (this.cordinate > o.cordinate)
				return +1;
			return -1;
		}
	}

	public List<K> topView() {
		List<K> result = new ArrayList<>();
		List<SortedKey> view = topView(root);
		Collections.sort(view);
		for (SortedKey key : view) {
			result.add(key.k);
		}
		return result;
	}

	public List<SortedKey> topView(Node n) {
		List<SortedKey> result = new ArrayList<>();
		Queue<NodeWithCordinate> q = new LinkedList<>();
		Set<Integer> visitedCordinate = new HashSet<>();
		q.add(new NodeWithCordinate(n, 0));
		while (!q.isEmpty()) {
			NodeWithCordinate temp = q.poll();
			if (!visitedCordinate.contains(temp.cordinate)) {
				// System.out.println(temp.node.key);
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

	public List<K> inOrder() {
		List<K> inorder = new ArrayList<>();
		inOrder(inorder, root);
		return inorder;
	}

	private void inOrder(List<K> list, Node n) {
		if (n == null)
			return;
		inOrder(list, n.left);
		list.add(n.key);
		inOrder(list, n.right);
	}

	public List<K> preOrder() {
		List<K> preOrder = new ArrayList<>();
		preOrder(preOrder, root);
		return preOrder;
	}

	private void preOrder(List<K> list, Node n) {
		if (n == null)
			return;
		list.add(n.key);
		inOrder(list, n.left);
		inOrder(list, n.right);
	}

	public List<K> postOrder() {
		List<K> postOrder = new ArrayList<>();
		postOrder(postOrder, root);
		return postOrder;
	}

	private void postOrder(List<K> list, Node n) {
		if (n == null)
			return;
		inOrder(list, n.left);
		inOrder(list, n.right);
		list.add(n.key);
	}

	public List<K> levelOrder() {
		List<K> levelOrder = new ArrayList<>();
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			levelOrder.add(temp.key);
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
		}
		return levelOrder;
	}

	public List<K> zigzagOrder() {
		List<K> levelOrder = new ArrayList<>();
		Stack<Node> q = new Stack<>();
		q.add(root);
		boolean reverse = false;
		while (!q.isEmpty()) {
			Stack<Node> t = new Stack<>();
			while (!q.isEmpty()) {
				Node temp = q.pop();
				levelOrder.add(temp.key);
				if (reverse) {
					if (temp.left != null)
						t.push(temp.left);
					if (temp.right != null)
						t.push(temp.right);
				} else {
					if (temp.right != null)
						t.push(temp.right);
					if (temp.left != null)
						t.push(temp.left);
				}
			}
			reverse = !reverse;
			q = t;
		}
		return levelOrder;
	}

	public int diffOfOddEven() {
		if (root == null)
			return 0;
		if (!(root.key instanceof Integer))
			throw new RuntimeException("this function is for integer keys");
		int sum = 0;
		Stack<Node> q = new Stack<>();
		q.add(root);
		boolean reverse = false;
		while (!q.isEmpty()) {
			Stack<Node> t = new Stack<>();
			while (!q.isEmpty()) {
				Node temp = q.pop();
				if (reverse) {
					sum += (int) temp.key;
					if (temp.left != null)
						t.push(temp.left);
					if (temp.right != null)
						t.push(temp.right);
				} else {
					sum += (int) temp.key * -1;
					if (temp.right != null)
						t.push(temp.right);
					if (temp.left != null)
						t.push(temp.left);
				}
			}
			reverse = !reverse;
			q = t;
		}
		return sum;
	}

}
