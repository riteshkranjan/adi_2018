package com.uca.util;

public interface Queue<E> extends Collection<E>{
	public void enqueue(E e);
	public E dequeue();

}
