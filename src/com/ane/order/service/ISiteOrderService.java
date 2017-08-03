package com.ane.order.service;

import java.util.List;

import com.ane.order.domain.OptOrder;


public interface ISiteOrderService {
	
	public int insert(OptOrder code); 
	
	public OptOrder getMaxOrder();
	
	List<OptOrder> getOrderByPage(OptOrder record,Integer pageSize,Integer offset);
	
	int getCounts(OptOrder record);
	
	OptOrder findById(Long id);
}