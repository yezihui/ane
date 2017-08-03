/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationCenterHandlerDao;
import com.ane.arbitration.domain.ArbitrationCenterHandler;
import com.ane.arbitration.service.IArbitrationCenterHandlerService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-16 下午5:50:08
 * @version 1.0
 */
@Service("arbCenterHandlerService")
public class ArbCenterHandlerServiceImpl implements IArbitrationCenterHandlerService{
	
	@Resource
	private ArbitrationCenterHandlerDao arbitrationCenterHandlerDao;
	
	public boolean add(ArbitrationCenterHandler record) {
		arbitrationCenterHandlerDao.add(record);
		return true;
	}

	public List<ArbitrationCenterHandler> getInfo(Long id) {
		return arbitrationCenterHandlerDao.getInfo(id);
	}

}
