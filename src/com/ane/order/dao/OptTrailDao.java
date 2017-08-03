package com.ane.order.dao;

import java.util.List;

import com.ane.order.domain.OptTrail;

public interface OptTrailDao {
    void delete(Integer id);

    void insert(OptTrail record);

    OptTrail findById(Integer id);

    void update(OptTrail record);
    
    List<OptTrail> getTrailByEwbNo(String ewbNo);

}