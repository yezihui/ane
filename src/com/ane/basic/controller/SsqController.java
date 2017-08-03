package com.ane.basic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ane.basic.dao.AreasDao;
import com.ane.basic.dao.CitiesDao;
import com.ane.basic.dao.ProvincesDao;
import com.ane.basic.domain.Areas;
import com.ane.basic.domain.Cities;
import com.ane.basic.domain.Provinces;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

@Controller
public class SsqController extends ResourceResponseSupport {

	@Resource
	private ProvincesDao provincesDao;
	
	@Resource
	private CitiesDao citiesDao;
	
	@Resource
	private AreasDao areasDao;
	
	@RequestMapping(value="/ssq/listProvice", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String listProvice(){
		List<Provinces> list = provincesDao.listAll();
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value="/ssq/listCity", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String listCity(Integer pro){
		List<Cities> list = citiesDao.listAll(pro);
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value="/ssq/listArea", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String listArea(Integer city){
		List<Areas> list = areasDao.listAll(city);
		return ResponseHelper.getJson(list);
	}
}
