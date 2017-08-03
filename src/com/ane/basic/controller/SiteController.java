package com.ane.basic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.basic.dao.AreasDao;
import com.ane.basic.dao.CitiesDao;
import com.ane.basic.dao.ProvincesDao;
import com.ane.basic.domain.AuthOrganization;
import com.ane.basic.service.IAuthOrganizationService;
import com.ane.main.domain.Dept;
import com.ane.main.service.IDeptService;
import com.ane.util.DecodeUtils;
import com.ane.util.PageBean;
import com.ane.util.PinYinUtils;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

@Controller
public class SiteController extends ResourceResponseSupport {

	@Autowired
	private IAuthOrganizationService authOrganizationService;
	
	@Autowired
	private IDeptService deptService;
	
	@Resource
	private ProvincesDao provincesDao;
	
	@Resource
	private CitiesDao citiesDao;
	
	@Resource
	private AreasDao areasDao;

	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/basic/siteMgr")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/basic/siteMgr");
		return mv;
	}

	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/save",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody
	String save(AuthOrganization ao) throws Exception {
		Dept d = new Dept();
		d= copy(ao);
		if(ao.getId() == null || ao.getId() ==0) {
			deptService.insert(d);
			int n = authOrganizationService.insert(ao);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("网点新增成功！");
			else
				return ResponseHelper.buildErrorResp("网点新增失败！");
		}else{
			deptService.update(d);
			int n = authOrganizationService.update(ao);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("网点更改成功！");
			else
				return ResponseHelper.buildErrorResp("网点更改失败！");
		}
	}

	/**
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-18 下午4:10:20
	 * @param ao
	 * @return
	 */
	private Dept copy(AuthOrganization ao) {
		Dept d = new Dept();
		d.setDeptName(ao.getName());
		if(ao.getId()!=null)
			d.setDeptId(new Long(ao.getId()).intValue());
		d.setDeptSpell(ao.getShortName());
		d.setDeptOrder(ao.getSiteOrder());
		if(ao.getSiteType()==1)
			d.setParentDeptId(1);
		else{
			d.setParentDeptId(2);
		}
		d.setDescription("这是一个网点");
		return d;
	}

	/**
	 * 
	 * <p>方法描述：按条件查询网点数据</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:16:31
	 * @param id
	 * @param name
	 * @param orderId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/site/getSites", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getSites(Long id, String name, Long orderId, int page) {
		AuthOrganization ao = new AuthOrganization();
		if (id != null && id > 0) {
			ao.setId(id);
		}
		if (name != null && name!="") {
			ao.setName(name);
		}
		if (orderId != null && orderId > 0) {
			ao.setOrderId(orderId);
		}
		List<AuthOrganization> A = authOrganizationService.getSiteByPage(ao,
				20, page);
		int allow = authOrganizationService.getCounts();
		PageBean pageBean = new PageBean(A,allow,20,page);
		return ResponseHelper.getJson(pageBean);
	}
	
	@RequestMapping(value = "/site/getAllSites", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getAllSites() {
		AuthOrganization ao = new AuthOrganization();
		List<AuthOrganization> A = authOrganizationService.getSiteByPage(ao,
				50, 1);
		int allow = authOrganizationService.getCounts();
		PageBean pageBean = new PageBean(A,allow,50,1);
		return ResponseHelper.getJson(pageBean);
	}
	
	/**
	 * 
	 * <p>方法描述：双击网点数据 查询网点详情</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:18:52
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value = "/site/getSiteById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getSiteById(Long siteId) {
		AuthOrganization ao = authOrganizationService.findById(siteId);
		ao.setProvince(provincesDao.getProvinceName(ao.getProvince()));
		ao.setCity(citiesDao.getCityName(ao.getCity()));
		ao.setRegion(areasDao.getAreaName(ao.getRegion()));
		return ResponseHelper.getJson(ao);
	}
	
	@RequestMapping(value = "/site/getOrderSite", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getOrderSite(Integer type) {
		AuthOrganization aoo = new AuthOrganization();
		aoo.setSiteType(type);
		List<AuthOrganization> ao = authOrganizationService.findByType(aoo);
		return ResponseHelper.getJson(ao);
	}
	
	@RequestMapping(value = "/site/getPy", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getPy(String name) {
		PinYinUtils pinYinUtils = new PinYinUtils();
		name = DecodeUtils.encodeStr(name);
		String nameSpell = pinYinUtils.String2Alpha(name);
		return ResponseHelper.buildSuccessResp(nameSpell);
	}
	
	@RequestMapping(value = "/site/getSiteOrder", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getSiteOrder() {
		int ao = authOrganizationService.getMaxOrder();
		return ResponseHelper.getJson(ao+1);
	}
	
	/**
	 * 
	 * <p>方法描述：删除某个网点</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:20:34
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/site/delSite", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delSite(Long id) {
		int ao = authOrganizationService.delete(id);
		if(ao>0)
			return ResponseHelper.buildSuccessResp("网点删除成功！");
		else
			return ResponseHelper.buildSuccessResp("网点删除失败！");
	}

}
