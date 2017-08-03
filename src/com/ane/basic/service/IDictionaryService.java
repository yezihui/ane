package com.ane.basic.service;

import java.util.List;

import com.ane.basic.domain.Dictionary;
import com.ane.basic.domain.DictionaryType;

public interface IDictionaryService {
	public List<Dictionary> findByTypeId(Long typeId); 
	
	public List<DictionaryType> findAllType(); 
	
	public Dictionary getInfoById(Long id);
	
	public int insert(Dictionary d);
	
	public int update(Dictionary d);
	
	public int delete(Long id);
	
}
