/**
 * 
 */
package com.ane.arbitration.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.ane.arbitration.domain.ArbitrationAssignment;
import com.ane.arbitration.domain.ArbitrationCenterHandler;
import com.ane.arbitration.domain.ArbitrationHandler;
import com.ane.arbitration.service.IArbAssignService;
import com.ane.arbitration.service.IArbitrationCenterHandlerService;
import com.ane.arbitration.service.IArbitrationHandlerService;
import com.ane.arbitration.service.IArbitrationService;
import com.ane.arbitration.service.IArbitrationTypeService;
import com.ane.main.domain.Employee;
import com.ane.main.service.IEmployeeService;
import com.ane.util.PageBean;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：仲裁控制层</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-16 下午5:08:18
 * @version 1.0
 */
@Controller
public class ArbitrationController extends ResourceResponseSupport {

	@Autowired
	private IArbitrationService arbService;
	
	@Autowired
	private IArbAssignService arbitrationAssignmentService;
	@Resource
	private IArbitrationTypeService arbitrationTypeService;
	@Resource
	private IArbitrationHandlerService arbHandlerService;
	@Resource
	private IArbitrationCenterHandlerService arbCenterHandlerService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value = "/arb/declare")
	public ModelAndView declare() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/declare");
		return mv;
	}
	
	@RequestMapping(value = "/arb/audit")
	public ModelAndView audit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/audit");
		return mv;
	}
	
	@RequestMapping(value = "/arb/search")
	public ModelAndView search() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/search");
		return mv;
	}
	
	@RequestMapping(value = "/arb/center")
	public ModelAndView center() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/center");
		return mv;
	}
	
	@RequestMapping(value = "/arb/apply")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/apply");
		return mv;
	}
	
	@RequestMapping(value = "/arb/arbOrder")
	public ModelAndView order() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/arbitration/arbOrder");
		return mv;
	}
	
	@RequestMapping(value = "/arbDeclare/getArbtrations", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getArbtrations(Arbitration at ,int page, HttpServletRequest req,
			HttpServletResponse resp){
		if((Integer) req.getSession().getAttribute(
				"loginUserId")==null){
			return ResponseHelper.buildErrorResp("请先登录！");
		}
		Long By = Long.valueOf((Integer) req.getSession().getAttribute(
				"loginUserId"));
		if(at.getState()!=null){
			if(at.getState().equals(Arbitration.YFZX)){
				at.setHandler(By);
			}else if(at.getState().equals(Arbitration.YJD)){
				at.setDelay(Integer.parseInt(Arbitration.SPBTG));
				at.setHandler(By);
			}
		}
		//按运单号查询·
		if(at.isChk()){
			PageBean pageBean = arbService.searchByEwbNo(at , 20, page);
			return ResponseHelper.getJson(pageBean);
		}
		else {
			if(at.getHandName()!=null && at.getHandName()!=""){
				Employee e = employeeService.findByName(at.getHandName());
				if(e!=null)
					at.setHandler(Long.valueOf(e.getId()));
				else{
					return ResponseHelper.buildErrorResp("处理人不存在，请正确输入");
				}
			}
			//按条件查询
			if(at.isCover()){
				at.setOwnerSiteName(at.getApplySiteName());
				at.setApplySiteName(null);
			}
			if(at.getApplyType()!=null && at.getApplyType()!="")
				at.setApplyType(arbitrationTypeService.findById(Long.valueOf(at.getApplyType())).getName());
			PageBean pageBean = arbService.getArbitrationByPage(at , 20, page);
			return ResponseHelper.getJson(pageBean);
		}
	}
	
	@RequestMapping(value = "/arbOrder/updateArbInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateArbInfo(String orderData,HttpServletRequest req,
			HttpServletResponse resp){
		boolean flag = false;
		String [] arr= orderData.split(",");
		for(int i = 0;i < arr.length; i++){
			//接单 状态  = 2
			Long id = Long.parseLong(arr[i]);
			Arbitration arb = new Arbitration();
			arb.setId(id);
			arb.setStatus(Arbitration.YJD);
			boolean b = arbService.updateArbInfo(arb);
			if(b==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationHandler hand = new ArbitrationHandler();
				hand.setArbitrationId(id);
				hand.setReason("接单");
				hand.setProvStatus(Arbitration.YFZX);
				hand.setStatus(Arbitration.YJD);
				if((Integer) req.getSession().getAttribute(
						"loginUserId")!=null){
					Integer handler = (Integer) req.getSession().getAttribute(
							"loginUserId");
					hand.setHandler(Long.valueOf(handler));
				}
				arbHandlerService.add(hand);
				flag=true;
			}
			if(b==false){
				flag = false;
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value = "/arbOrder/returnArbInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String returnArbInfo(String orderData){
		boolean flag = false;
		String [] arr= orderData.split(",");
		for(int i = 0;i < arr.length; i++){
			//接单 状态  = 2
			Long id = Long.parseLong(arr[i]);
			Arbitration arb = new Arbitration();
			arb.setId(id);
			arb.setStatus(Arbitration.BYJD);
			boolean b = arbService.updateArbInfo(arb);
			if(b==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationHandler hand = new ArbitrationHandler();
				Arbitration at = arbService.findById(id);
				hand.setArbitrationId(id);
				hand.setReason("不予接单");
				if(at.getStatus().equals("已分中心"))
					hand.setProvStatus(Arbitration.YFZX);
				else if(at.getStatus().equals("已接单，处理中"))
					hand.setProvStatus(Arbitration.YJD);
				else if(at.getStatus().equals("仲裁审批不通过"))
					hand.setProvStatus(Arbitration.SPBTG);
				hand.setStatus(Arbitration.BYJD);
				arbHandlerService.add(hand);
				flag=true;
			}
			if(b==false){
				flag = false;
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	@RequestMapping(value = "/arbOrder/noArbInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String noArbInfo(String orderData){
		boolean flag = false;
		String [] arr= orderData.split(",");
		for(int i = 0;i < arr.length; i++){
			//接单 状态  = 2
			Long id = Long.parseLong(arr[i]);
			Arbitration arb = new Arbitration();
			arb.setId(id);
			arb.setStatus(Arbitration.SPBTG);
			boolean b = arbService.updateArbInfo(arb);
			if(b==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationHandler hand = new ArbitrationHandler();
				Arbitration at = arbService.findById(id);
				hand.setArbitrationId(id);
				hand.setReason("仲裁审批不通过，请重新进行处理");
				if(at.getStatus().equals("已分中心"))
					hand.setProvStatus(Arbitration.YFZX);
				else if(at.getStatus().equals("已接单，处理中"))
					hand.setProvStatus(Arbitration.YJD);
				else if(at.getStatus().equals("中心处理中"))
					hand.setProvStatus(Arbitration.ZXCLZ);
				hand.setStatus(Arbitration.SPBTG);
				arbHandlerService.add(hand);
				flag=true;
			}
			if(b==false){
				flag = false;
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value = "/arbOrder/yesArbInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String yesArbInfo(String orderData){
		boolean flag = false;
		String [] arr= orderData.split(",");
		for(int i = 0;i < arr.length; i++){
			//接单 状态  = 2
			Long id = Long.parseLong(arr[i]);
			Arbitration arb = new Arbitration();
			arb.setId(id);
			arb.setStatus(Arbitration.JSTG);
			boolean b = arbService.updateArbInfo(arb);
			if(b==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationHandler hand = new ArbitrationHandler();
				hand.setArbitrationId(id);
				hand.setReason("仲裁审批通过");
				hand.setProvStatus(Arbitration.CLWB);
				hand.setStatus(Arbitration.JSTG);
				arbHandlerService.add(hand);
				flag=true;
			}
			if(b==false){
				flag = false;
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value = "/arbDeclare/getArbInfo" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getArbInfo(long id){
		Arbitration aot = arbService.findById(id);
		return ResponseHelper.getJson(aot);
	}
	
	@RequestMapping(value="/arbDeclare/saveArbInfo", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveArbInfo(Arbitration arb,HttpServletRequest req,
			HttpServletResponse resp){
		boolean flag = false;
		if(null != arb){
			//新增
			if(null == arb.getId()){
				Arbitration oe = arbService.findByEwbNo(arb.getEwbNo());
				arb = copy(arb,oe);
				flag = arbService.add(getInt(),arb,req,resp);
			}
			else{
				flag = arbService.update(arb);
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	@RequestMapping(value="/arbCenter/saveCenter", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveCenter(ArbitrationCenterHandler ch,HttpServletRequest req,
			HttpServletResponse resp){
		boolean flag = false;
		if(null != ch){
			Arbitration arb = arbService.findById(ch.getArbitrationId());
			arb.setStatus(ch.getState()+"");
			flag = arbService.updateArbInfo(arb);
			if(flag==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationHandler hand = new ArbitrationHandler();
				hand.setArbitrationId(ch.getArbitrationId());
				hand.setReason(ch.getReason());
				hand.setProvStatus(Arbitration.YJD);
				hand.setStatus(Arbitration.CLWB);
				flag=arbHandlerService.add(hand);
			}
			if(flag==true){
				//如果接单成功 需要添加 一条 处理信息 
				ArbitrationCenterHandler hand = new ArbitrationCenterHandler();
				//责任网点
				hand.setArbitrationId(ch.getArbitrationId());
				hand.setOrganizationType(ch.getOrganizationType());
				hand.setJoinOrganization(ch.getJoinOrganization());
				hand.setDutyType(ch.getDutyType());
				hand.setFeeMoney(ch.getFeeMoney());
				hand.setFeeProject(ch.getFeeProject());
				flag = arbCenterHandlerService.add(hand);
				hand = new ArbitrationCenterHandler();
				hand.setArbitrationId(ch.getArbitrationId());
				hand.setOrganizationType(ch.getOrganizationType1());
				hand.setJoinOrganization(ch.getJoinOrganization1());
				hand.setFeeMoney(ch.getFeeMoney1());
				hand.setFeeProject(ch.getFeeProject1());
				flag = arbCenterHandlerService.add(hand);
			}
		}
		if(flag){
			return ResponseHelper.buildSuccessResp("操作成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("操作失败！");
	}
	
	/**
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-2-20 下午2:06:14
	 * @param arb
	 * @param oe
	 * @return
	 */
	private Arbitration copy(Arbitration arb, Arbitration oe) {
		arb.setSendTime(oe.getSendTime());
		arb.setSignName(oe.getSignName());
		arb.setProductType(oe.getProductType());
		arb.setBeginStation(oe.getBeginStation());
		arb.setEndStation(oe.getEndStation());
		arb.setPiece(oe.getPiece());
		arb.setCalcWeight(oe.getCalcWeight());
		arb.setProductName(oe.getProductName());
		arb.setPackType(oe.getPackType());
		arb.setSender(oe.getSender());
		arb.setSendPhone(oe.getSendPhone());
		arb.setSendAddress(oe.getSendAddress());
		arb.setRecipient(oe.getRecipient());
		arb.setRecipientAddress(oe.getRecipientAddress());
		arb.setRecipientPhone(oe.getRecipientPhone());
		return arb;
	}

	public int getInt(){
		List<ArbitrationAssignment> list = this.arbitrationAssignmentService
				.findAll();
		int i = 0;
		if (list.size() == 0)
			i = 0;
		else if (list.get(0).getApplyAddresses() != null) {
			i = 1;
		} else if (list.get(0).getOwnerAddresses() != null) {
			i = 2;
		}
		return i;
	}
	
	@RequestMapping(value="/arbDeclare/delArbInfo", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delArbInfo(Long id){
		boolean flag = arbService.delete(id);
		if(flag){
			return ResponseHelper.buildSuccessResp("数据删除成功！");
		}
		//异常时返回的msg应该是封装后的 对应提示业务异常的message  这里简单返回提示
		return ResponseHelper.buildErrorResp("数据删除失败！");
	}
	
	@RequestMapping(value = "/ewb/ewbInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String ewbInfo(String ewbNo ,HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		if (ewbNo == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		Arbitration oe = arbService.findByEwbNo(ewbNo);
		if (oe == null)
			return ResponseHelper.buildErrorResp("该运单号不存在！");
		else{
			oe.setApplySiteName((String) req.getSession()
					.getAttribute("loginSiteName"));
			if(oe.getApplySiteName()==null){
				return ResponseHelper.buildErrorResp("请先登录！");
			}
			oe.setSendTime(sdf.parse(sdf.format(oe.getSendTime())));
		}
		return ResponseHelper.getJson(oe);
	}
	
	@RequestMapping(value = "/arb/getAllInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String getAllInfo( Long id ,HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		List<ArbitrationHandler> ah= arbHandlerService.getInfo(id);
		List<ArbitrationCenterHandler> ahc = arbCenterHandlerService.getInfo(id);
		for(int i=0;i<ahc.size();i=i+2){
			ArbitrationCenterHandler ch = ahc.get(i);
			for(int j=0;j<ah.size() ;j++){
				ArbitrationHandler hand = ah.get(j);
				if(ch.getCreated().before(hand.getCreated())){
					String str = "";
					if(ch.getOrganizationType()==0){
						str+="责任网点["+ch.getJoinOrganization()+"]";
					}else if(ch.getOrganizationType()==1){
						str+="申报网点["+ch.getJoinOrganization()+"]";
					}
					if(ch.getFeeProject()==0){
						str+="被收仲裁罚款["+ch.getFeeMoney()+"]元";
					}else if(ch.getFeeProject()==1){
						str+="被付仲裁奖励["+ch.getFeeMoney()+"]元";
					}
					ah.get(j).setCenterHandOne(str);
					
					str = "";
					ch = ahc.get(i+1);
					if(ch.getOrganizationType()==0){
						str+="责任网点["+ch.getJoinOrganization()+"]";
					}else if(ch.getOrganizationType()==1){
						str+="申报网点["+ch.getJoinOrganization()+"]";
					}
					if(ch.getFeeProject()==0){
						str+="被收仲裁罚款["+ch.getFeeMoney()+"]元";
					}else if(ch.getFeeProject()==1){
						str+="被付仲裁奖励["+ch.getFeeMoney()+"]元";
					}
					ah.get(j).setCenterHandTwo(str);
					break;
				}
			}
		}
		return ResponseHelper.getJson(ah);
	}
}
