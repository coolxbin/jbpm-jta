package com.huangzb.test.lang.util;

import java.util.HashMap;
import java.util.Map;

public class JbpmUtil {
	private static Map<String, Object> holder = new HashMap<String, Object>();
	
	public static Object get(String key){
		return holder.get(key);
	}
	
	public static void add(String key, Object obj){
		holder.put(key, obj);
	}
	
	public static String ENTITY_MANAGER_FACTORY = "entitymanagerfactory";
	public static String RUNTIME_MANAGER = "runtimemanager";
	public static String RUNTIME_ENVIRONMENT = "runtimeenvironment";
}
