/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationHandlerDao;
import com.ane.arbitration.domain.ArbitrationHandler;
import com.ane.arbitration.service.IArbitrationHandlerService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-16 下午5:50:46
 * @version 1.0
 */
@Service("arbHandlerService")
public class ArbHandlerSerivceImpl implements IArbitrationHandlerService {

	@Resource
	private ArbitrationHandlerDao arbitrationHandlerRepository;

	public boolean add(ArbitrationHandler arbitrationHandler) {
		 this.arbitrationHandlerRepository.add(arbitrationHandler);
		return true;
	}
	
	

	public void delete(long id) {
		this.arbitrationHandlerRepository.delete(id);
	}

	public ArbitrationHandler findById(ArbitrationHandler arbitrationHandler) {
		return this.arbitrationHandlerRepository.findById(arbitrationHandler);
	}

	public List<ArbitrationHandler> getInfo(Long id) {
		return this.arbitrationHandlerRepository.getInfo(id);
	}

}
