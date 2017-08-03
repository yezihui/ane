package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.Areas;

public interface AreasDao {

    List<Areas> listAll(Integer id);
    
    String getAreaName(String areaid);
}