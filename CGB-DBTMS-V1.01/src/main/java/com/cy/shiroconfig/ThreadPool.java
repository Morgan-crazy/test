package com.cy.shiroconfig;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPool {
	
	private int corePoolSize=10;//核心线程  少于核心线程有事务直接创建线程
	private int maximumPoolSize=20;//最大线程
	private long keepAliveTime=30;//线程没有执行 时间过后销毁线程
	private int capacity=20;//阻塞
	private ThreadFactory threadFactory = new ThreadFactory() {
		
		private AtomicLong number = new AtomicLong(100);
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r,"async-thread-"+number.getAndIncrement());
		}
	};
	

	@Bean("asyncThreadPool")
	public ThreadPoolExecutor newtThreadPoolExecutor() {
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, 
				keepAliveTime,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(capacity),threadFactory);
				return pool;
	}
	

}