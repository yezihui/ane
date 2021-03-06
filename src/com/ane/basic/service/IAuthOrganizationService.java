package com.ane.basic.service;

import java.util.List;

import com.ane.basic.domain.AuthOrganization;

public interface IAuthOrganizationService {

	int delete(Long id);

    int insert(AuthOrganization record);

    int getMaxOrder();

    AuthOrganization findById(Long id);
    
    List<AuthOrganization> findByType(AuthOrganization record);
    
    AuthOrganization findByName(String name);
    
    AuthOrganization findByDeptId(Long siteId);
    
    List<AuthOrganization> getSiteByPage(AuthOrganization record,int pageSize,int offset);
    
    int getCounts();

    int updateByPrimaryKeySelective(AuthOrganization record);

    int update(AuthOrganization record);
    
    Long getSiteId(Integer id);//获取寄件网点id
}
