package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.DictionaryType;


public interface DictionaryTypeDao {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryType record);

    int insertSelective(DictionaryType record);

    DictionaryType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictionaryType record);

    public List<DictionaryType> findAllType();
}