package com.uca.zip;

import java.io.File;
import java.io.IOException;

public class TestZipFile2 extends TestZip {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		TestZip t = new TestZipFile2();
		t.test();
	}

	public AbstractZipFile getZipInstance(File f) throws IOException {
		return new ZipFile2(f);
	}

}
