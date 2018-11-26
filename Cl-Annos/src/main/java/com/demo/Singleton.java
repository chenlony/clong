package com.demo;
/**
 * 双检查锁模式
 * @author CL
 *
 */
public class Singleton {
	/**
	 * volatile 关键字为了防止指令重排。（指令重排即在JVM中为了提高效率可能会发生不是按顺序执行代码，只要能保证代码结果最终一致性，
	 * ）
	 * 
	 * 分配内存空间
	 * 初始化对象
	 * 将songleton对象只想分配的内存地址
	 * 
	 * 加了volatile是让上面三哥步骤顺序执行防止指令重排
	 * 
	 * 
	 * volatile 关键字只能保证可见性，顺序性，不能保证原子性。
	 */
	private static volatile Singleton singleton;
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		if(singleton == null){
			synchronized (Singleton.class) {
				if(singleton ==null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
