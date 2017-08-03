/**
 * 
 */
package com.ane.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.main.dao.DeptDao;
import com.ane.main.domain.Dept;
import com.ane.main.service.IDeptService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-16 下午3:55:38
 * @version 1.0
 */
@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService{

	@Resource
	private DeptDao deptDao;
	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#findById(java.lang.Integer)
	 */
	public Dept findById(Integer id) {
		return deptDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#insert(com.ane.main.domain.Dept)
	 */
	public int insert(Dept record) {
		deptDao.insert(record);
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#getEmployeeByPage(com.ane.main.domain.Dept, java.lang.Integer, java.lang.Integer)
	 */
	public List<Dept> getDeptByPage(Dept record, Integer pageSize,
			Integer offset) {
		return deptDao.getDeptByPage(record);
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#getAllEmployees()
	 */
	public List<Dept> getAllDepts() {
		return deptDao.getAllDepts();
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#getCounts()
	 */
	public int getCounts() {
		return deptDao.getCounts();
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#update(com.ane.main.domain.Dept)
	 */
	public int update(Dept record) {
		deptDao.update(record);
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#getMaxOrder()
	 */
	public int getMaxOrder() {
		return deptDao.getMaxOrder();
	}

	/* (non-Javadoc)
	 * @see com.ane.main.service.IDeptService#delete(int)
	 */
	public int delete(int id) {
		deptDao.delete(id);
		return 1;
	}

}
