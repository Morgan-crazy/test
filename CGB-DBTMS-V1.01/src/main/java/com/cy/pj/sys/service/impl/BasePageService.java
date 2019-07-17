package com.cy.pj.sys.service.impl;
import java.util.List;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.PageDao;
import com.cy.pj.sys.service.PageService;
import com.cy.pj.sys.service.exception.ServiceException;
public abstract class BasePageService<T> implements PageService<T> {
	private PageDao<T> pageDao;
	public BasePageService(PageDao<T> pageDao) {
		this.pageDao=pageDao;
	}
	@Override
	public PageObject<T> findPageObjects(String name, Integer pageCurrent) {
		//1.验证参数有效性
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=pageDao.getRowCount(name);
		if(rowCount==0)
		throw new ServiceException("没找到对应记录");
		//3.查询当前页要呈现的记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<T> records=
		pageDao.findPageObjects(name,startIndex, pageSize);
		//4.封装查询结果并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

}
