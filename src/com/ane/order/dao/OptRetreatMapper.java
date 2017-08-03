package com.ane.order.dao;

import java.util.List;

import com.ane.order.domain.OptRetreat;

public interface OptRetreatMapper {
    int delete(Long id);

    int insert(OptRetreat record);

    OptRetreat findByEwb(String id);

    int update(OptRetreat record);
    
    List<OptRetreat> findAll(OptRetreat record);
}