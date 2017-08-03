package com.ane.order.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ane.order.domain.OptOrder;
import com.ane.order.domain.OptTrail;
import com.ane.order.service.IOptEwbService;
import com.ane.order.service.IOptTrailService;
import com.ane.order.service.ISiteOrderService;
import com.ane.util.PageBean;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

@Controller
public class SiteOrderController extends ResourceResponseSupport {

	@Autowired
	private IAuthOrganizationService authOrganizationService;
	@Resource
	private ISiteOrderService siteOrderService;
	@Resource
	private IOptEwbService optEwbService;

	@Resource
	private IOptTrailService optTrailServiceImpl;

	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order/siteOrderList")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/taskOrder");
		return mv;
	}
	
	@RequestMapping(value = "/order/follow")
	public ModelAndView follow() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/follow");
		return mv;
	}
	
	@RequestMapping(value = "/order/receiveOrder")
	public ModelAndView receiveOrder() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/receiveOrder");
		return mv;
	}

	@RequestMapping(value = "/order/sendOrder")
	public ModelAndView sendOrder() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/sendOrder");
		return mv;
	}
	@RequestMapping(value = "/order/dispatch")
	public ModelAndView dispatch() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/dispatch");
		return mv;
	}
	@RequestMapping(value = "/order/sign")
	public ModelAndView sign() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/sign");
		return mv;
	}

	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order/orderOrder")
	public ModelAndView orderList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/order/orderOrder");
		return mv;
	}

	@RequestMapping(value = "/order/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String saveOrder(OptOrder o) {
		o.setState(0);
		Integer n = siteOrderService.insert(o);
		if (n != null && n > 0)
			return ResponseHelper.buildSuccessResp("用户下单成功！");
		else
			return ResponseHelper.buildErrorResp("用户下单失败！");
	}

	/**
	 * 
	 * <p>
	 * 方法描述：
	 * </p>
	 * 
	 * @author : 叶嘉贤
	 * @date : 2017-2-4 下午4:21:09
	 * @param ewbNo
	 * @return
	 */
	@RequestMapping(value = "/send/getNextSite", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String getNextSite(String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) {
		// 该运单号是否存在
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		// 该运单是否正处于当前登录网点
		List<OptTrail> ot = optTrailServiceImpl.getTrailByEwbNo(ewbNo);
		// 不为空 取最新网点判断 并进行状态判断 必须要是到件
		if (!ot.isEmpty()&&ot.get(0).getState()!=0) {
			String cBy = (String) req.getSession()
					.getAttribute("loginSiteName");
			if (ot.get(0).getNextSite().equals(cBy)) {
				if (ot.get(0).getState() != 2) {
					return ResponseHelper.buildErrorResp("该运单并不是在库状态！");
				}else{
					String siteId = (String) req.getSession()
							.getAttribute("loginSiteName");
					if(!siteId.equals(ot.get(0).getNextSite())){
						return ResponseHelper.buildErrorResp("该运单并不在当前网点！");
					}
					Long nextId = getNextSiteId(oe,req,resp);
					if(nextId.equals(-1L)){
						return ResponseHelper.buildErrorResp("本网点是目的网点！");
					}else{
						AuthOrganization ao = authOrganizationService.findById(nextId);
						return ResponseHelper.getJson(ao);
					}
				}
			} else {
				return ResponseHelper.buildErrorResp("该运单并不在当前网点！");
			}
			//当前网点==运单正处于的网点
			//进行判断 下一网点
		}else{
			Long siteId = (Long) req.getSession()
					.getAttribute("loginSiteId");
			if(!siteId.equals(oe.getSendSiteId())){
				return ResponseHelper.buildErrorResp("该运单并不在当前网点！");
			}
			Long nextId = getNextSiteId(oe,req,resp);
			if(nextId.equals(-1L)){
				return ResponseHelper.buildErrorResp("本网点是目的网点！");
			}else{
				AuthOrganization ao = authOrganizationService.findById(nextId);
				return ResponseHelper.getJson(ao);
			}
		}
	}
	
	@RequestMapping(value = "/receive/getProvSite", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody String getProvSite(String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) {
		// 该运单号是否存在
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		// 该运单是否正处于当前登录网点
		List<OptTrail> ot = optTrailServiceImpl.getTrailByEwbNo(ewbNo);
		// 不为空 取最新网点判断 并进行状态判断 必须要是到件
		if (!ot.isEmpty()) {
			String cBy = (String) req.getSession()
					.getAttribute("loginSiteName");
			if (ot.get(0).getNextSite().equals(cBy)) {
				if (ot.get(0).getState() == 1) {
					AuthOrganization ao = new AuthOrganization();
					ao.setName(ot.get(0).getNextSite());
					return ResponseHelper.getJson(ao);
				}else{
					return ResponseHelper.buildErrorResp("该运单并不是发件状态！");
				}
			} else {
				return ResponseHelper.buildErrorResp("该运单并不发往当前网点！");
			}
			//当前网点==运单正处于的网点
			//进行判断 下一网点
		}else{
			return ResponseHelper.buildErrorResp("该运单并没有在运输途中！");
		}
	}

	/**
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-2-4 下午5:41:33
	 * @param siteId
	 * @return
	 */
	private Long getNextSiteId(OptEwb oe, HttpServletRequest req,
			HttpServletResponse resp) {
		Long siteId = (Long) req.getSession()
				.getAttribute("loginSiteId");
		AuthOrganization send = authOrganizationService.findById(siteId);
		AuthOrganization receive = authOrganizationService.findById(oe.getReceiveSiteId());
		//第一种情况 当前网点 == 目的网点
		Long r = getParentSite(receive.getId());
		Long s = getParentSite(send.getId());
		if(send.getId()==receive.getId()){
			return -1L;
		}else if(send.getSiteType()==3){
			//当前网点是直营网点类型 且当前网点不是目的网点
			return send.getOrderId();
		}else if(send.getSiteType()==2&&receive.getSiteType()==3){
			//当前网点是二级分拨 且目的网点是直营网点 目的网点所属分拨=当前网点
			if(s.equals(send.getId())){
				return receive.getId();
			}else{
				//不在同一分拨下
				return getParentSite(r);
			}
		}else if(send.getSiteType()==2&&receive.getSiteType()==2){
			return s;
		}else if(send.getSiteType()==1){
			if(receive.getSiteType()==3){
				if(getParentSite(r).equals(send.getId())){
					return r;
				}else{
					return getParentSite(r);
				}
			}else if(receive.getSiteType()==2){
				if(r.equals(send.getId())){
					return receive.getId();
				}else{
					return r;
				}
			}else if(receive.getSiteType()==1){
				return receive.getId();
			}
		}
		return -2L;
	}

	

	/**
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-2-4 下午5:56:23
	 * @param id
	 * @return
	 */
	private Long getParentSite(Long id) {
		return authOrganizationService.findById(id).getOrderId();
	}

	@RequestMapping(value = "/send/sendOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String sendOrder(String siteName, String ewbNo) {
		AuthOrganization ao = authOrganizationService.findByName(siteName);
		if (ao == null)
			return ResponseHelper.buildErrorResp("下一网点不存在！");
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		return ResponseHelper.getJson(oe);
	}
	
	@RequestMapping(value = "/receive/receiveOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String receiveOrder(String siteName, String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) {
		AuthOrganization ao = authOrganizationService.findByName(siteName);
		if (ao == null)
			return ResponseHelper.buildErrorResp("上一网点不存在！");
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		String cBy = (String) req.getSession()
				.getAttribute("loginSiteName");
		oe.setLoginSiteName(cBy);
		return ResponseHelper.getJson(oe);
	}
	
	@RequestMapping(value = "/dispatch/dispatchOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String dispatchOrder(String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) {
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		String cBy = (String) req.getSession()
				.getAttribute("loginSiteName");
		List<OptTrail> ot = optTrailServiceImpl.getTrailByEwbNo(ewbNo);
		if(ot.isEmpty()){
			return ResponseHelper.buildErrorResp("该运单还在寄件网点，不可派送");
		}
		if(ot.get(0).getNextSite().equals(cBy)&&ot.get(0).getState()==2){
			OptOrder o = siteOrderService.findById(oe.getOrderId());
			oe.setLoginSiteName(o.getReceiveCustomer());
			return ResponseHelper.getJson(oe);
		}else{
			return ResponseHelper.buildErrorResp("该运单还在运输途中，不可派送");
		}
	}
	
	@RequestMapping(value = "/sign/signOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String signOrder(String ewbNo, int signType,HttpServletRequest req,
			HttpServletResponse resp) {
		OptEwb oe = optEwbService.findById(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		String cBy = (String) req.getSession()
				.getAttribute("loginSiteName");
		List<OptTrail> ot = optTrailServiceImpl.getTrailByEwbNo(ewbNo);
		if(ot.isEmpty()){
			return ResponseHelper.buildErrorResp("该运单不是派送中，不可签收");
		}
		if(ot.get(0).getTopSite().equals(cBy)&&ot.get(0).getState()==3){
			OptOrder o = siteOrderService.findById(oe.getOrderId());
			oe.setLoginSiteName(o.getReceiveCustomer());
			oe.setSendSiteId(Long.valueOf(signType));
			return ResponseHelper.getJson(oe);
		}else{
			return ResponseHelper.buildErrorResp("该运单不是派送中，不可签收");
		}
	}
	
	@RequestMapping(value = "/sign/upload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String signUpload(String ewbNo,int signType, HttpServletRequest req,
			HttpServletResponse resp) {
		// 修改运单状态 和 上传运单轨迹
		OptEwb oe = new OptEwb();
		oe.setEwbNo(ewbNo);
		if(signType==0){
			oe.setState(4);
		}else{
			oe.setState(5);
		}
		optEwbService.update(oe);
		OptTrail ot = new OptTrail();
		if (null != req.getSession().getAttribute("loginSiteId")) {
			Long By = Long.valueOf((Integer) req.getSession().getAttribute(
					"loginUserId"));
			String cBy = (String) req.getSession().getAttribute(
					"loginSiteName");
			ot.setCreateName(By);
			ot.setTopSite(cBy);
		}
		if(signType==0){
			ot.setState(4);
		}else{
			ot.setState(5);
		}
		oe = optEwbService.findById(ewbNo);
		OptOrder o = siteOrderService.findById(oe.getOrderId());
		ot.setNextSite(o.getReceiveCustomer());
		ot.setEwbNo(ewbNo);
		optTrailServiceImpl.insert(ot);
		return ResponseHelper.getJson("上传成功");
	}
	
	@RequestMapping(value = "/dispatch/upload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String dispatchUpload(String ewbNo, HttpServletRequest req,
			HttpServletResponse resp) {
		// 修改运单状态 和 上传运单轨迹
		OptEwb oe = new OptEwb();
		oe.setEwbNo(ewbNo);
		oe.setState(3);
		optEwbService.update(oe);
		OptTrail ot = new OptTrail();
		if (null != req.getSession().getAttribute("loginSiteId")) {
			String cBy = (String) req.getSession()
					.getAttribute("loginSiteName");
			ot.setTopSite(cBy);
			Long By = Long.valueOf((Integer) req.getSession().getAttribute(
					"loginUserId"));
			ot.setCreateName(By);
		}
		ot.setState(3);
		oe = optEwbService.findById(ewbNo);
		OptOrder o = siteOrderService.findById(oe.getOrderId());
		ot.setNextSite(o.getReceiveCustomer());
		ot.setEwbNo(ewbNo);
		optTrailServiceImpl.insert(ot);
		return ResponseHelper.getJson("上传成功");
	}

	@RequestMapping(value = "/send/upload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String sendUpload(String ewbNo, String siteName, HttpServletRequest req,
			HttpServletResponse resp) {
		// 修改运单状态 和 上传运单轨迹
		OptEwb oe = new OptEwb();
		oe.setEwbNo(ewbNo);
		oe.setState(1);
		optEwbService.update(oe);
		OptTrail ot = new OptTrail();
		if (null != req.getSession().getAttribute("loginSiteId")) {
			String cBy = (String) req.getSession()
					.getAttribute("loginSiteName");
			ot.setTopSite(cBy);
			Long By = Long.valueOf((Integer) req.getSession().getAttribute(
					"loginUserId"));
			ot.setCreateName(By);
		}
		ot.setState(1);
		ot.setNextSite(siteName);
		ot.setEwbNo(ewbNo);
		optTrailServiceImpl.insert(ot);
		return ResponseHelper.getJson("上传成功");
	}
	
	@RequestMapping(value = "/receive/upload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String receiveUpload(String ewbNo, String siteName, HttpServletRequest req,
			HttpServletResponse resp) {
		// 修改运单状态 和 上传运单轨迹
		OptEwb oe = new OptEwb();
		oe.setEwbNo(ewbNo);
		oe.setState(2);
		optEwbService.update(oe);
		OptTrail ot = new OptTrail();
		if (null != req.getSession().getAttribute("loginSiteId")) {
			String cBy = (String) req.getSession()
					.getAttribute("loginSiteName");
			ot.setNextSite(cBy);
			Long By = Long.valueOf((Integer) req.getSession().getAttribute(
					"loginUserId"));
			ot.setCreateName(By);
		}
		ot.setState(2);
		ot.setTopSite(siteName);
		ot.setEwbNo(ewbNo);
		optTrailServiceImpl.insert(ot);
		return ResponseHelper.getJson("上传成功");
	}

	@RequestMapping(value = "/order/order", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String order(String orderData, HttpServletRequest req,
			HttpServletResponse resp) {
		String[] arr = orderData.split(",");
		int count = 0;
		for (String s : arr) {
			OptOrder o = siteOrderService.findById(Long.parseLong(s));
			Long id = authOrganizationService.getSiteId(o
					.getReceiveCustomerAddressId());
			if (id == null) {
				count++;
				return ResponseHelper.buildErrorResp("第" + count
						+ "条数据的目的网点不存在，请联系客户重新下单！");
			}
			Date dt = new Date();
			Long time = dt.getTime();
			String ewbNo = time + "";
			ewbNo = ewbNo.substring(5, ewbNo.length());
			int a = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;// 产生1000-9999的随机数
			ewbNo = ewbNo + a;
			if(ewbNo.charAt(0)=='0'){
				ewbNo="1"+ewbNo.substring(1, ewbNo.length());
			}
			OptEwb ewb = new OptEwb();
			ewb.setEwbNo(ewbNo);
			ewb.setOrderId(o.getOrderId());
			ewb.setReceiveSiteId(id);
			if (null != req.getSession().getAttribute("loginSiteId")) {
				Long cBy = (Long) req.getSession().getAttribute("loginSiteId");
				ewb.setSendSiteId(cBy);
			}
			ewb.setState(0);
			int n = optEwbService.insert(ewb);
			if (n > 0) {
				/**
				 * 插入运单轨迹--收件
				 */
				OptTrail ot = new OptTrail();
				if (null != req.getSession().getAttribute("loginUserId")) {
					Long cBy = Long.valueOf((Integer) req.getSession()
							.getAttribute("loginUserId"));
					String c = (String) req.getSession().getAttribute(
							"loginSiteName");
					ewb.setSendSiteId(cBy);
					ot.setCreateName(cBy);
					ot.setNextSite(c);
					ot.setTopSite(o.getSendCustomer());
				}
				ot.setEwbNo(ewbNo);
				ot.setState(0);// 0收件-接单
				optTrailServiceImpl.insert(ot);
				count++;
			} else {
				break;
			}
		}
		if (arr.length - count == 0)
			return ResponseHelper.buildSuccessResp("总共勾选" + arr.length
					+ "条数据，成功了" + count + "条");
		else {
			return ResponseHelper.buildSuccessResp("总共勾选" + arr.length
					+ "条数据，前" + count + "条执行成功了");
		}
	}

	@RequestMapping(value = "/order/getOrders", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody String getOrders(String start, String end, int page,
			HttpServletRequest req, HttpServletResponse resp)
			throws ParseException {
		OptOrder o = new OptOrder();
		if (null != req.getSession().getAttribute("loginSiteId")) {
			Long cBy = (Long) req.getSession()
					.getAttribute("loginSiteId");
			o.setLoginSiteId(cBy);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.setStart(sdf.parse(start));
		o.setEnd(sdf.parse(end));
		List<OptOrder> A = siteOrderService.getOrderByPage(o, 20, page);
		int allow = siteOrderService.getCounts(o);
		PageBean pageBean = new PageBean(A, allow, 20, page);
		return ResponseHelper.getJson(pageBean);
	}
	
	@RequestMapping(value = "/follow/followOrder", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String followOrder(String ewbNo , int page) throws ParseException {
		List<OptTrail> A = optTrailServiceImpl.getTrailByEwbNo(ewbNo);
		PageBean pageBean = new PageBean(A,A.size(),10,page);
		return ResponseHelper.getJson(pageBean);
	}
	
	@RequestMapping(value = "/order/getOrderInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String getOrderInfo(Long id,  HttpServletRequest req,
			HttpServletResponse resp) {
		OptOrder o = siteOrderService.findById(id);
		return ResponseHelper.getJson(o);
	}
	
}
