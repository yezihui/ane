/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationTypeDao;
import com.ane.arbitration.domain.ArbitrationType;
import com.ane.arbitration.service.IArbitrationTypeService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 上午11:38:26
 * @version 1.0
 */
@Service("arbitrationTypeService")
public class ArbTypeServiceImpl implements IArbitrationTypeService {
	@Resource
	private ArbitrationTypeDao arbitrationTypeDao;
	
	
	/**
	 * 获得树形仲裁类型
	 * @return
	 */
	public List<ArbitrationType> findArbType(){
		List<ArbitrationType> list = arbitrationTypeDao.findArbType();
		return list;
	}
	
	/**
	 * 添加仲裁类型
	 * @param arbType
	 */
	public boolean add(ArbitrationType at){
		//创建人 aot.setCreater(creater);
		arbitrationTypeDao.add(at);
		return true;
	}
	
	
	public boolean update(ArbitrationType at){
		 arbitrationTypeDao.update(at);
		 return true;
	}
	
	/**
	 * 通过id删除仲裁类型
	 * @param id
	 */
	public boolean delete(Long id){
		 this.arbitrationTypeDao.delete(id);
		 return true;
	}
	
	/**
	 * 获得所有仲裁类型 普通
	 * @return
	 */
	public List<ArbitrationType> findAll(){
		List<ArbitrationType> list = arbitrationTypeDao.findAll();
		return list;
	}
	
	/**
	 * 获得所有仲裁类型 简单字段
	 * @return
	 */
	public List<ArbitrationType> getTypes(){
		List<ArbitrationType> list = arbitrationTypeDao.getTypes();
		return list;
	}
	
	public int getTypeOrder() {
		return arbitrationTypeDao.getMaxOrder();
	}
	
	/**
	 * 通过id获取仲裁类型
	 * @param id
	 * @return
	 */
	public ArbitrationType findById(Long id){
		ArbitrationType arbitrationType = arbitrationTypeDao.findById(id);
		return arbitrationType;
	}
	
	
	public Integer getDesc(){
		return arbitrationTypeDao.getDesc();
	}
	
	public boolean findByName(String name){
		//如果本来就有两个同样的类型名存在 这里异常
		Long l =  arbitrationTypeDao.findByName(name);
		if(l!=null)
			return true;
		else
			return false;
	}
	
	public boolean findByCode(String code){
		Long lcode =  arbitrationTypeDao.findByCode(code);
		if(lcode!=null)
			return true;
		else
			return false;
	}
	
	public boolean findByOrder(String order){
		Long lorder =  arbitrationTypeDao.findByOrder(order);
		if(lorder!=null)
			return true;
		else
			return false;
	}
	
	public int getArbitrationTypeNum() {
		int Num = arbitrationTypeDao.getArbTypeNum();
		return Num;
	}
}
