package com.cy.pj.sys.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;
/**
 * POJO:一般java对象(属性,set/get/toString)
 * 1)vo: Value Object  (与表中字段可以没有任何关系)
 * 2)po: 
 *
 */
@Data
@ToString
public class SysMenu implements Serializable{
	private static final long serialVersionUID = 6328149119720259896L;
	private Integer id;
	/**菜单名称*/
	private String name;
	/**菜单url: log/doFindPageObjects*/
	private String url;
	/**菜单类型(两种:按钮,普通菜单)*/
	private Integer type=1;
	/**排序(序号)*/
	private Integer sort;
	/**备注*/
	private String note;
	/**上级菜单id*/
	private Integer parentId;
	/**菜单对应的权限标识(sys:log:delete)*/
	private String permission;
	/**创建用户*/
	private String createdUser;
	/**修改用户*/
	private String modifiedUser;
	private Date createdTime;
	private Date modifiedTime;
}
