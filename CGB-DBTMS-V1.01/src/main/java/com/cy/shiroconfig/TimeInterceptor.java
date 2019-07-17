package com.cy.shiroconfig;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.cy.pj.sys.service.exception.ServiceException;

public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取当前时间的日历对象
				Calendar c=Calendar.getInstance();
				c.set(Calendar.HOUR_OF_DAY, 8);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				//获取允许访问的开始时间
				long start=c.getTimeInMillis();
				c.set(Calendar.HOUR_OF_DAY, 18);
				//获取允许访问的结束时间
				long end=c.getTimeInMillis();
				long time=System.currentTimeMillis();
				if(time<start||time>end)
				throw new ServiceException("此时间之内不允许访问");
				return true;//true表示放行,false 反之.
		
	}
}
