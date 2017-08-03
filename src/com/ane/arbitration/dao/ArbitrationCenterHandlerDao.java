package com.ane.arbitration.dao;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationCenterHandler;
/**
 * 
 * @title:ArbitrationCenterHandlerRepository
 * @description:
 * @author yjx
 * @date:2016-9-12 下午3:28:30
 */
public interface ArbitrationCenterHandlerDao {

    int add(ArbitrationCenterHandler record);

    List<ArbitrationCenterHandler> getInfo(Long id);
}
