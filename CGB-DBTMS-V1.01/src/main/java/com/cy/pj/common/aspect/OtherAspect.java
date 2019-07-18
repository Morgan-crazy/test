package com.cy.pj.common.aspect;
import java.util.Arrays;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Aspect
@Service
@Slf4j
public class OtherAspect {
	
	@Pointcut("bean(*ServiceImpl)")
	public void otherPointCut() {}
	
	@Before("otherPointCut()")
	public void beforeMethod(JoinPoint jp) {
		System.out.println("Other.@Before");
		Object[] args=jp.getArgs();
		System.out.println("Other.@Before.args-->"+Arrays.toString(args));
		
	}

	@After("otherPointCut()")
	public void afterMethod() {
		System.out.println("Other.@After");
	}

	@AfterReturning("bean(*ServiceImpl)")
	public void returnMethod() {
		System.out.println("Other.@AfterReturning");
	}
	
	@AfterThrowing("bean(*ServiceImpl)")
	public void throwMethod() {
		System.out.println("Other.@AfterThrowing");
	}
	
	@Around("bean(*ServiceImpl)")
	//@Around("within(com.cy.pj.sys.service..*)")
	//@Around("execution(* com.cy.pj.sys.service..*.find*(..))")
	//@Around("@annotation(com.cy.pj.common.annotation.RequiredLog)")
	public Object around(ProceedingJoinPoint jp)
	throws Throwable{
		log.info("other.@Around.start:"+System.currentTimeMillis());
		Object result=jp.proceed();
		log.info("other.@Around.after:"+System.currentTimeMillis());
		return result;
	}
}






