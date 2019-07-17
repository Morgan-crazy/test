package com.cy.pj.sys.entity;

import java.util.Date;

import lombok.Data;
@Data
public class BaseEntity {
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
