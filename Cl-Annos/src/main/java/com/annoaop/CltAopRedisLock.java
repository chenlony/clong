package com.annoaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cltannotation.CltRedisLock;
/**
 * 锁的实现不完善
 * @author CL
 *互斥性。在任意时刻，只有一个客户端能持有锁。
     不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
     具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
     解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
 */
@Component
@Aspect
public class CltAopRedisLock {
	public static int ERROR_COUNT = 0;
	@Around("@annotation(CltRedisLock)")
	public void around(ProceedingJoinPoint point,CltRedisLock redisLock){
		
	}
	/**
	 * 获取锁
	 * @param time
	 * @param expire
	 * @return
	 */
//	public boolean lock(long time, int expire){
//		long nanoTime = System.nanoTime();
//		
//	}
}
