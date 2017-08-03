package com.ane.main.dao;

import java.util.List;

import com.ane.main.domain.Dept;

public interface DeptDao {
	Dept findById(Integer id);

    public void insert(Dept record);
	
	List<Dept> getDeptByPage(Dept record);
	
	List<Dept> getAllDepts();
	
	int getCounts();
	
	int getMaxOrder();
	
	void delete(int id);
	
	int update(Dept record);
}