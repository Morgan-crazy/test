package com.cy.pj.sys.dao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysLog;
@Mapper
public interface SysLogDao extends PageDao<SysLog>{
	 @Delete("delete from sys_logs where id=#{id}")
	 int deleteObject(Integer id);
	 /**
	  * 基于多个id执行删除操作
	  * @param ids
	  * @return
	  */
	 int deleteObjects(@Param("ids")Integer...ids);
}
