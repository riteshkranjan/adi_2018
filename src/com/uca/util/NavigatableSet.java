package com.uca.util;

public interface NavigatableSet<E extends Comparable<E>> extends SortedSet<E> {
	public E ceiling(E e);

	public Iterator<E> descendingIterator();

	public NavigatableSet<E> descendingSet();

	public E floor(E e);

	public SortedSet<E> headSet(E toElement);

	public NavigatableSet<E> headSet(E toElement, boolean inclusive);

	public E higher(E e);

	public E lower(E e);

	public E pollFirst();

	public E pollLast();
}
