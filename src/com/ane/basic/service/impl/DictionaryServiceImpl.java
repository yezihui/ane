package com.ane.basic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.basic.dao.DictionaryDao;
import com.ane.basic.dao.DictionaryTypeDao;
import com.ane.basic.domain.Dictionary;
import com.ane.basic.domain.DictionaryType;
import com.ane.basic.service.IDictionaryService;

@Service("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {

	@Resource
	private DictionaryDao dictionaryDao;
	@Resource
	private DictionaryTypeDao dictionaryTypeDao;
	
	
	public List<Dictionary> findByTypeId(Long typeId) {
		return dictionaryDao.findByTypeId(typeId);
	}

	public List<DictionaryType> findAllType() {
		return dictionaryTypeDao.findAllType();
	}

	public Dictionary getInfoById(Long id) {
		return dictionaryDao.findById(id);
	}

	public int insert(Dictionary d) {
		return dictionaryDao.insert(d);
	}

	public int update(Dictionary d) {
		return dictionaryDao.update(d);
	}

	public int delete(Long id) {
		return dictionaryDao.delete(id);
	}
}
