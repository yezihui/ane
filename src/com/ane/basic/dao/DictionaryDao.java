package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.Dictionary;


public interface DictionaryDao {
	
	public List<Dictionary> findByTypeId(Long typeId); 
	
	Dictionary findById(Long id);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int update(Dictionary record);
    
    int delete(Long id);
}