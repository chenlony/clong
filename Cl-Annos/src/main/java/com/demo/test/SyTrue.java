package com.demo.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SyTrue {

	public static void main(String[] args) {
		try {
			StringBuffer buffer = new StringBuffer();
			// BufferedReader bf = new BufferedReader(new FileReader(
			// "D:\\testSY.txt"));
			BufferedReader bf = new BufferedReader(new FileReader("D:\\SY.txt"));
			String s = null;
			while ((s = bf.readLine()) != null) {// 使用readLine方法，一次读一行
				buffer.append(s.trim() + "\r\n");
			}
			String xml = buffer.toString();
			System.out.println(xml);
			String[] split = xml.split("\r\n");
			List<String> arrayList = new ArrayList<String>();
			
			for (int i = 0; i < split.length; i++) {
				Pattern compile = Pattern.compile("^(\\*[A-Z]{6}).*");
				Matcher matcher = compile.matcher(split[i]);
				while(matcher.find()){
					String[] split2 = matcher.group().trim().split(" +");
					String st = split2[0].substring(1)+","+split2[split2.length-1];
					st = st.replaceAll("/", ",");
					arrayList.add(st);
				}
			}
			
			
			System.out.println("11");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
