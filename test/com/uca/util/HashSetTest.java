package com.uca.util;

public class HashSetTest {
	public static void main(String[] args) {
		Set<Integer> list = new HashSet<>();
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
		assert list.contains(1) == true;
		assert list.contains(2) == true;
		List<Integer> data = new LinkedList<>();
		data.add(1);
		data.add(2);
		data.add(3);
		validateElements(list, data);

		assert list.contains(1) == true;
		list.remove(1);
		assert list.contains(1) == false;
		data.remove(1);
		validateElements(list, data);

		assert list.contains(3) == true;
		list.remove(3);
		assert list.contains(3) == false;
		data.remove(3);
		validateElements(list, data);

		assert list.contains(100) == false;
		list.remove(100);
		assert list.contains(100) == false;
		validateElements(list, data);

		System.out.println("All test cases passed");
	}

	private static void validateElements(Set<Integer> l, List<Integer> data) {
		Iterator<Integer> ite = l.iterator();
		assert l.size() == data.size();
		assert ite.hasNext() == true;
		while (ite.hasNext()) {
			int s = ite.next();
			System.out.println(s);
			assert data.contains(s);
		}
		assert ite.hasNext() == false;
	}
}
