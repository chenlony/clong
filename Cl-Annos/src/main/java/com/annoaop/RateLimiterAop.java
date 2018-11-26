package com.annoaop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.storm.guava.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cltannotation.ClRateLimiter;
import com.sun.xml.internal.ws.client.RequestContext;

@Aspect
@Component
public class RateLimiterAop {
	//存放接口是否已经存在
	private static ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<String, RateLimiter>();
	@Pointcut("execution(public * com.itmayeidu.api.*.*(..))")
	public void rop(){
		
	}
	@Around("rop()")
	public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		//获取方法
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		//反射获取方法上是否有ClRateLimiter注解
		ClRateLimiter annotation = signature.getMethod().getDeclaredAnnotation(ClRateLimiter.class);
		if(annotation ==null){
			//正常方法执行
			Object proceed = proceedingJoinPoint.proceed();
			return proceed;
		}
		//获取注解上的值
		double value = annotation.value();//获取配置的速率
		long timeOut = annotation.timeOut(); // 获取等到令牌等待时间
		RateLimiter rateLimiter = getLimiter(value,timeOut);
		//判断令牌桶获取token是否超时
		boolean tryAcquire = rateLimiter.tryAcquire(timeOut,TimeUnit.SECONDS);
		if(!tryAcquire){
			serviceDowng();
			return null;
		}
		//获取到令牌执行
		Object proceed = proceedingJoinPoint.proceed();
		return proceed;
		
	}
	/**
	 * 服务降级
	 * @throws IOException 
	 */
	private void serviceDowng() throws IOException {
		// 执行服务降级处理
				System.out.println("执行降级方法,亲,服务器忙！请稍后重试!");
				ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				HttpServletResponse response = attributes.getResponse();
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				try {
					writer.println("执行降级方法,亲,服务器忙！请稍后重试!");
				} catch (Exception e) {

				} finally {
					writer.close();
				}

			}

	private RateLimiter getLimiter(double value, long timeOut) {
		//获取当前的url
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String requestURI = request.getRequestURI();
		RateLimiter ratLimiter = null;
		/*
		 * //如果map里面有rateLimiter对象则直接从map里面拿
		 * 如果map里面没有，则直接create一个，相当于加个缓存
		 */
		if(!rateLimiterMap.containsKey(requestURI)){
			//开启令牌桶限流
			 ratLimiter = RateLimiter.create(value);
			rateLimiterMap.put(requestURI, ratLimiter);
		}else{
			ratLimiter = rateLimiterMap.get(requestURI);
		}
		return ratLimiter;
	}
	public static void main(String[] args) {
		// 使用Java反射技术获取方法上是否有@ExtRateLimiter注解类
//		ClRateLimiter extRateLimiter = IndexController.class.getClass().getAnnotation(ExtRateLimiter.class);
//		System.out.println(extRateLimiter);
	}
}
