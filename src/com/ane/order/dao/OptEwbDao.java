package com.ane.order.dao;

import com.ane.order.domain.OptEwb;

public interface OptEwbDao {
    void delete(String ewbNo);

    void insert(OptEwb record);

    OptEwb findById(String ewbNo);

    void update(OptEwb record);
    
    OptEwb ewbInfo(String ewbNo);
}