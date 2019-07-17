package com.cy.pj.sys.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao extends PageDao<SysUserDeptVo> {
    
	 SysUserDeptVo findObjectById(Integer id);
	
	 int updateObject(SysUser entity);
	 
	 int insertObject(SysUser entity);
	
	 @Update("update sys_users "
     		+ "set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() "
     		+ "where id=#{id}")
	 int validById(
			 @Param("id")Integer id,
			 @Param("valid")Integer valid,
			 @Param("modifiedUser")String modifiedUser);
	 
	 @Select("select * from sys_users where username=#{username}")
	 SysUser findUserByUserName(String username);
}
