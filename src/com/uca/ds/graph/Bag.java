package com.uca.ds.graph;

import java.util.Iterator;

public class Bag implements Iterable<Integer> {
	private Node first = null;
	private Node last = null;

	private class Node {
		private int data;
		private Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public void enqueue(int v) {
		Node temp = new Node(v);
		if (first == null) {
			first = temp;
			last = temp;
		} else {
			last.next = temp;
			last = temp;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private Node current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Integer next() {
				int temp = current.data;
				current = current.next;
				return temp;
			}
		};
	}

}
