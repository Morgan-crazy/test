package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao extends PageDao<SysRole>{
	
	@Select("select id,name from sys_roles")
	List<CheckBox> findObjects();
	
	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject(Integer id);
	/**
	 * 更新角色信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	/**
	 * 写入角色信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	/**
	 * 基于角色id查询角色以及对应的菜单信息
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
}
