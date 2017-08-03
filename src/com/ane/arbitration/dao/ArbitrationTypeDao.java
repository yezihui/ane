package com.ane.arbitration.dao;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationType;


public interface ArbitrationTypeDao {
	
	public List<ArbitrationType> findArbType();
	
	int add(ArbitrationType arbitrationType);
	
	int update(ArbitrationType arbitrationType);
	
	int delete(long id);
	
	ArbitrationType findById(long id);
	
	List<ArbitrationType> findAll();
	
	List<ArbitrationType> getTypes();
	
	Integer getDesc();
	
	Long findByName(String name);
	
	Long findByCode(String code);
	
	Long findByOrder(String order);
	
	int getMaxOrder();
	
	int getArbTypeNum();
}