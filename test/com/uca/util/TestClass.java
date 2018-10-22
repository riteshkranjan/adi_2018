package com.uca.util;

public class TestClass {

	public static void main(String[] args) {
		
		List<Student> list = new LinkedList<>();
		assert list.size() == 0;
		assert list.isEmpty() == true;
		list.add(new Student(1, "first"));
		list.add(new Student(2, "2nd"));
		list.add(new Student(3, "3rd"));
		assert list.size() == 3;
		assert list.isEmpty() == false;
		
		Iterator<Student> ite = list.iterator();
		assert ite.hasNext()==true;
		Student t = ite.next();
		assert t.id ==1;
		assert t.name.equals("first");
		t = ite.next();
		assert t.id ==2;
		assert t.name.equals("2nd");
		t = ite.next();
		assert t.id ==3;
		assert t.name.equals("3rd");
		assert ite.hasNext()==false;
		
		
		Student i = new Student(2,"sfdsfasf");
		list.remove(i);
		ite = list.iterator();
		assert ite.hasNext()==true;
		assert ite.next().id == 1;
		assert ite.next().id == 3;
		assert ite.hasNext()==false;
		
		System.out.println("all test cases passed");
		
		
	}

	private static class Student {
		private int id;
		private String name;

		public Student(int id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public boolean equals(Object obj) {
			Student other = (Student) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}

}
