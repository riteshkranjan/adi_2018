package com.uca.test.suite;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
	static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		File f = new File("./test");
		function(f, "");
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
					String[] params = { className };
					try {
						meth.invoke(null, (Object) params);
						System.out.println(" All test cases passed ");
					} catch (InvocationTargetException e) {
						System.err.println("Failed ");
					}
					
				}
			}

		}

	}

}
