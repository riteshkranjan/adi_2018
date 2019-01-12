package com.uca.zip;

import java.io.Serializable;

public class HuffmanCode implements Serializable {
	private static final long serialVersionUID = -2385370360750101535L;
	char data;
	int count;
	HuffmanCode left;
	HuffmanCode right;
}
