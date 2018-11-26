package com.demo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**贪心算法
 * 实现一个长整数，去掉第k个数字后，余下的数最大
 * 思路：如34932183
 * 比较若3<4则保留3，去掉3后面最大的数
 * 若32890
 * 比较3>2则去掉3就可以了
 * @author CL
 *
 */
public class Tanxinsuanfa {
	//32434
	public static String removeKDigits(String num , int k){
		int newLength = num.length() - k;
		char[] stack = new char[num.length()];//模拟栈
		int top = 0;
		for(int i = 0;i<num.length();i++){
			char c = num.charAt(i);
//			while(stack[i]<)

			//当栈顶数字大于遍历到的当前数字，栈顶数字出栈（相当于删除数字
			while(top > 0 && stack[top -1 ]> c && k> 0 ){
				top -=1;
				k -= 1;
			}
			stack[top++] = c;
		}
		int offset = 0;
		while(offset <newLength && stack[offset] =='0'){
			offset ++;
		}
		return offset == newLength?"0":new String(stack,offset,newLength -offset);
		
	}
	public static void main(String[] args) {
		System.out.println(removeKDigits("5412709361593212",3));
		System.out.println(removeKDigits("30200",1));
		System.out.println(removeKDigits("10",2));
		System.out.println(removeKDigits("541270936",3));

		/*List<paixun> list = new ArrayList<paixun>();
		paixun pai = new paixun();
		pai.setUser("23H/DD");
		list.add(pai);
		paixun paixun2 = new paixun();
		paixun2.setUser("134H/SSS");
		list.add(paixun2);
		paixun paixun3 = new paixun();
		paixun3.setUser("5H/SSS");
		list.add(paixun3);
		
		Collections.sort(list,new Comparator<paixun>() {

			@Override
			public int compare(paixun o1, paixun o2) {
				Pattern compile = Pattern.compile("[0-9]+");
				Matcher matcher = compile.matcher(o1.getUser());
				int a = 0;
				int b = 0;
				while(matcher.find()){
					a = Integer.parseInt(matcher.group());
					break;
				}
				Pattern compile1 = Pattern.compile("[0-9]+");
				Matcher matcher1 = compile1.matcher(o2.getUser());

				while(matcher1.find()){
					b = Integer.parseInt(matcher1.group());
					break;
				}
				return 	a-b > 0 ? 1 : -1;
			}
		});
		System.out.println("");*/
//		(list, new Comparator<MenuNIC>() {  
//			            @Override  
//			            public int compare(MenuNIC o1, MenuNIC o2) {  
//			                return o1.getSort().compareTo(o2.getSort());  
//			            }  
//			        }); );
		
	}
}
class sort<T> implements Comparator<T>{
	
	@Override
	public int compare(T arg0, T arg1) {
		
		return 0;
	}
	
}

class paixun{
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
class mai{
	
}