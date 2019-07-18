package com.cy.pj.common.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ServiceCacheAspect {
	//@Autowired
	//private Cache cache;//JVM
	@Around("execution(* com.cy.pj.sys.service..*.find*(..))")
	public Object cacheAround(ProceedingJoinPoint jp)
	throws Throwable{
		//1.从cache取数据
		//Object result=cache.get(key);
		//if(result!=null)return result;
		//2.cache没有则从数据库取数据
		 Object result=jp.proceed();
		//3.将取到的数据放到cache
		//cache.put(key,result);
		return result;//查询到的数据
	}
}




