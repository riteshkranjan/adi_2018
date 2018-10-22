package com.uca.util;

public interface List<E> extends Collection<E> {
	public void add(E e, int index);
	public void remove(int index);
	public E get(int index);
}
