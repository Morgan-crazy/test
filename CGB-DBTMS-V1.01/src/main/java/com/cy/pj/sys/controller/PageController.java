package com.cy.pj.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 在controller负责所有页面的呈现
 */
@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		System.out.println("doIndexUI");
		return "starter";
	}
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	@RequestMapping("doPageUI")
	public String doPageUI(){
		//Thread.sleep(2000);
		return "common/page";
	}
	//rest 风格的url
	//@PathVariable 注解用于取url数据赋值给对应变量
	@RequestMapping("{module}/{page}")
	public String doModuleUI(//rest
			@PathVariable String page) {
		return "sys/"+page;
	}
//	@RequestMapping("log/doLogUI")
//	public String doLogUI() {
//		return "sys/log_list";
//	}
//	@RequestMapping("menu/doMenuListUI")
//	public String doMenuListUI(){
//	 return "sys/menu_list";
//	}

}






