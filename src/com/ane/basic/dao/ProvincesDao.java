package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.Provinces;

public interface ProvincesDao {

    List<Provinces> listAll();
    
    String getProvinceName(String provinceid);
}