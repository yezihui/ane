/**
 * 
 */
package com.ane.claim.controller;

import java.text.ParseException;
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

import com.ane.arbitration.domain.Arbitration;
import com.ane.arbitration.domain.ArbitrationType;
import com.ane.arbitration.service.IArbitrationService;
import com.ane.arbitration.service.IArbitrationTypeService;
import com.ane.basic.domain.AuthOrganization;
import com.ane.basic.service.IAuthOrganizationService;
import com.ane.claim.domain.Claim;
import com.ane.claim.domain.ClaimDetail;
import com.ane.claim.service.IClaimDetailService;
import com.ane.claim.service.IClaimService;
import com.ane.util.PageBean;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

/**
 * <p>
 * 功能描述：
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author : 叶嘉贤
 * @date : 2017-3-1 下午3:59:58
 * @version 1.0
 */
@Controller
public class ClaimController extends ResourceResponseSupport {

	@Autowired
	private IAuthOrganizationService authOrganizationService;
	@Autowired
	private IArbitrationService arbService;
	@Resource
	private IArbitrationTypeService arbitrationTypeService;
	@Resource
	private IClaimService claimService;
	@Resource
	private IClaimDetailService claimDetailService;

	@RequestMapping(value = "/claim/declare")
	public ModelAndView declare() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/claim/declare");
		return mv;
	}

	@RequestMapping(value = "/claim/search")
	public ModelAndView search() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/claim/search");
		return mv;
	}

	@RequestMapping(value = "/claim/confirm")
	public ModelAndView confirm() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/claim/confirm");
		return mv;
	}

	@RequestMapping(value = "/claim/audit")
	public ModelAndView audit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/claim/audit");
		return mv;
	}

	@RequestMapping(value = "/claim/apply")
	public ModelAndView apply() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/claim/apply");
		return mv;
	}

	@RequestMapping(value = "/ewb/arbInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String ewbInfo(String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) throws ParseException {
		if (ewbNo == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		Arbitration oe = arbService.findByClaim(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		List<ArbitrationType> list = arbitrationTypeService.findArbType();
		boolean flag = false;
		if (null == oe.getApplyTime()) {
			return ResponseHelper.buildErrorResp("该运单没有进行仲裁申报！");
		}
		if (null == oe.getAuditTime()) {
			return ResponseHelper.buildErrorResp("该运单仲裁申报没有审核通过！");
		}
		for (ArbitrationType type : list) {
			if (type.getName().equals(oe.getApplyType())) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			return ResponseHelper.buildErrorResp("申报类型不允许理赔！");
		}
		return ResponseHelper.getJson(oe);
	}

	@RequestMapping(value = "claim/saveCalimInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveType(Claim claim) {
		boolean flag = false;
		if (null != claim) {
			// 新增
			if (null == claim.getId()) {
				Arbitration oe = arbService.findByClaim(claim.getEwbNo());
				claim = claimcopy(oe, claim);
				flag = claimService.insert(claim);
			} else {
				flag = claimService.update(claim);
			}
		}
		if (flag) {
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		return ResponseHelper.buildErrorResp("操作失败！");
	}

	/**
	 * <p>
	 * 方法描述：
	 * </p>
	 * 
	 * @author : 叶嘉贤
	 * @date : 2017-3-1 下午7:09:41
	 * @param oe
	 * @param claim
	 */
	Claim claimcopy(Arbitration oe, Claim claim) {
		claim.setAmount(oe.getAmount());
		claim.setApplyTime(oe.getApplyTime());
		claim.setAuditTime(oe.getAuditTime());
		claim.setProductName(oe.getProductName());
		claim.setSendDate(oe.getSendDate());
		claim.setApplyType(oe.getApplyType());
		return claim;
	}

	@RequestMapping(value = "/claim/getClaims", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getClaims(Claim at, int page, HttpServletRequest req,
			HttpServletResponse resp) {
		// 按运单号查询·
		if (at.isChk()) {
			PageBean pageBean = claimService.searchByEwbNo(at, 20, page);
			return ResponseHelper.getJson(pageBean);
		} else {
			if (at.getSiteName() != null && at.getSiteName() != "") {
				AuthOrganization ao = authOrganizationService.findByName(at
						.getSiteName());
				if (ao != null)
					at.setSiteId(ao.getId());
			}
			PageBean pageBean = claimService.getArbitrationByPage(at, 20, page);
			return ResponseHelper.getJson(pageBean);
		}
	}

	@RequestMapping(value = "/claim/getCalimInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getArbInfo(Long id) {
		Claim aot = claimService.findById(id);
		return ResponseHelper.getJson(aot);
	}

	@RequestMapping(value = "/claim/saveCalimDetailInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveCalimDetailInfo(ClaimDetail cm, HttpServletRequest req,
			HttpServletResponse resp) {
		boolean flag = false;
		if (null != cm) {
			flag = claimDetailService.insert(cm);
		}
		if (flag) {
			Claim record = new Claim();
			record.setId(cm.getClaimId());
			if (cm.getAmount() == null) {
				record.setApplyState(2 + "");
			} else if (cm.getApplyReason() != null) {
				record.setApplyState(3 + "");
			}
			claimService.update(record);
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		return ResponseHelper.buildErrorResp("操作失败！");
	}

	@RequestMapping(value = "/claim/updateAuditCalim", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String updateAuditCalim(Long id, HttpServletRequest req,
			HttpServletResponse resp) {
		Claim record = new Claim();
		record.setId(id);
		record.setApplyState(4 + "");
		claimService.update(record);
		return ResponseHelper.buildSuccessResp("操作成功！");

	}
	
	@RequestMapping(value = "/claim/updatenoAuditCalim", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String updatenoAuditCalim(Long id, HttpServletRequest req,
			HttpServletResponse resp) {
		Claim record = new Claim();
		record.setId(id);
		record.setApplyState(5 + "");
		claimService.update(record);
		return ResponseHelper.buildSuccessResp("操作成功！");

	}
	
	@RequestMapping(value = "/claim/updateconAuditCalim", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String updateconAuditCalim(Long id, HttpServletRequest req,
			HttpServletResponse resp) {
		Claim record = new Claim();
		record.setId(id);
		record.setApplyState(7 + "");
		claimService.update(record);
		return ResponseHelper.buildSuccessResp("操作成功！");

	}
}
