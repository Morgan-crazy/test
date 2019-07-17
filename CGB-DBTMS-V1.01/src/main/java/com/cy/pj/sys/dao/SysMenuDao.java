package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {

	@Select("select count(*) from sys_menus where parentId=#{id}")
	int getChildCount(Integer id);
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObject(Integer id);

	int updateObject(SysMenu entity);
	int insertObject(SysMenu entity);

	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	/**
	 * 查询所有菜单以及对应的上级菜单名称
	 * 1)一条记录(菜单信息)映射为内存中的一个map对象
	 * 2)多条记录对应多个map,多个map存储到list集合.
	 * @return
	 */
	List<Map<String,Object>> findObjects();
 //基于菜单id查找权限标识信息
	List<String> findPermissions(@Param("menuIds")Integer[] menuIds);

}





