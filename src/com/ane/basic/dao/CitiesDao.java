package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.Cities;

public interface CitiesDao {
    
    List<Cities> listAll(Integer id);

    String getCityName(String cityid);
}