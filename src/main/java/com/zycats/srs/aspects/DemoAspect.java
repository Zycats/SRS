package com.zycats.srs.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {
	@Around("@annotation(com.zycats.srs.aspects.Demo) && execution(public * *(..))")
	public Object performDemo(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		System.out.println("this has happen before join point");
		
		Object value = null ;
		
		try{
			value = proceedingJoinPoint.proceed();
		}catch(Throwable throwable){
			throw throwable;
		}
		finally {
			System.out.println("===============================");
			System.out.println(proceedingJoinPoint.getTarget().getClass().getName());
			System.out.println(proceedingJoinPoint.getSignature().getName());
			System.out.println("===============================");
			System.out.println(value);
			System.out.println("===============================");
			System.out.println("this happened after join point");
		}
		
		return value;
		
		
	}
	

}


