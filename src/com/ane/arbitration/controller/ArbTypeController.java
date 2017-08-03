/**
 * 
 */
package com.ane.arbitration.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.arbitration.domain.ArbitrationType;
import com.ane.arbitration.service.IArbitrationTypeService;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：仲裁类型控制层</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 上午11:29:26
 * @version 1.0
 */
@Controller
public class ArbTypeController extends ResourceResponseSupport  {

	@Resource
	private IArbitrationTypeService arbitrationTypeService;
	
	@RequestMapping(value = "/arb/type")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/type");
		return mv;
	}
	
	@RequestMapping(value = "arbType/getArbTypesByPay", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getArbTypesByPay(){
		List<ArbitrationType> list = arbitrationTypeService.findArbType();
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "arbType/getArbTypes", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getArbTypes(){
		List<ArbitrationType> list = arbitrationTypeService.findAll();
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "arbType/getTypeById", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getTypeById(long id){
		ArbitrationType aot = arbitrationTypeService.findById(id);
		return ResponseHelper.getJson(aot);
	}
	
	@RequestMapping(value = "arbType/findById/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ModelAndView getCompanyById(@PathVariable long id){
		List<ArbitrationType> list = this.arbitrationTypeService.findAll();
		ArbitrationType at = arbitrationTypeService.findById(id);
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db",com);
		mv.addObject("type",at);
		mv.addObject("types", list);
		mv.setViewName("arbitration/type/edit");
		return mv;
	}
	
	@RequestMapping(value = "arbType/getDesc", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getDesc(){
		Integer desc = arbitrationTypeService
				.getDesc();
		return ResponseHelper.getJson(desc+1);
	}
	
	@RequestMapping(value="arbType/saveType", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveType(ArbitrationType at){
		boolean flag = false;
		if(null != at){
			if(null == at.getId()){
				flag = arbitrationTypeService.add(at);
			}
			else{
				flag = arbitrationTypeService.update(at);
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value="arbType/delType", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delType(Long id){
		boolean flag = arbitrationTypeService.delete(id);
		if(flag){
			return ResponseHelper.buildSuccessResp("数据删除成功！");
		}
		return ResponseHelper.buildErrorResp("数据删除失败！");
	}
}
