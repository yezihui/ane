/**
 * 
 */
package com.ane.arbitration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.arbitration.domain.ArbitrationOwnerType;
import com.ane.arbitration.service.IArbOwnerTypeService;
import com.ane.util.PageBean;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：仲裁责任类型控制层</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 下午4:05:12
 * @version 1.0
 */
@Controller
public class ArbOwnerController extends ResourceResponseSupport  {
	
	@Autowired
	private IArbOwnerTypeService arbitrationOwnerTypeService;

	@RequestMapping(value = "/arb/owner")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/owner");
		return mv;
	}
	
	@RequestMapping(value = "/arbOwner/getArbtrations", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getArbtrations(ArbitrationOwnerType aot,int page){
		PageBean pageBean = arbitrationOwnerTypeService.getArbOwnerByPage(aot,20, page);
		return ResponseHelper.getJson(pageBean);
	}
	
	@RequestMapping(value = "/arbOwner/getAllOwners", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAllOwners(String applyType){
		List<ArbitrationOwnerType> list = arbitrationOwnerTypeService.getOwnerTypes(applyType);
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "/arbOwner/getOwnerById" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getOwnerById(long id){
		ArbitrationOwnerType aot = arbitrationOwnerTypeService.findById(id);
		return ResponseHelper.getJson(aot);
	}
	
	@RequestMapping(value = "/arbOwner/getMaxOrder" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getMaxOrder(){
		ArbitrationOwnerType aot = arbitrationOwnerTypeService.getMaxOrder();
		return ResponseHelper.getJson(aot);
	}
	
	@RequestMapping(value="/arbOwner/saveOwner", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveOwner(ArbitrationOwnerType aot){
		boolean flag = false;
		if(null != aot){
			if(null == aot.getId()){
				flag = arbitrationOwnerTypeService.add(aot);
			}
			else{
				flag = arbitrationOwnerTypeService.update(aot);
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value="/arbOwner/delOwner", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delOwner(Long id){
		boolean flag = arbitrationOwnerTypeService.delete(id);
		if(flag){
			return ResponseHelper.buildSuccessResp("数据删除成功！");
		}
		return ResponseHelper.buildErrorResp("数据删除失败！");
	}
}
