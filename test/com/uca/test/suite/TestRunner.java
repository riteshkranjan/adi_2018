package com.uca.test.suite;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
	private static int testCount = 0;
	private static int successCount = 0;
	private static int failedCount = 0;
	static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		File f = new File("./test");
		function(f, "");
		System.out.println(String.format("Test run summary: Total: %d (passed:%d, failed:%d)",testCount,successCount,failedCount));
	}

	private static void function(File f, String suffix) throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException {

		File[] ff = f.listFiles();
		for (File f1 : ff) {
			if (f1.isDirectory()) {
				function(f1, suffix + "." + f1.getName());
			} else {
				if (f1.getName().endsWith("Test.java")) {
					String className = (suffix + "." + f1.getName()).substring(1).replace(".java", "");
					Class<?> c = classLoader.loadClass(className);
					System.out.print("Running " + className + " : ");
					Method meth = c.getMethod("main", String[].class);
					testCount++;
					String[] params = { className };
					try {
						meth.invoke(null, (Object) params);
						System.out.println(" All test cases passed ");
						successCount++;
					} catch (InvocationTargetException e) {
						System.err.println("Failed ");
						failedCount++;
					}
					
				}
			}

		}

	}

}
