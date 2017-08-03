/**
 * 
 */
package com.ane.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ane.order.dao.OptEwbDao;
import com.ane.order.domain.OptEwb;
import com.ane.order.service.IOptEwbService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-21 下午5:23:59
 * @version 1.0
 */
@Service("optEwbService")
public class OptEwbService implements IOptEwbService {

	@Resource
	private OptEwbDao OptEwbDao;
	
	public int delete(String ewbNo) {
		return 0;
	}

	public int insert(OptEwb record) {
		OptEwbDao.insert(record);
		return 1;
	}

	public OptEwb findById(String ewbNo) {
		return OptEwbDao.findById(ewbNo);
	}

	public int update(OptEwb record) {
		OptEwbDao.update(record);
		return 1;
	}

	public OptEwb ewbInfo(String ewbNo) {
		return OptEwbDao.ewbInfo(ewbNo);
	}

}
