package com.uca.java8;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaFunction {

	public static void main(String[] args) {
		conditionalSum();
		printList();
		mapAndFilter();
		xorAll();
		listToMapsortByValue();
		mapToMapSortByValue();
	}

	private static void mapToMapSortByValue() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 10);
		map.put(2, 7);
		map.put(3, 8);
		System.out.print("input: ");
		print(map);
		Map<Integer, Integer> m2 = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.print("output: ");
		print(m2);
	}

	private static void listToMapsortByValue() {
		List<Pair> list = new ArrayList<>();
		list.add(new Pair(1, 10));
		list.add(new Pair(2, 7));
		list.add(new Pair(2, 8));
		Map<Integer, Integer> m = list.stream().sorted((e1, e2) -> e1.getVal().compareTo(e2.getVal()))
				.collect(Collectors.toMap(Pair::getKey, Pair::getVal, (e1, e2) -> e2, LinkedHashMap::new));
		System.out.print("list is "+list + " output map is (sorte by value & keeping last occurance) : ");
		print(m);
	}

	private static void xorAll() {
		List<Integer> list = Arrays.asList(2, 4, 2, 3, 3, 1, 1);
		Integer u = list.stream().reduce((x, y) -> x ^ y).orElse(0);
		System.out.println("unique element in list "+list+" is "+u);
	}

	private static void mapAndFilter() {
		List<Integer> list = Arrays.asList(2, 4, 3, 1);
		List<Integer> list2 = list.stream().map(m -> m * 2).filter(m -> m > 4).collect(Collectors.toList());
		System.out.println("square of list "+list + " and element greater than 4 is " +list2);
	}

	private static void printList() {
		List<Integer> list = Arrays.asList(2, 4, 3, 1);
		System.out.print("printing using lambda ");
		list.stream().forEach(num -> System.out.print(num + " "));
		System.out.println();
		System.out.print("printing using lambda using ::");
		list.stream().forEach(System.out::print);
		System.out.println();
	}

	private static void conditionalSum() {
		int key = 2;
		List<Integer> list = Arrays.asList(2,4,3,1);
		Integer sum = list.stream().map(e->(e>key?key:e)).reduce((e1, e2)->e1+e2).orElse(0);
		System.out.println("sum of all elements chopped by "+key+" in list " + list.toString() + " is " +sum);
	}

	private static void print(Map<Integer, Integer> map) {
		Iterator<Integer> ite = map.keySet().iterator();
		System.out.print("[");
		while (ite.hasNext()) {
			int key = ite.next();
			System.out.print(key + ":" + map.get(key) + (ite.hasNext() ? " , " : ""));
		}
		System.out.println("]");
	}

	private static class Pair {
		Integer key;
		Integer val;

		public Pair(Integer key, Integer val) {
			this.key = key;
			this.val = val;
		}

		public Integer getKey() {
			return key;
		}

		public Integer getVal() {
			return val;
		}

		@Override
		public String toString() {
			return "{key=" + key + ", val=" + val +"}";
		}
		
	}
}
