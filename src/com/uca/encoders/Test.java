package com.uca.encoders;

import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.Stack;

public class Test {
	public static void main(String[] args) throws IOException {
		testEncoder("abcdef");
		testEncoder("abcd");
		testEncoder("abcde");
		testEncoder("abc");

		testDecoder("YWJjZGVm", "abcdef");
		testDecoder("YWJjZA==", "abcd");
		testDecoder("YWJjZGU=", "abcde");
		testDecoder("YWJj", "abc");

		
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			StringBuilder sb = new StringBuilder();
			int length = r.nextInt(30);
			for (int j = 0; j < length; j++)
				sb.append((char) r.nextInt(128));
			String input = sb.toString();
			System.out.println(input);
			String output = testEncoder(input);
			if (output == null)
				System.out.println(" Failed");
			else
				testDecoder(output, input);
		}
	}

	private static String testEncoder(String input) {
		String expected = Base64.getEncoder().encodeToString(input.getBytes());
		String actual = new Base64Encoder().getEncoder().encode(input);
		if (expected.equals(actual))
			return actual;
		return null;
	}

	private static void testDecoder(String input, String expected) {
		String actual = new Base64Encoder().getDecoder().decode(input);
		if (expected.toString().equals(actual))
			System.out.println(" Passed");
		else {
			System.out.print(" Expected = " + expected + " while actual = " + actual);
			System.out.println(" Failed");
		}
	}

	public static void printBits(long n) {
		Stack<Long> s = new Stack<>();

		while (n != 0 && n != -1) {
			s.push(n & 1);
			n = n >> 1;
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop());
			if (s.size() % 8 == 0)
				System.out.print(",");
		}
		System.out.println();
	}

}
