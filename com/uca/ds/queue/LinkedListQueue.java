package com.uca.ds.queue;

public class LinkedListQueue implements QueueIntf {

	private class Node {
		private int data;
		private Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}

	}

	private Node head;
	private Node tail;
	private int size = 0;

	@Override
	public void enqueue(int e) {
		if (tail == null) {
			tail = new Node(e);
			head = tail;
		} else {
			Node temp = tail;
			tail = new Node(e);
			temp.next = tail;
		}
		size++;
	}

	@Override
	public int dequeue() {
		int temp = peek();
		head = head.next;
		size--;
		if (size == 0)
			tail = null;
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return tail == null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("underflow");
		return head.data;
	}
}
