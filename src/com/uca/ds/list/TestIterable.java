package com.uca.ds.list;

import java.util.Iterator;

public class TestIterable {

	public static void main(String[] args) {
		testWithString();
		testWithInteger();
	}
	
	private static void testWithInteger() {
		MyLinkedList<Integer> s = new MyLinkedList<>();
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		print(s);
		System.out.println(String.format("current size is %d, '4' is %s", s.size(), s.search(5)?"found":"not found"));
		s.removeLast();
		System.out.println(String.format("current size is %d, '4' is %s", s.size(), s.search(5)?"found":"not found"));
		print(s);
	}

	private static void testWithString() {
		MyLinkedList<String> s = new MyLinkedList<>();
		s.add("12");
		s.add("as");
		s.add("asdf");
		s.add("er");
		print(s);
		System.out.println(String.format("current size is %d, 'er' is %s", s.size(), s.search("er")?"found":"not found"));
		s.removeLast();
		System.out.println(String.format("current size is %d, 'er' is %s", s.size(), s.search("er")?"found":"not found"));
		print(s);
	}

	@SuppressWarnings("rawtypes")
	private static void print(Iterable i) {
		Iterator ite = i.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next().toString());
		}
	}

}
