package com.uca.ds.trees;

public interface Tree<K extends Comparable<K>, V> extends Iterable<K> {
	
	public V get(K key);
	public int height();
	public void add(K key, V val);

}
