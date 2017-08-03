/**
 * 
 */
package com.ane.arbitration.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.arbitration.domain.ArbitrationAssignment;
import com.ane.arbitration.service.IArbAssignService;
import com.ane.util.PageBean;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：仲裁人员分配控制层</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-10 下午4:09:02
 * @version 1.0
 */
@Controller
public class ArbAssignController extends ResourceResponseSupport  {

	@RequestMapping(value = "/arb/assign")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/assign");
		return mv;
	}
	
	@Autowired
	private IArbAssignService arbitrationAssignmentService;
	
	@RequestMapping(value = "/arbAssign/getAssignInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAssignInfo(ArbitrationAssignment assign,int page){
		PageBean pageBean = arbitrationAssignmentService.getAssignInfo(assign,20, page);
		return ResponseHelper.getJson(pageBean);
	}
	
	@RequestMapping(value = "/arbAssign/getAssign", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAssign(long id){
		ArbitrationAssignment list = this.arbitrationAssignmentService
				.findById(id);
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value="/arbAssign/saveAssignInfo", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveArbInfo(String applyAddresses ,String applyType,Long center,Long id,Long ownerId,int ownerType,BigDecimal rate){
		ArbitrationAssignment assign = new ArbitrationAssignment();
		assign.setApplyAddresses(applyAddresses);
		assign.setTypes(applyType);
		assign.setCenter(center);
		assign.setOwnerId(ownerId);
		assign.setRate(rate);
		boolean flag = false;
		if(null == id){
			flag = arbitrationAssignmentService.add(assign,ownerType);
		} else{
			assign.setId(id);
			flag = arbitrationAssignmentService.update(assign);
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value="/arbAssign/delAssign", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delArbInfo(Long id){
		boolean flag = arbitrationAssignmentService.delete(id);
		if(flag){
			return ResponseHelper.buildSuccessResp("数据删除成功！");
		}
		return ResponseHelper.buildErrorResp("数据删除失败！");
	}
}
