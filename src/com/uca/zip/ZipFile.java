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
import java.util.Map;

public class ZipFile extends AbstractZipFile{

	private char lastLen;
	private long helpFileLength;
	public ZipFile(File inputFile) throws IOException {
		super(inputFile);
	}
	
	public ZipFile(String inputFile) throws IOException {
		super(inputFile);
	}

	public void zip() throws IOException {
		File outputFile = createFileWithExtension(inputFile, ".ser");
		Map<Character, Integer> frequency = getFrequency();
		Huffman h = buildHuffman(frequency);
		this.lastLen = zipData(inputFile, outputFile, h);
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

	public void unzipData(File input, File outputFile) throws IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		long temp = 0;
		int len = 0;
		Huffman h = new Huffman();
		h.setRoot(deserialize());
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

	private void serialize(Huffman h) throws IOException {
		File f = createFileWithExtension(inputFile, ".help");
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(f));
		HuffmanData d = new HuffmanData();
		d.l = lastLen;
		d.c = h.getRoot();
		oo.writeObject(d);
		this.helpFileLength = f.length();
		oo.close();
	}

	private HuffmanCode deserialize() throws IOException, ClassNotFoundException {
		ObjectInputStream oo = new ObjectInputStream(new FileInputStream(createFileWithExtension(inputFile, ".help")));
		HuffmanData h = (HuffmanData) oo.readObject();
		this.lastLen = h.l;
		oo.close();
		return h.c;
	}
}
