package com.ane.basic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.basic.dao.AreasDao;
import com.ane.basic.dao.AuthOrganizationDao;
import com.ane.basic.dao.CitiesDao;
import com.ane.basic.dao.ProvincesDao;
import com.ane.basic.domain.AuthOrganization;
import com.ane.basic.service.IAuthOrganizationService;

@Service("authOrganizationService")
public class AuthOrganizationServiceImpl implements IAuthOrganizationService {

	@Resource
	private AuthOrganizationDao authOrganizationDao;
	@Resource
	private ProvincesDao provincesDao;
	@Resource
	private CitiesDao citiesDao;
	@Resource
	private AreasDao areasDao;
	
	public int delete(Long id) {
		authOrganizationDao.delete(id);
		return 1;
	}

	public int insert(AuthOrganization record) {
		String fullAddress = "";
		fullAddress += provincesDao.getProvinceName(record.getProvince()+"")+"-";
		fullAddress += citiesDao.getCityName(record.getCity()+"")+"-";
		fullAddress += areasDao.getAreaName(record.getRegion()+"");
		record.setFullAddress(fullAddress);
		return authOrganizationDao.insert(record);
	}

	public int getMaxOrder() {
		return authOrganizationDao.getMaxOrder();
	}

	public AuthOrganization findById(Long id) {
		return authOrganizationDao.findById(id);
	}

	public int updateByPrimaryKeySelective(AuthOrganization record) {
		return 0;
	}

	public int update(AuthOrganization record) {
		String fullAddress = "";
		fullAddress += provincesDao.getProvinceName(record.getProvince()+"")+"-";
		fullAddress += citiesDao.getCityName(record.getCity()+"")+"-";
		fullAddress += areasDao.getAreaName(record.getRegion()+"");
		record.setFullAddress(fullAddress);
		return authOrganizationDao.update(record);
	}

	public List<AuthOrganization> getSiteByPage(AuthOrganization record,
			int pageSize, int page) {
		record.setOffset((page-1)*pageSize);
		record.setPageSize(pageSize);
		return authOrganizationDao.getSiteByPage(record);
	}

	public int getCounts() {
		return authOrganizationDao.getCounts();
	}

	public Long getSiteId(Integer id) {
		return authOrganizationDao.getSiteId(id);
	}

	/* (non-Javadoc)
	 * @see com.ane.basic.service.IAuthOrganizationService#findByName(java.lang.String)
	 */
	public AuthOrganization findByName(String name) {
		return authOrganizationDao.findByName(name);
	}

	/* (non-Javadoc)
	 * @see com.ane.basic.service.IAuthOrganizationService#findByType(java.lang.Integer)
	 */
	public List<AuthOrganization> findByType(AuthOrganization record) {
		return authOrganizationDao.findByType(record);
	}

	/* (non-Javadoc)
	 * @see com.ane.basic.service.IAuthOrganizationService#findBySiteId(java.lang.Long)
	 */
	public AuthOrganization findByDeptId(Long siteId) {
		return authOrganizationDao.findByDeptId(siteId);
	}
}
