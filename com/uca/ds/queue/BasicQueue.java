package com.uca.ds.queue;

public class BasicQueue implements QueueIntf {
	private int[] a;
	private int tail = -1;
	private int head = -1;
	private int MAX_SIZE;

	public BasicQueue(int size) {
		this.MAX_SIZE = size;
		a = new int[MAX_SIZE];
	}

	public BasicQueue() {
		this(5);
	}

	@Override
	public void enqueue(int e) {
		if (isFull())
			throw new IllegalStateException("overflow");
		a[++tail] = e;
	}

	private boolean isFull() {
		return size() == MAX_SIZE;
	}

	@Override
	public int dequeue() {
		int temp = peek();
		head++;
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return head == -1 && tail == -1 ? 0 : tail - head;
	}

	@Override
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("underflow");
		return a[head + 1];
	}

}
