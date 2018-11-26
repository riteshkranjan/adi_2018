package com.uca.util;

public class LinkedListTest {
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		assert list.size() == 0;
		assert list.isEmpty() == true;
		list.add(1);
		assert list.size() == 1;
		assert list.isEmpty() == false;
		list.clear();
		assert list.size() == 0;
		list.add(1);
		list.add(2);
		list.add(3);
		assert list.size() == 3;
		assert list.get(0) == 1;
		assert list.contains(1) == true;
		assert list.contains(2) == true;
		validateElements(list, 1, 2, 3);

		list.add(0, 4);
		validateElements(list, 4, 1, 2, 3);

		list.add(2, 5);
		validateElements(list, 4, 1, 5, 2, 3);

		assert list.contains(4) == true;
		list.remove(new Integer(4));
		assert list.contains(4) == false;
		validateElements(list, 1, 5, 2, 3);

		assert list.contains(3) == true;
		list.remove(new Integer(3));
		assert list.contains(4) == false;
		validateElements(list, 1, 5, 2);

		assert list.contains(100) == false;
		list.remove(100);
		assert list.contains(100) == false;
		validateElements(list, 1, 5, 2);

		boolean isException = false;
		try {
			list.add(100, 6);
		} catch (IllegalAccessError e) {
			isException = true;
		}

		isException = false;
		try {
			list.get(100);
		} catch (IllegalAccessError e) {
			isException = true;
		}
		assert isException == true;
	}

	private static void validateElements(List<Integer> l, int... data) {
		Iterator<Integer> ite = l.iterator();
		assert l.size() == data.length;
		assert ite.hasNext() == true;
		for (int i = 0; i < data.length; i++) {
			//assert l.get(i) == data[i];
			assert ite.next() == data[i];
		}
		assert ite.hasNext() == false;
		assert ite.next() == null;
	}
}
