package com.cy.shiroconfig;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	//配置SecurityManager对象(Shiro框架的核心管理器对象)
	@Bean
	public SecurityManager newSecurityManager(@Autowired Realm realm) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(newCacheManager());
		sManager.setRememberMeManager(newRememberMeManager());
		sManager.setSessionManager(newsSessionManager());
		return sManager;
	}
	//配置ShiroFilterFactoryBean对象(通过此对象创建shiro中的过滤器对象)
	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		//假如没有认证请求先访问此认证的url
		sfBean.setLoginUrl("/doLoginUI");
		// //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//静态资源允许匿名访问:"anon"
		map.put("/bower_components/**","anon");
		map.put("/build/**","anon");
		map.put("/dist/**","anon");
		map.put("/plugins/**","anon");
		map.put("/user/doLogin","anon");
		//退出
		map.put("/doLogout","logout");
		//除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/**","user");//authc
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}
	//配置shiro框架中一些bean对象的生命周期管理器

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	//配置代理对象创建器,通过此对象为目标业务对象创建代理对象方法上(@RequiresPermissions)
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	/**
	 * 此对象定义切入点以及通知增强
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
			@Autowired SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor=
				new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	//配置缓存Bean对象(Shiro框架提供)
	@Bean
	public CacheManager newCacheManager() {
		return new MemoryConstrainedCacheManager();
	}
	
	public SimpleCookie newCookie() {
		SimpleCookie c = new SimpleCookie();
		c.setMaxAge(10*60);
		return c;
	}
	 public CookieRememberMeManager newRememberMeManager() {
		 CookieRememberMeManager cManager=
		 new CookieRememberMeManager();
		 cManager.setCookie(newCookie());
		 return cManager;
	 }
	 
	 public DefaultWebSessionManager newsSessionManager() {
		 DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		 sessionManager.setGlobalSessionTimeout(60*60*1000);
		 return sessionManager;
	 }

}
