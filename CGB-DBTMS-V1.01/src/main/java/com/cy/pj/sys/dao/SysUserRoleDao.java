package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserRoleDao {
	
	@Select("select role_id from sys_user_roles where user_id=#{userId}")
	List<Integer> findRoleIdsByUserId(Integer userId);
	
	@Delete("delete from sys_user_roles where user_id=#{id}")
	int deleteObjectsByUserId(Integer id);
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
	
}
