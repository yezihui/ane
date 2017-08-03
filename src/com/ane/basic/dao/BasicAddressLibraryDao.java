package com.ane.basic.dao;

import com.ane.basic.domain.BasicAddressLibrary;


public interface BasicAddressLibraryDao {
	BasicAddressLibrary getMaxId();

    int insert(BasicAddressLibrary record);
    
    BasicAddressLibrary findById(Integer addressLibrartId);

}