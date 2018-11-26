package com.demo.test;

import java.util.HashMap;
import java.util.Map;

public class LamdaTest {
	public static void main(String[] args) {
		Map<String,String> m = new HashMap<String,String>();
		m.put("resultCode","200");
		m.put("data", "wwwwwwwwwwwww");
		m.forEach((k,v) -> System.out.println(k+"="+v));
//		System.out.println("111111");
	}
}
