/**
 * 
 */
package com.ane.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.order.dao.OptTrailDao;
import com.ane.order.domain.OptTrail;
import com.ane.order.service.IOptTrailService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-21 下午5:59:34
 * @version 1.0
 */
@Service("optTrailServiceImpl")
public class OptTrailServiceImpl implements IOptTrailService{

	@Resource
	private OptTrailDao optTrailDao;
	/* (non-Javadoc)
	 * @see com.ane.order.service.IOptTrailService#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.ane.order.service.IOptTrailService#insert(com.ane.order.domain.OptTrail)
	 */
	public int insert(OptTrail record) {
		optTrailDao.insert(record);
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.ane.order.service.IOptTrailService#findById(java.lang.Integer)
	 */
	public OptTrail findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ane.order.service.IOptTrailService#update(com.ane.order.domain.OptTrail)
	 */
	public int update(OptTrail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.ane.order.service.IOptTrailService#getTrailByEwbNo(java.lang.String)
	 */
	public List<OptTrail> getTrailByEwbNo(String ewbNo) {
		return optTrailDao.getTrailByEwbNo(ewbNo);
	}

}
