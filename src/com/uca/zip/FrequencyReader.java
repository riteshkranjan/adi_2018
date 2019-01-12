package com.uca.zip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrequencyReader {
	public Map<Character, Integer> readFile(File f) throws IOException {
		Map<Character, Integer> map = new HashMap<>();
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		int ch;
		try {
			while ((ch = br.read()) != -1) {
				char c = (char) ch;
				map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
			}
		} finally {
			fr.close();
			br.close();
		}
		return map;
	}

}
