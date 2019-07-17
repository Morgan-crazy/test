package com.cy.pj.common.vo;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
/**VO*/
@Data
@ToString
public class Node implements Serializable{
	private static final long serialVersionUID = -8035928111049328300L;
	private Integer id;
	private String name;
	private Integer parentId;
}
