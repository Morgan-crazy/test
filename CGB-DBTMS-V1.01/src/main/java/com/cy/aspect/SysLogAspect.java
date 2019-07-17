package com.cy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Service
@Slf4j
public class SysLogAspect {
	@Pointcut("bean(sysUserServiceImpl)")
	public void logPointCut() {}
	
	
	@Around("logPointCut()")//代理对象调用
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		//调用下一个切面或目标方法
		Object proceed = jp.proceed();
		return proceed;//返回给代理对象,在返回给controller
	}

}
