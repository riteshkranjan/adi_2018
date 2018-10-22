package com.uca.ds.list;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
	private Node head;
	private int size = 0;

	public int size() {
		return size;
	}

	public boolean search(T data) {
		return search(data, head);
	}

	private boolean search(T data, Node n) {
		if (n == null)
			return false;
		if (n.data.equals(data))
			return true;
		return search(data, n.next);
	}

	public void add(T data) {
		head = add(data, head);
		size++;
	}

	private Node add(T data, Node n) {
		if (n == null)
			return new Node(data);
		n.next = add(data, n.next);
		return n;
	}

	public void removeLast() {
		if (size == 0)
			throw new IllegalStateException("Empty List");
		head = removeLast(head);
		size--;
	}

	private Node removeLast(Node n) {
		if (n.next == null)
			return null;
		n.next = removeLast(n.next);
		return n;
	}

	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node temp = head;

			@Override
			public boolean hasNext() {
				return temp != null;
			}

			@Override
			public T next() {
				T t = temp.data;
				temp = temp.next;
				return t;
			}
		};
	}
}
