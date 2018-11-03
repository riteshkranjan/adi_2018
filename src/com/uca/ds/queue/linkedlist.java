package com.uca.ds.queue;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class linkedlist<K> implements List<K> {

	private int size = 0;

	

	public static void main(String args[]) {
		List<Integer> obj = new linkedlist<>();

		obj.add(10);
		obj.add(30);
		obj.add(40);
		obj.add(20);
		Collections.sort(obj);
		Iterator<Integer> i = obj.iterator();

		while (i.hasNext()) {
			System.out.println(i.next());
		}

		for (Integer x : obj) {
			System.out.println(x);
		}
	}

	@Override
	public boolean add(K e) {
		Node nn = new Node(e);
		nn.val = e;
		nn.next = head;
		head = nn;
		size++;
		return true;
	}

	@Override
	public void add(int index, K element) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addAll(Collection<? extends K> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends K> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<K> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<K> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K set(int index, K element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<K> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		K[] arr = (K[]) new Object[this.size()];
		Iterator<K> i = this.iterator();
		int j = 0;
		while (i.hasNext()) {
			K key = i.next();
			System.out.println(key.toString());
			arr[j++] = key;
		}
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class Node {
		private K val;
		private Node next;

		public Node(K val) {
			this.val = val;
			this.next = null;
		}
	}

	private Node head = null;

	@Override
	public Iterator<K> iterator() {
		Iterator<K> i = new Iterator<K>() {
			public Node curr = head;

			public boolean hasNext() {
				return (curr != null);
			}

			public K next() {
				Node temp = curr;
				curr = curr.next;
				return temp.val;
			}

		};
		return i;

	}

}
