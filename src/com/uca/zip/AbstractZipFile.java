package com.uca.zip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractZipFile {
	protected final File inputFile;
	protected final int PADDING_CHAR_COUNT = 7;
	protected final int PADDING_CHAR = 127;
	protected final int INTEGER_PAD = Integer.MAX_VALUE >> PADDING_CHAR_COUNT;

	public AbstractZipFile(String inputFile) throws IOException {
		this.inputFile = new File(inputFile);
	}
	public AbstractZipFile(File inputFile) throws IOException {
		this.inputFile = inputFile;
	}

	protected abstract void zip() throws IOException;
	
	protected abstract void unzipData(File f, File o) throws IOException, ClassNotFoundException;

	protected void unzip() throws IOException, ClassNotFoundException {
		File o = createFileWithExtension(inputFile, ".unser");
		unzipData(inputFile, o);
	}
	
	protected int unzipData(BufferedWriter bw, long temp, int len, int max, Huffman h) throws IOException {
		while (len >= max) {
			char out = h.getChar(temp, len);
			bw.write(out);
			len -= h.getCode(out).length();
		}
		return len;
	}
	protected File createFileWithExtension(File f, String ext) {
		return new File(f.getParent() + File.separator + f.getName().substring(0, f.getName().indexOf(".")) + ext);
	}
	protected char zipData(File input, File output, Huffman h) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		BufferedWriter bw = new BufferedWriter(new FileWriter(output));
		StringBuilder sb = new StringBuilder();
		int lastLen = PADDING_CHAR_COUNT;
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
		return (char) lastLen;

	}
	protected Huffman buildHuffman(Map<Character, Integer> frequency) throws IOException {
		Huffman h = new Huffman();
		h.build(frequency);
		return h;
	}

	protected Map<Character, Integer> getFrequency() throws IOException {
		FrequencyReader fq = new FrequencyReader();
		Map<Character, Integer> frequency = fq.readFile(inputFile);
		return frequency;
	}

	

}
