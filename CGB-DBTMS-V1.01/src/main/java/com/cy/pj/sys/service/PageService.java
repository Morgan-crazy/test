package com.cy.pj.sys.service;
import com.cy.pj.common.vo.PageObject;
/**
 *   分页service对象
 * @author tarena
 * @param <T>
 */
public interface PageService<T> {
	PageObject<T> findPageObjects(
			String name,Integer pageCurrent);
}
