package com.uca.encoders;

import java.util.Arrays;

public class Base64Encoder {
	private char[] base64Lookup;

	public Base64Encoder getEncoder() {
		base64Lookup = new char[64];
		char c = 'A';
		int i = 0;
		for (i = 0; i < 26;)
			base64Lookup[i++] = c++;
		c = 'a';
		for (; i < 52;)
			base64Lookup[i++] = c++;
		c = '0';
		for (; i < 62;)
			base64Lookup[i++] = c++;
		base64Lookup[i++] = '+';
		base64Lookup[i] = '/';
		return this;
	}

	public Base64Encoder getDecoder() {
		base64Lookup = new char[123];
		Arrays.fill(base64Lookup, 0, 122, (char) -1);
		char j = 0;
		for (int i = 65; i < 91; i++)
			base64Lookup[i] = j++;
		for (int i = 97; i < 123; i++)
			base64Lookup[i] = j++;
		for (int i = 48; i < 58; i++)
			base64Lookup[i] = j++;
		return this;
	}

	public String encode(String input) {
		int i = 0;
		int n = 0;
		StringBuilder sb = new StringBuilder();
		while (i < input.length()) {
			n = n << 8;
			n = n | (input.charAt(i++) & 255);
			if (i % 3 == 0)
				encode(n, sb, 3);
		}
		encode(n, sb, input.length() % 3);
		while (sb.length() % 4 != 0)
			sb.append("=");
		return sb.toString();
	}

	public String decode(String input) {
		int i = 0;
		int n = 0;
		StringBuilder sb = new StringBuilder();
		while (i < input.length() && input.charAt(i) != '=') {
			n = n << 6;
			n = n | base64Lookup[input.charAt(i++)];
			if (i % 4 == 0)
				decode(n, sb, 4);
		}
		decode(n, sb, i % 4);
		return sb.toString();
	}

	private void encode(int n, StringBuilder sb, int max) {
		int pad = 63;
		max *= 8;
		int shift = max % 6;
		if (shift != 0) {
			n = n << (6 - shift);
			max += 6 - shift;
		}
		while (max > 0) {
			max -= 6;
			int pos = (n & (pad << max)) >> max;
			sb.append(base64Lookup[pos]);
		}
	}

	private void decode(int n, StringBuilder sb, int max) {
		max = 6 * max - 8;
		int pad = 255;
		while (max >= 0) {
			char c = (char) ((n & (pad << max)) >> max);
			sb.append(c);
			max -= 8;
		}
	}
}
