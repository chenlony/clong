package com.cltannotation;

public @interface CltRedisLock {
 String lockedPrefix() default "";//redis 锁key的前缀
 long timeOut() default 2000;  //锁时间
 int expireTime() default 10; //key在redis里存在的时间
}
