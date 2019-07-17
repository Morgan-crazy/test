package com.cy.pj.sys.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	@Qualifier("sysUserServiceImpl")
	private SysUserService sysUserService;
	
	@GetMapping("doFindObjectById") 
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(sysUserService.findObjectById(id));
	}
	@RequestMapping("doValidById")
	public JsonResult doValidById(
			Integer id,Integer valid) {
		sysUserService.validById(id, valid,
				"admin");
		return new JsonResult("update ok");
	}
	@PostMapping("doUpdateObject")
	public JsonResult doUpdateObject(
			SysUser entity,Integer[] roleIds) {
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}
	@PostMapping("doSaveObject")
	public JsonResult doSaveObject(
			@Valid SysUser entity,Integer[] roleIds) {
		sysUserService.saveObject(entity, roleIds);
		return new JsonResult("save ok");
	}
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,
			Integer pageCurrent) { 
		return new JsonResult(
		     sysUserService.findPageObjects(username,
				pageCurrent));
	}
	
	
	 @RequestMapping("doLogin")
	   @ResponseBody
	   public JsonResult doLogin(boolean isRememberMe, String username,String password){
		   //1.获取Subject对象
		 Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		   UsernamePasswordToken token=
		   new UsernamePasswordToken(
				   username,//身份信息
				   password);//凭证信息
		   //2.2对用户信息进行身份认证
		   if(isRememberMe) {
				token.setRememberMe(true); 
			 }
		   subject.login(token);
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   return new JsonResult("login ok");
	 }
	 
}
