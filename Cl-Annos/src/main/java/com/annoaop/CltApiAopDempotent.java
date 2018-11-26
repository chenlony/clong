package com.annoaop;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cltannotation.CltApiDempotent;
import com.cltannotation.CltApiToken;
import com.common.BaseRedisService;
import com.common.JsonUtil;
import com.common.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.spec.ECField;
import java.util.Enumeration;
/**
 * 日志处理，以及解决幂等性问题，
 * @author CL
 *
 */
@Component
@Aspect
public class CltApiAopDempotent {
    public org.slf4j.Logger logger = LoggerFactory.getLogger(CltApiAopDempotent.class);
    
    @Autowired
    private BaseRedisService baseRedisService;
        @Pointcut("execution(public * com.eureka.controller.*.*(..))")
        public void rlAop(){

        }
        //前置通知，解决重复提交问题
        @Before("rlAop()")
        public void before(JoinPoint point){
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 判断方法是是否加注解
            CltApiToken cltApiToken = signature.getMethod().getDeclaredAnnotation(CltApiToken.class);
            if(cltApiToken!=null){
                getToken();
            }
        }
        //环绕通知
        @Around("rlAop()")
        public Object doBefore(ProceedingJoinPoint point) throws Throwable {
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 判断方法是是否加注解
            CltApiDempotent cltApiDempotent = signature.getMethod().getDeclaredAnnotation(CltApiDempotent.class);
            String token =null;
           if(cltApiDempotent != null){
               String type = cltApiDempotent.type();
               HttpServletRequest request = getRequest();
               if(type.equals("head")){
                   token = request.getHeader("token");
               }else{
                   token = request.getParameter("token");
               }
               if(StringUtils.isEmpty(token)){
                   return null;
               }

            boolean istoken=findBySessionToken(token);
           if(!istoken){
//               response("请勿重复提交");
               return null;
           }
           }
           Object ob = point.proceed();//放行
            return ob;
        }

        /**
         * 记录日志
         * @param joinPoint
         */
        @Before("rlAop()")
        public void doBefore(JoinPoint joinPoint){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            logger.info("请求URL : " + request.getRequestURL().toString());
            logger.info("请求方式HTTP_METHOD : " + request.getMethod());
            logger.info("请求IP : " + request.getRemoteAddr());
            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String name = (String) enu.nextElement();
                logger.info("name:{},value:{}", name, request.getParameter(name));
            }
        }
        /**
         * 记录返回值的日志
         * @param ret
         * @throws Throwable
         */
        @AfterReturning(returning = "ret", pointcut = "rlAop()")
        public void doAfterReturning(Object ret) throws Throwable {
            // 处理完请求，返回内容
            logger.info("RESPONSE : " + JsonUtil.toJSONString(ret));
        }

        public HttpServletRequest getRequest(){
           ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            return request;
        }

        public void response(String msg) throws IOException {
            ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            response.setHeader("Content-type","text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            try{
                writer.println(msg);
            }catch (Exception e) {
            }finally {
                    writer.close();
            }
        }

        public String getToken(){
            String uuid = StringUtil.getUUID();
            baseRedisService.setString(uuid, uuid, 60*60*1000L);
            return uuid;
        }

        public boolean findBySessionToken(String token){
           String attribute = (String) baseRedisService.getString(token);
            if(attribute!=null&&attribute!="" &&attribute.equals(token)){
            	baseRedisService.delKey(token);
                return true;
            }
           return false;
        }
   
}
