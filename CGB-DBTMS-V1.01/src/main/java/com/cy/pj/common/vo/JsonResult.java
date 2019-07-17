package com.cy.pj.common.vo;
import java.io.Serializable;

import lombok.Data;
/** VO:封装控制层要返回给客户端的数据*/
@Data
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 5415540679197546732L;
	private int state=1;//1 ok,0 error
	private String message="ok";
	private Object data;
	public JsonResult() {}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	public JsonResult(Throwable e) {
		this.message=e.getMessage();
		this.state=0;
	}
}
