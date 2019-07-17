package com.cy.pj.sys.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;
/**
 * 借助此对象封装数据
 * 1)数据库中查询到的数据
 * 2)用户提交数据
 * 
 * POJO (Plain Ordinary Java Object-普通的java对象)
 * 1)VO (Value Object-值对象)
 * 2)PO (persistent object -持久化对象: entity):与表有映射关系
 * 3)....
 */
@Data //SysLog类在编译时会自动生成set/get写到.class文件
@ToString
public class SysLog implements Serializable{
	private static final long serialVersionUID = -8427441809753041210L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;
	
}
