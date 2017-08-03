package com.ane.arbitration.dao;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationOwnerType;

public interface ArbitrationOwnerTypeDao {

	int add(ArbitrationOwnerType owner);
	
	int update(ArbitrationOwnerType owner);
	
	int delete(long id);
	
	ArbitrationOwnerType findById(long id);
	
	List<ArbitrationOwnerType> findAll();
	
	List<ArbitrationOwnerType> getOwnerTypes(String applyType);
	
	Long findByOwnName(String code);
	
	Long findByOwnCode(String order);
	
	Long findByOwnOrder(String order);
	
	ArbitrationOwnerType getMaxOrder();
	
	//分页获取所有责任信息
	List<ArbitrationOwnerType> getArbOwnerByPage(ArbitrationOwnerType aot );
		
	//获取总页数
	int getArbitrationNum();
}