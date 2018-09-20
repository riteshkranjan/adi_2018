package com.uca.ds.queue;

public class CircularQueue implements QueueIntf {

	private int[] a;
	private int tail = -1;
	private int head = -1;
	private int MAX_SIZE;

	public CircularQueue(int size) {
		this.MAX_SIZE = size;
		a = new int[MAX_SIZE];
	}

	public CircularQueue() {
		this(5);
	}

	@Override
	public void enqueue(int e) {
		if (isFull())
			throw new RuntimeException("overflow");
		if (tail == -1) {
			head = 0;
		}
		tail = (tail + 1) % MAX_SIZE;
		a[tail] = e;
	}

	private boolean isFull() {
		return size() == MAX_SIZE;
	}

	@Override
	public int dequeue() {
		int temp = peek();
		head++;
		if (isEmpty()) {
			head = -1;
			tail = -1;
		}
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return head == -1 ? 0 : MAX_SIZE * (tail / 5) + (tail + 1) % MAX_SIZE - head;
	}

	@Override
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("underflow");
		return a[head];
	}
}
