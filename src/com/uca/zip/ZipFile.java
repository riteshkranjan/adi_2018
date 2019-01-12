package com.uca.zip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ZipFile {

	private final File inputFile;
	private final int PADDING_CHAR_COUNT = 7;
	private final int PADDING_CHAR = 127;
	private final int INTEGER_PAD = Integer.MAX_VALUE >> PADDING_CHAR_COUNT;
	private char lastLen;
	private long helpFileLength;

	public ZipFile(String inputFile) throws IOException {
		this.inputFile = new File(inputFile);
	}
	public ZipFile(File inputFile) throws IOException {
		this.inputFile = inputFile;
	}

	private Huffman buildHuffman() throws IOException {
		FrequencyReader fq = new FrequencyReader();
		Map<Character, Integer> frequency = fq.readFile(inputFile);
		Huffman h = new Huffman();
		h.build(frequency);
		return h;
	}

	private File createFileWithExtension(File f, String ext) {
		return new File(f.getParent() + File.separator + f.getName().substring(0, f.getName().indexOf(".")) + ext);
	}

	public void zip() throws IOException {
		File outputFile = createFileWithExtension(inputFile, ".ser");
		Huffman h = buildHuffman();
		writeData(inputFile, outputFile, h);
		serialize(h);
		long inputLenght = inputFile.length();
		long outputLength = outputFile.length();
		System.out.println(String.format(
				"Successfully compressed file : input size = %d Bytes while output is of %d Bytes (reduced by %d%s))",
				inputLenght, outputLength, ((inputLenght - outputLength) * 100 / inputLenght), "%"));
		System.out.println("help file size = " + helpFileLength);
		outputLength += helpFileLength;
		System.out.println("Actual reduction in size = " + ((inputLenght - outputLength) * 100 / inputLenght) + "%");

	}

	public void unzip() throws IOException, ClassNotFoundException {
		File o = createFileWithExtension(inputFile, ".unser");
		Huffman h = new Huffman();
		h.setRoot(deserialize());
		extractData(inputFile, o, h);
	}

	private void extractData(File input, File outputFile, Huffman h) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		long temp = 0;
		int len = 0;
		int batchSize = h.getMaxSizeEncode();

		int ch = br.read();
		try {
			while (ch != -1) {
				temp = ((temp & INTEGER_PAD) << PADDING_CHAR_COUNT) | (ch & PADDING_CHAR);
				len += PADDING_CHAR_COUNT;
				ch = br.read();
				if (ch == -1) {
					int ignore = PADDING_CHAR_COUNT - this.lastLen;
					temp = temp >> ignore;
					len -= ignore;
				}
				len = writeData(bw, temp, len, batchSize, h);

			}
			batchSize = 1;
			writeData(bw, temp, len, batchSize, h);
		} finally {
			bw.flush();
			br.close();
			bw.close();
		}
	}

	private int writeData(BufferedWriter bw, long temp, int len, int max, Huffman h) throws IOException {
		while (len >= max) {
			char out = h.getChar(temp, len);
			bw.write(out);
			len -= h.getCode(out).length();
		}
		return len;
	}

	private void writeData(File input, File output, Huffman h) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		BufferedWriter bw = new BufferedWriter(new FileWriter(output));
		StringBuilder sb = new StringBuilder();
		int lastLen = 7;
		int c;
		try {
			while ((c = br.read()) != -1) {
				if (c > 127)
					System.out.println("out or range char : " + c);
				sb.append(h.getCode((char) c));
				while (sb.length() >= PADDING_CHAR_COUNT) {
					bw.write(Integer.parseInt(sb.substring(0, PADDING_CHAR_COUNT), 2));
					sb = new StringBuilder(sb.substring(PADDING_CHAR_COUNT));
				}
			}
			if (sb.length() != 0) {
				lastLen = sb.length();
				int out = Integer.parseInt(sb.toString(), 2);
				out = out << (PADDING_CHAR_COUNT - lastLen);
				bw.write(out);
			}
		} finally {
			bw.flush();
			br.close();
			bw.close();
		}
		this.lastLen = (char) lastLen;
	}

	private void serialize(Huffman h) throws IOException {
		File f = createFileWithExtension(inputFile, ".help");
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(f));
		HuffmanData d = new HuffmanData();
		d.lastLen = lastLen;
		d.c = h.getRoot();
		oo.writeObject(d);
		this.helpFileLength = f.length();
		oo.close();
	}

	private HuffmanCode deserialize() throws IOException, ClassNotFoundException {
		ObjectInputStream oo = new ObjectInputStream(new FileInputStream(createFileWithExtension(inputFile, ".help")));
		HuffmanData h = (HuffmanData) oo.readObject();
		this.lastLen = h.lastLen;
		oo.close();
		return h.c;
	}
}
