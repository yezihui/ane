/**
 * 
 */
package com.ane.arbitration.service;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationType;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 上午11:37:46
 * @version 1.0
 */
public interface IArbitrationTypeService {

	public List<ArbitrationType> findArbType();
	
	public boolean add(ArbitrationType at);
	
	public boolean update(ArbitrationType at);
	
	public boolean delete(Long id);
	
	public List<ArbitrationType> findAll();
	
	public List<ArbitrationType> getTypes();
	
	public int getTypeOrder();
	
	public ArbitrationType findById(Long id);
	
	public Integer getDesc();
	
	public boolean findByName(String name);
	
	public boolean findByCode(String code);
	
	public boolean findByOrder(String order);
	
	public int getArbitrationTypeNum();
}
