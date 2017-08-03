package com.ane.basic.dao;

import java.util.List;

import com.ane.basic.domain.AuthOrganization;

public interface AuthOrganizationDao {
	void delete(Long id);

    int insert(AuthOrganization record);

    int getMaxOrder();

    AuthOrganization findById(Long id);

    AuthOrganization findByName(String name);
    
    List<AuthOrganization> findByType(AuthOrganization record);
    
    List<AuthOrganization> getSiteByPage(AuthOrganization record);
    
    AuthOrganization findByDeptId(Long siteId);
    
    int getCounts();
    
    int updateByPrimaryKeySelective(AuthOrganization record);

    int update(AuthOrganization record);
    
    Long getSiteId(Integer id);//获取寄件网点id
}