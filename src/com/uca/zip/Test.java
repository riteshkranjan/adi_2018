package com.uca.zip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File folder = new File("./testData");

		for (File f : folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				String name = arg0.getName();
				return !(name.endsWith(".ser") || name.endsWith(".unser") || name.endsWith(".help"));
			}
		})) {
			System.out.println("************************************");
			System.out.println("Testing with file "+f.getName());
			ZipFile z1 = new ZipFile(f);
			z1.zip();

			ZipFile z2 = new ZipFile(createFileWithExtension(f, ".ser"));
			z2.unzip();

			File f2 = createFileWithExtension(f, ".unser");
			if (f2.length() != f.length()) {
				System.out.println("Test case failed for file size did not match" + f.getName());
			} else if (!compare(f, f2)) {
				System.out.println("compare result failed for file  " + f.getName());
			}else {
				System.out.println("Testcase Passed!!");
			}
			createFileWithExtension(f, ".ser").delete();
			createFileWithExtension(f, ".help").delete();
			createFileWithExtension(f, ".unser").delete();
			
		}
	}

	private static boolean compare(File f1, File f2) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		BufferedReader br2 = new BufferedReader(new FileReader(f2));
		String s1 = br1.readLine();
		String s2 = br2.readLine();
		try {
			while (true) {
				if (s1 == null && s2 == null)
					return true;
				if (s1 == null || s2 == null)
					return false;
				if (!s2.equals(s1)) {
					return false;
				}
				s1 = br1.readLine();
				s2 = br2.readLine();
			}
		} finally {
			br1.close();
			br2.close();
		}
	}

	private static File createFileWithExtension(File f, String ext) {
		return new File(f.getParent() + File.separator + f.getName().substring(0, f.getName().indexOf(".")) + ext);
	}

}
