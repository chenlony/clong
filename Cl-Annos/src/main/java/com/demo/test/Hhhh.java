package com.demo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Hhhh {
	public static void main(String[] args) {
		String LaddStr = "\u000E\u000FLADU:G5/B2928  /26JUN18/CGO2  \u000E       DATE/TIME: \u000E26SEP/23:09:36\u000E                  WEIGHT \u000EKG\u000E  LENGTH \u000ECM\u000E                                           \u000E  \u000E            POS POS       OVERLAYS       MAX-WGT  ARM  PR U/L ZONE       VALID TYPE         IDX\u000E\u000F1__\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___771\u000E\u000E\u000F1529.1\u000E\u000E\u000F_1\u000E \u000E\u000FL\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F__1\u000E\u000E\u000F3__\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F__1497\u000E\u000E\u000F3038.8\u000E\u000E\u000F_1\u000E \u000E\u000FL\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F__2\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E \u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F___\u000E\u000E\u000F______\u000E\u000E\u000F______\u000E\u000E\u000F__\u000E \u000E\u000F_\u000E  \u000E\u000F_\u000E \u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E\u000E\u000F____\u000E \u000E\u000F___\u000E\u000F \u0003";
		LaddStr = LaddStr.replaceAll("\u000E\u000F", "@");
		System.out.println(LaddStr);
		int a = LaddStr.lastIndexOf("IDX");
		String substring2 = LaddStr.substring(a+3);
		System.out.println(substring2);
		int b = substring2.length() / 97;
		System.out.println(b);
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < b; i++) {
			String s = LaddStr.substring(a + 3  + (97 * (i)), a + 7 + (97 * (i)));
			System.out.println(s);
			Pattern compile = Pattern.compile("[0-9]+");
			Matcher matcher = compile.matcher(s);
			while (matcher.find()) {
				li.add(matcher.group());
			}
		}

		for (String s : li) {
			System.out.println("===" + s);
		}
	}
	@Test
	public void ttt(){
		/*List<String> a  = new ArrayList<String>();
		a.add("1");
		a.add("2");
		
		List<String> b  = new ArrayList<String>();
		b.add("1");
		b.add("2");
		
		if(a.contains("0")){
			System.out.println(11);
			b.remove(1);
		}
	*/
		
		Po po = new Po();
		po.setUsername("3");
		Po po2 = new Po();
		po2.setUsername("2");

	}
	
	@Test
	public void t(){
	    double a = 2;
	    double b = 1.8;
	    double c =1.5;
	    double x = a-b;
	    double y = a-c ;
	    System.out.println(x);
	    System.out.println(y);
	}
	
}

class Po{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}