package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService extends PageService<SysUserDeptVo> {
	/**
	 * 基于用户id查询:
	 * 1)用户信息
	 * 2)用户对应的部门信息
	 * 3)用户关联的角色信息
	 * @param id
	 * @return
	 */
	Map<String,Object> findObjectById(
			Integer id);
	
	int validById(Integer id,Integer valid,String modifiedUser);
	int saveObject(SysUser entity,Integer[]roleIds);
	int updateObject(SysUser entity,Integer[]roleIds);
	
}
