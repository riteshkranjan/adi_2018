package com.uca.zip;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {
	private HuffmanCode root;
	private Map<Character, String> encode = new HashMap<>();
	private int maxSizeEncode;
	private final char MAX_CHAR = '+';

	public HuffmanCode constructHuffmanCode(char data, int count) {
		HuffmanCode c = new HuffmanCode();
		c.count = count;
		c.data = data;
		return c;
	}

	public void build(Map<Character, Integer> frequency) {
		Comparator<HuffmanCode> c = new Comparator<HuffmanCode>() {

			@Override
			public int compare(HuffmanCode o1, HuffmanCode o2) {
				return o1.count - o2.count;
			}
		};
		Queue<HuffmanCode> q = new PriorityQueue<>(c);
		for (Entry<Character, Integer> entry : frequency.entrySet()) {
			q.add(constructHuffmanCode(entry.getKey(), entry.getValue()));
		}
		while (q.size() > 1) {
			HuffmanCode h1 = q.poll();
			HuffmanCode h2 = q.poll();
			HuffmanCode h = constructHuffmanCode(MAX_CHAR, h1.count + h2.count);
			h.l = h1;
			h.r = h2;
			q.add(h);
			root = h;
		}
		preOrder(root, "");
	}

	public String getCode(char c) {
		return encode.get(c);
	}

	public char getChar(long pattern, int index) {
		return search(root, pattern, index);

	}

	private char search(HuffmanCode h, long pattern, int index) {
		if (h.data != MAX_CHAR)
			return h.data;
		long temp = pattern >> (index - 1);
		if ((temp & 1) == 0)
			return search(h.l, pattern, index - 1);
		return search(h.r, pattern, index - 1);
	}

	public int getMaxSizeEncode() {
		return maxSizeEncode;
	}

	private void preOrder(HuffmanCode h, String val) {
		if (h == null)
			return;
		if (h.data != MAX_CHAR) {
			encode.put(h.data, val);
			if (val.length() > maxSizeEncode)
				maxSizeEncode = val.length();
		}
		preOrder(h.l, val + "0");
		preOrder(h.r, val + "1");
	}

	public HuffmanCode getRoot() {
		return root;
	}

	public void setRoot(HuffmanCode c) {
		root = c;
		preOrder(root, "");
	}
}
