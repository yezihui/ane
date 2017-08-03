/**
 * 
 */
package com.ane.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.basic.domain.AuthOrganization;
import com.ane.basic.service.IAuthOrganizationService;
import com.ane.order.domain.OptEwb;
import com.ane.order.domain.OptRetreat;
import com.ane.order.service.IOptEwbService;
import com.ane.order.service.IOptRetreatService;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：</p>
 * @author : 叶嘉贤
 * @date : 2017-3-10 下午4:31:25
 * @version 1.0
 */
@Controller
public class ReturnController extends ResourceResponseSupport {

	@Resource
	private IOptEwbService optEwbService;
	@Autowired
	private IAuthOrganizationService authOrganizationService;
	@Resource
	private IOptRetreatService optRetreatService;
	
	
	@RequestMapping(value = "/return/apply")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/return/apply");
		return mv;
	}
	
	@RequestMapping(value = "/return/confirm")
	public ModelAndView confirm() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/return/confirm");
		return mv;
	}
	
	@RequestMapping(value = "/return/getEwbInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String getEwbInfo( String ewbNo) {
		//是否签收-签收是否为退件
		OptEwb oe = optEwbService.ewbInfo(ewbNo);
		if(oe.getState()==null||oe.getState()<4){
			return ResponseHelper.getJson("该运单还没有签收，不能进行退件申请");
		}else if(oe.getState()!=5){
			return ResponseHelper.getJson("该运单签收方式不是退件，不能进行退件申请");
		}
		return ResponseHelper.getJson(oe);
	}
	
	@RequestMapping(value = "/return/getRetreats", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String getRetreats(OptRetreat r, HttpServletRequest req, HttpServletResponse resp) {
		Long siteId = (Long) req.getSession()
				.getAttribute("loginSiteId");
		r.setMsgSiteId(siteId);
		if(r.getApplySiteName()!=null&&r.getApplySiteName()!=""){
			AuthOrganization auo = authOrganizationService.findByName(r.getReceiveSiteName());
			if(auo!=null){
				r.setApplySiteId(auo.getId());
			}else{
				return ResponseHelper.buildSuccessResp("申请网点不存在！");
			}
		}
		List<OptRetreat> re = optRetreatService.findAll(r);
		return ResponseHelper.getJson(re);
	}
	
	@RequestMapping(value = "/return/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String saveRetreat(OptRetreat r, HttpServletRequest req, HttpServletResponse resp) {
		r.setAcceptStatus(0+"");
		AuthOrganization auo = authOrganizationService.findByName(r.getReceiveSiteName());
		if(auo==null){
			return ResponseHelper.buildSuccessResp("通知网点不存在！");
		}else {
			r.setMsgSiteId(auo.getId());
		}
		OptRetreat f = optRetreatService.findByEwb(r.getEwbNo());
		if(f!=null){
			return ResponseHelper.buildSuccessResp("该运单已经进行退件申请！");
		}
		Long siteId = (Long) req.getSession()
				.getAttribute("loginSiteId");
		r.setApplySiteId(siteId);
		Integer n = optRetreatService.insert(r);
		if (n != null && n > 0)
			return ResponseHelper.buildSuccessResp("退件申请成功！");
		else
			return ResponseHelper.buildErrorResp("退件申请失败！");
	}
	
	@RequestMapping(value = "/return/confirm", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String confirm(String orderData, HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = true;
		int count=0;
		String [] arr= orderData.split(",");
		for(int i = 0;i < arr.length; i++){
			Long id = Long.parseLong(arr[i]);
			OptRetreat re = new OptRetreat();
			re.setId(id);
			re.setAcceptStatus("1");
			Long siteId = (Long) req.getSession()
					.getAttribute("loginSiteId");
			re.setConfirmSiteId(siteId);
			String name = (String) req.getSession()
					.getAttribute("loginSiteName");
			re.setConfirmBy(name);
			boolean b = optRetreatService.update(re);
			if(b==false){
				flag = false;break;
			}else{
				count++;
			}
		}
		if(flag){
			return ResponseHelper.buildErrorResp("成功执行"+count+"条记录");
		}else {
			return ResponseHelper.buildErrorResp("成功执行"+count+"条记录 第"+(count+1)+"条操作失败！");
		}
	}
}
