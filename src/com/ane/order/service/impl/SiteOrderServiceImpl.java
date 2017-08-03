package com.ane.order.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.basic.dao.AreasDao;
import com.ane.basic.dao.BasicAddressLibraryDao;
import com.ane.basic.dao.CitiesDao;
import com.ane.basic.dao.ProvincesDao;
import com.ane.basic.domain.BasicAddressLibrary;
import com.ane.order.dao.OptOrderDao;
import com.ane.order.domain.OptOrder;
import com.ane.order.service.ISiteOrderService;

@Service("siteOrderService")
public class SiteOrderServiceImpl implements ISiteOrderService{

	@Resource
	private OptOrderDao optOrderDao;
	@Resource
	private ProvincesDao provincesDao;
	@Resource
	private CitiesDao citiesDao;
	@Resource
	private AreasDao areasDao;
	@Resource
	private BasicAddressLibraryDao basicAddressLibraryDao;
	
	public int insert(OptOrder code) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = null;
		try {
			date = sdf.parse(code.getSendDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date != null){
			code.setSendTime(date);
		}
		BasicAddressLibrary b = new BasicAddressLibrary();
		BasicAddressLibrary b1 = new BasicAddressLibrary();
		/**
		 * 添加 寄件地址信息 yjx
		 * 2016-12-7 14:36:43
		 */
		BasicAddressLibrary bal = basicAddressLibraryDao.getMaxId();
		if(bal != null && bal.getAddressLibrartId()!=null){
			b.setAddressLibrartId(bal.getAddressLibrartId() + 1);
			code.setSendCustomerAddressId(bal.getAddressLibrartId() + 1);
		}else{
			b.setAddressLibrartId(1);
			code.setSendCustomerAddressId(1);
		}
		b.setAddress(code.getSendAddress());
		b.setAreaId(code.getA());
		b.setCityId(code.getC());
		b.setProvinceId(code.getP());
		String fullAddress = "";
		fullAddress += provincesDao.getProvinceName(code.getP()+"")+"-";
		fullAddress += citiesDao.getCityName(code.getC()+"")+"-";
		fullAddress += areasDao.getAreaName(code.getA()+"");
		b.setFullAddress(fullAddress);
		if(bal != null && bal.getAddressLibrartId()!=null){
			b1.setAddressLibrartId(bal.getAddressLibrartId() + 2);
			code.setReceiveCustomerAddressId(bal.getAddressLibrartId() + 2);
		}else{
			b1.setAddressLibrartId(2);
			code.setReceiveCustomerAddressId(2);
		}
		b1.setAddress(code.getReceiveAddress());
		b1.setAreaId(code.getArea());
		b1.setCityId(code.getCity());
		b1.setProvinceId(code.getProvince());
		fullAddress = "";
		fullAddress = fullAddress+provincesDao.getProvinceName(code.getProvince()+"")+"-";
		fullAddress = fullAddress+citiesDao.getCityName(code.getCity()+"")+"-";
		fullAddress = fullAddress+areasDao.getAreaName(code.getArea()+"");
		b1.setFullAddress(fullAddress);
		int o= optOrderDao.insert(code);
		basicAddressLibraryDao.insert(b);
		basicAddressLibraryDao.insert(b1);
		return o;
	}

	public OptOrder getMaxOrder() {
		return optOrderDao.getMaxOrder();
	}

	public List<OptOrder> getOrderByPage(OptOrder record, Integer pageSize,
			Integer offset) {
		record.setOffset((offset-1)*pageSize);
		record.setPageSize(pageSize);
		return optOrderDao.getOrderByPage(record);
	}

	public int getCounts(OptOrder record) {
		return optOrderDao.getCounts(record);
	}

	public OptOrder findById(Long id) {
		return optOrderDao.findById(id);
	}

}
