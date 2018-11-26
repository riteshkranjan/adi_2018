package com.uca.util;

public interface List<E> extends Collection<E> {
	public void add(int index, E e);

	public void remove(int index);

	public E get(int index);
}
