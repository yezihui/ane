/**
 * 
 */
package com.ane.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.order.dao.OptRetreatMapper;
import com.ane.order.domain.OptRetreat;
import com.ane.order.service.IOptRetreatService;

 /**
 * <p>功能描述：</p>
 * @author : 叶嘉贤
 * @date : 2017-3-13 上午10:17:22
 * @version 1.0
 */
@Service("optRetreatService")
public class OptRetreatServiceImpl implements IOptRetreatService {

	@Resource
	private OptRetreatMapper optRetreatDao;
	
	public boolean delete(Long id) {
		optRetreatDao.delete(id);
		return true;
	}

	public int insert(OptRetreat record) {
		return optRetreatDao.insert(record);
	}

	public OptRetreat findByEwb(String id) {
		return optRetreatDao.findByEwb(id);
	}

	public boolean update(OptRetreat record) {
		optRetreatDao.update(record);
		return true;
	}

	public List<OptRetreat> findAll(OptRetreat record) {
		return optRetreatDao.findAll(record);
	}

}
