package com.cy.pj.sys.service;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService extends PageService<SysLog>{
	/**
	 * 基于日志id执行日志删除操作
	 * @param ids 多个id
	 * @return 删除的行数
	 */
	int deleteObjects(Integer...ids);
}
