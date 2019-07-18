package com.cy.pj.common.web;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;

import lombok.extern.slf4j.Slf4j;
/**
 *  Spring MVC 全局异常处理
 *  @ControllerAdvice 描述的类为全局异常处理类,
 *  此类内部可以定义很多异常处理方法
 */
//@ControllerAdvice
@RestControllerAdvice //此类也会交给spring管理
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ShiroException.class)
	public JsonResult doHandleShiroException(
			ShiroException e) {
		JsonResult r=new JsonResult();
		r.setState(0);
		if(e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}
	 @ExceptionHandler(BindException.class)
	 public JsonResult doHandleBindException(
			 BindException e) {
		 log.error(e.getMessage(),e);
		 FieldError fr=
		 e.getBindingResult().getFieldError();
		 JsonResult r=new JsonResult();
		 r.setState(0);
		 r.setMessage(fr.getDefaultMessage());
		 return r;
	 }
	 /**
	  * @ExceptionHandler 注解描述的方法为异常处理方法
	  */
	   @ExceptionHandler(RuntimeException.class)
	   //@ResponseBody
	   public JsonResult doHandleRuntimeException(
			   RuntimeException e) {
		   e.printStackTrace();
		   return new JsonResult(e);
	   }
}
