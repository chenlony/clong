package com.cltannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD )//用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时触发
public @interface ClRateLimiter {
double value();//定义注解属性
long timeOut();

}
