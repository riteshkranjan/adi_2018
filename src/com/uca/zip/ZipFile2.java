package com.uca.zip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class ZipFile2 extends AbstractZipFile{
	private int lastLen;
	private final int SIZE_KEY = -1;
	

	public ZipFile2(String inputFile) throws IOException {
		super(inputFile);
	}

	public ZipFile2(File inputFile) throws IOException {
		super(inputFile);
	}

	public void zip() throws IOException {
		File tempFile = createFileWithExtension(inputFile, ".temp");
		Map<Character, Integer> frequency = getFrequency();
		Huffman h = buildHuffman(frequency);
		this.lastLen = zipData(inputFile, tempFile, h);
		File outputFile = createFileWithExtension(inputFile, ".ser");
		updateZip(tempFile, outputFile, frequency);
		tempFile.delete();
		long inputLenght = inputFile.length();
		long outputLength = outputFile.length();
		System.out.println(String.format(
				"Successfully compressed file : input size = %d Bytes while output is of %d Bytes (reduced by %d%s))",
				inputLenght, outputLength, ((inputLenght - outputLength) * 100 / inputLenght), "%"));
		System.out.println("Actual reduction in size = " + ((inputLenght - outputLength) * 100 / inputLenght) + "%");

	}

	private void updateZip(File tempFile, File outputFile, Map<Character, Integer> i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		BufferedReader br = new BufferedReader(new FileReader(tempFile));
		Map<Integer, Integer> h = new HashMap<>();
		for (char c : i.keySet())
			h.put((int) c, i.get(c));
		try {
			h.put(SIZE_KEY, this.lastLen);
			bw.write(h.toString());

			bw.newLine();
			int c = 0;
			while ((c = br.read()) != -1) {
				bw.write(c);
			}
		} finally {
			bw.flush();
			bw.close();
			br.close();
		}

	}

	public void unzipData(File input, File outputFile) throws IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		long temp = 0;
		int len = 0;
		try {
			Huffman h = new Huffman();
			h.setRoot(buildHuffman(deserialize(br)).getRoot());
			int batchSize = h.getMaxSizeEncode();
			int ch = br.read();
			while (ch != -1) {
				temp = ((temp & INTEGER_PAD) << PADDING_CHAR_COUNT) | (ch & PADDING_CHAR);
				len += PADDING_CHAR_COUNT;
				ch = br.read();
				if (ch == -1) {
					int ignore = PADDING_CHAR_COUNT - this.lastLen;
					temp = temp >> ignore;
					len -= ignore;
				}
				len = unzipData(bw, temp, len, batchSize, h);

			}
			batchSize = 1;
			unzipData(bw, temp, len, batchSize, h);
		} finally {
			bw.flush();
			br.close();
			bw.close();
		}
	}

	@SuppressWarnings("unchecked")
	private Map<Character, Integer> deserialize(BufferedReader br) throws IOException {
		Gson g = new Gson();
		Map<String, Double> fromJson = g.fromJson(br.readLine(), Map.class);
		this.lastLen = fromJson.get(String.valueOf(SIZE_KEY)).intValue();
		fromJson.remove(String.valueOf(SIZE_KEY));
		Map<Character, Integer> h = new HashMap<>();
		for (String i : fromJson.keySet()) {
			h.put((char) Integer.parseInt(i), fromJson.get(i).intValue());
		}
		return h;
	}
}
