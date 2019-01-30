package com.uca.zip;

import java.io.File;
import java.io.IOException;

public class TestZipFile extends TestZip {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		TestZip t = new TestZipFile();
		t.test();
	}

	public AbstractZipFile getZipInstance(File f) throws IOException {
		return new ZipFile(f);
	}

}
