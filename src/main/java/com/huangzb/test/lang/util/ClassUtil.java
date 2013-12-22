package com.huangzb.test.lang.util;

public class ClassUtil {
	public static String getClassPathWithoutPrefix(Class<?> clz){
		String pathWithPrefix = clz.getResource("/").toString();
		return pathWithPrefix.substring(pathWithPrefix.indexOf(":") + 2);
	}
}
