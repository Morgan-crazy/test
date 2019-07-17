package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
@RestController
@RequestMapping("/log/")
public class SysLogController {
	@Autowired
    private SysLogService sysLogService;
	
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteObjects(
			Integer...ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
	@RequestMapping(value="doFindPageObjects")
	public JsonResult doFindPageObjects(
		String username,Integer pageCurrent){
		PageObject<SysLog> po=
		sysLogService.findPageObjects(
				username,pageCurrent);
		return new JsonResult(po);
	}//json string (默认jackson)
}
