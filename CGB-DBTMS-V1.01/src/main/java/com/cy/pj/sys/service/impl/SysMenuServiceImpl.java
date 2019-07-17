package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import com.cy.pj.sys.service.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
		throw new IllegalArgumentException("id值不正确");
		int rowCount=sysMenuDao.getChildCount(id);
		if(rowCount>0)
		throw new ServiceException("请先删除子元素");
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		int rows=sysMenuDao.deleteObject(id);
		return rows;
	}
	@Override
	public int updateObject(SysMenu entity) {
		//1.验证参数有效性
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		//...
		//2.持久化数据到数据库
		int rows;
		try {
		rows=sysMenuDao.updateObject(entity);
		}catch(Throwable e) {
		log.error(e.getMessage());
		//send mail and message to user
		throw new ServiceException(e.getMessage());
		}
		//3.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysMenu entity) {
		try{Thread.sleep(5000);}
		catch(Exception e) {e.printStackTrace();}
	    //1.验证参数有效性
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		//...
		//2.持久化数据到数据库
		int rows;
		try {
		rows=sysMenuDao.insertObject(entity);
		}catch(Throwable e) {
		log.error(e.getMessage());
		//send mail and message to user
		throw new ServiceException(e.getMessage());
		}
		//3.返回结果
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list=
		sysMenuDao.findObjects();
		for(Map<String,Object> map:list) {
			log.info(map.toString());
		}
		if(list==null||list.size()==0)
		throw new ServiceException("没有对应记录");
		return list;
	}
}
