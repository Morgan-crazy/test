package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.service.exception.ServiceException;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Service
public class SysUserServiceImpl 
       extends BasePageService<SysUserDeptVo> 
       implements SysUserService {
	 private SysUserDao sysUserDao;
	 private SysUserRoleDao sysUserRoleDao;
	 @Autowired
	 public SysUserServiceImpl(SysUserDao sysUserDao,
			 SysUserRoleDao sysUserRoleDao) {
		 super(sysUserDao);
		 this.sysUserDao=sysUserDao;
		 this.sysUserRoleDao=sysUserRoleDao;
	}
	 @RequiresPermissions("sys:user:find")
	 @Override
	public Map<String, Object> findObjectById(Integer id) {
		 //1.参数校验
		 if(id==null||id<1)
		 throw new IllegalArgumentException("id值无效");
		 //2.查询用户以及对应的部门信息
		 SysUserDeptVo user=
		 sysUserDao.findObjectById(id);
		 //3.查找用户对应的角色信息
		 List<Integer> roleIds=
		 sysUserRoleDao.findRoleIdsByUserId(id);
		 //4.封装数据并返回
		 Map<String,Object> map=new HashMap<>();
		 map.put("user", user);
		 map.put("roleIds", roleIds);
		return map;
	}
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		throw new IllegalArgumentException("用户名不能为空");
		//2.写用户信息
		int rows=sysUserDao.updateObject(entity);
		//3.写用户角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		//2.写用户信息
		String salt=UUID.randomUUID().toString();
		SimpleHash sh=new SimpleHash(
				"MD5",//algorithmName 加密算法 
				entity.getPassword(), //source 没加密的密码
				salt, //盐值
				1);//hashIterations
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		int rows=sysUserDao.insertObject(entity);
		//3.写用户角色关系数据
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	 
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if(id==null||id<1)
		throw new IllegalArgumentException("id值不合法");
		if(valid!=1&&valid!=0)
		throw new IllegalArgumentException("状态值不正确");
		//..
		int rows=sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
		throw new ServiceException("记录可能已经存在");
		return rows;
	}
	

}
