package com.uca.util;

public class HashSetTest {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		assert set.size() == 0;
		assert set.isEmpty() == true;
		set.add(1);
		assert set.size() == 1;
		assert set.isEmpty() == false;
		set.clear();
		assert set.size() == 0;
		set.add(1);
		set.add(2);
		set.add(3);
		assert set.size() == 3;
		assert set.contains(1) == true;
		assert set.contains(2) == true;
		List<Integer> data = new LinkedList<>();
		data.add(1);
		data.add(2);
		data.add(3);
		validateElements(set, data);

		assert set.contains(1) == true;
		set.remove(1);
		assert set.contains(1) == false;
		data.remove(new Integer(1));
		validateElements(set, data);

		assert set.contains(3) == true;
		set.remove(3);
		assert set.contains(3) == false;
		data.remove(new Integer(3));
		validateElements(set, data);

		assert set.contains(100) == false;
		set.remove(100);
		assert set.contains(100) == false;
		validateElements(set, data);

		System.out.println("All test cases passed");
	}

	private static void validateElements(Set<Integer> l, List<Integer> data) {
		Iterator<Integer> ite = l.iterator();
		assert l.size() == data.size();
		assert ite.hasNext() == true;
		int count  = 0;
		while (ite.hasNext()) {
			int s = ite.next();
			assert data.contains(s);
			count++;
		}
		assert count==data.size();
		assert ite.hasNext() == false;
	}
}
