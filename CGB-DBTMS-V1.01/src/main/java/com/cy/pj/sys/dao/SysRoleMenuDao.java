package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMenuDao {
	/**
	 * 基于菜单id执行删除操作
	 * @param id
	 * @return
	 */
	@Delete("delete from sys_role_menus where menu_id=#{id}")
	int deleteObjectsByMenuId(Integer id);
	/**
	 * 基于角色id执行删除操作
	 * @param id
	 * @return
	 */
	@Delete("delete from sys_role_menus where role_id=#{id}")
	int deleteObjectsByRoleId(Integer id);
	
	@Select("select menu_id from sys_role_menus where role_id=#{id}")
	List<Integer> findMenuIdsByRoleId(Integer id);
	/**
	 * 写入关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	//用户id查找菜单id信息
	List<Integer> findMenuIdsByRoleIds(@Param("roleIds")Integer[] roleIds);
}
