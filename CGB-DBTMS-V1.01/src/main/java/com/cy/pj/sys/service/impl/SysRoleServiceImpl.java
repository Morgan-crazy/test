package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.service.exception.ServiceException;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl 
               extends BasePageService<SysRole> 
                 implements SysRoleService {
	
	private SysRoleDao sysRoleDao;
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired 
	public SysRoleServiceImpl(
		SysRoleDao sysRoleDao,
		SysRoleMenuDao sysRoleMenuDao) {
		super(sysRoleDao);
		this.sysRoleDao=sysRoleDao;
		this.sysRoleMenuDao=sysRoleMenuDao;
	}
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		if(id==null||id<1)
		throw new IllegalArgumentException("id值不正确");
		return sysRoleDao.findObjectById(id);
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new IllegalArgumentException("必须为角色分配权限");
		//2.保存角色自身信息
		//2.1更新角色自身信息
		int rows=sysRoleDao.updateObject(entity);
		//2.2删除菜单角色关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		//2.3插入菜单角色新的关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//3.保存角色菜单关系数据
		return rows;
	}
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
		throw new IllegalArgumentException("必须为角色分配权限");
		//2.保存角色自身信息
		int rows=sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//3.保存角色菜单关系数据
		return rows;
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByUserId(id);
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	
}
