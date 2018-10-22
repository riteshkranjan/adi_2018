package com.uca.util;

public class HashSet<E> implements Set<E> {
	private LinkedList<E>[] bucket;
	private int bucketSize;
	private float loadFactor;
	private int size;

	@SuppressWarnings("unchecked")
	public HashSet(int bucketSize, float loadFactor) {
		this.bucketSize = bucketSize;
		this.loadFactor = loadFactor;
		this.bucket = new LinkedList[bucketSize];
	}

	public HashSet() {
		this(16, 0.75f);
	}

	@Override
	public void add(E e) {
		int bucketNumber = e.hashCode() % bucketSize;
		if (bucket[bucketNumber] == null) {
			bucket[bucketNumber] = new LinkedList<>();
		}
		if (!bucket[bucketNumber].contains(e)) {
			bucket[bucketNumber].add(e);
			size++;
			if (bucket[bucketNumber].size() >= bucketSize * loadFactor) {
				resize();
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void resize() {
		Iterator<E> ite = this.iterator();
		LinkedList<E>[] temp = new LinkedList[this.bucketSize * 2];
		int revisedBucketSize = 2 * bucketSize;
		while (ite.hasNext()) {
			E curr = ite.next();
			int bucketNumber = curr.hashCode() % revisedBucketSize;
			if (temp[bucketNumber] == null) {
				temp[bucketNumber] = new LinkedList<>();
			}
			temp[bucketNumber].add(curr);
		}
		this.bucket = temp;
		this.bucketSize = revisedBucketSize;
	}

	@Override
	public void remove(E e) {
		int bucketNumber = e.hashCode() % bucketSize;
		if (bucket[bucketNumber] != null) {
			int currentSize = bucket[bucketNumber].size();
			bucket[bucketNumber].remove(e);
			if (bucket[bucketNumber].size() != currentSize)
				size--;
			if (bucket[bucketNumber].size() == 0)
				bucket[bucketNumber] = null;

		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < bucketSize; i++) {
			if (bucket[i] != null)
				bucket[i].clear();
		}
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			private int i = 0;
			Iterator<E> ite = null;

			@Override
			public boolean hasNext() {
				if (ite != null && ite.hasNext())
					return true;
				while (i < bucketSize && bucket[i] == null) {
					i++;
				}
				if (i == bucketSize)
					return false;

				ite = bucket[i].iterator();
				i++;
				return hasNext();
			}

			@Override
			public E next() {
				if (hasNext())
					return ite.next();
				return null;
			}
		};
	}

	@Override
	public boolean contains(E e) {
		int bucketNumber = e.hashCode() % bucketSize;
		return bucket[bucketNumber] == null ? false : bucket[bucketNumber].contains(e);

	}

}
