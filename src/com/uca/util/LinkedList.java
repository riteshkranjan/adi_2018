package com.uca.util;

public class LinkedList<E> implements List<E>,Queue<E> {
	private Node head = null;
	private int size = 0;

	@Override
	public void add(E e) {
		head = add(e, head);
		size++;
	}

	private Node add(E e, Node n) {
		if (n == null)
			return new Node(e);
		n.next = add(e, n.next);
		return n;
	}

	@Override
	public void remove(E e) {
		head = remove(e, head);
		size--;
	}

	private Node remove(E e, Node n) {
		if (n == null)
			return null;// if element not found
		if (n.data.equals(e))
			return n.next;
		n.next = remove(e, n.next);
		return n;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node t = head;

			@Override
			public boolean hasNext() {
				return t != null;
			}

			@Override
			public E next() {
				E e = t.data;
				t = t.next;
				return e;
			}
		};
	}

	@Override
	public void add(E e, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	private class Node {
		private E data;
		private Node next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	@Override
	public boolean contains(E e) {
		Node t = head;
		while (t != null) {
			if (t.data.equals(e))
				return true;
			t = t.next;
		}
		return false;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

}
