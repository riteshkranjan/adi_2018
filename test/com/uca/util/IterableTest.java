package com.uca.util;

import java.util.Iterator;

import java.lang.Iterable;

import com.uca.ds.list.MyLinkedList;

public class IterableTest {

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
		assert s.size() == 4;
		// System.out.println(String.format("current size is %d, '4' is %s", s.size(),
		// s.search(5)?"found":"not found"));
		s.removeLast();
		// System.out.println(String.format("current size is %d, '4' is %s", s.size(),
		// s.search(5)?"found":"not found"));
		print(s);
		assert s.size() == 3;

		assert s.search(new Integer(2)) == true;
		assert s.search(new Integer(5)) == false;
		s.removeLast();
		s.removeLast();
		s.removeLast();
		boolean isException = false;
		try {
			s.removeLast();
		} catch (IllegalStateException e) {
			isException = true;
		}
		assert isException == true;
		
	}

	private static void testWithString() {
		MyLinkedList<String> s = new MyLinkedList<>();
		s.add("12");
		s.add("as");
		s.add("asdf");
		s.add("er");
		print(s);
		// System.out.println(String.format("current size is %d, 'er' is %s", s.size(),
		// s.search("er")?"found":"not found"));
		s.removeLast();
		// System.out.println(String.format("current size is %d, 'er' is %s", s.size(),
		// s.search("er")?"found":"not found"));
		print(s);
	}

	@SuppressWarnings("rawtypes")
	private static void print(Iterable i) {
		Iterator ite = i.iterator();
		while (ite.hasNext()) {
			ite.next();
			// System.out.println(ite.next().toString());
		}
	}

}
