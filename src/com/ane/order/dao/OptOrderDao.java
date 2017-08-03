package com.ane.order.dao;

import java.util.List;

import com.ane.order.domain.OptOrder;


public interface OptOrderDao {
    int insert(OptOrder record);

    OptOrder getMaxOrder();
    
    List<OptOrder> getOrderByPage(OptOrder record);
	
	int getCounts(OptOrder record);
	
	OptOrder findById(Long id);
}