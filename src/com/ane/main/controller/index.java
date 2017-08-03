package com.ane.main.controller;

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
import com.ane.main.domain.Dept;
import com.ane.main.domain.Employee;
import com.ane.main.domain.User;
import com.ane.main.service.IDeptService;
import com.ane.main.service.IEmployeeService;
import com.ane.main.service.IUserService;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;


@Controller
public class index extends ResourceResponseSupport {
	@Autowired
	private IDeptService deptService;
	@Resource
	private IUserService userService;
	@Resource
	private IEmployeeService employeeService;
	@Resource
	private IAuthOrganizationService authOrganizationService;
	
	@RequestMapping("")
	public String in(HttpServletRequest request,HttpServletResponse response){
		return "/login";
	}
	
	@RequestMapping(value="/user/load", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String inn(User user,HttpServletRequest req,HttpServletResponse resp){
		User u = userService.login(user);
		if(u==null){
			return ResponseHelper.buildErrorResp("用户登录失败！");
		}
		if(u.getUserName()!=null) {
			req.getSession().setAttribute("loginUserName", u.getUserName());
			req.getSession().setAttribute("loginUserId", u.getUserId());
		}
		Employee e = employeeService.findById(u.getEmployeeId());
		Dept o = deptService.findById(Integer.parseInt(e.getSiteId()));
		if(o!=null) {
			req.getSession().setAttribute("loginSiteName", o.getDeptName());
			AuthOrganization ao = authOrganizationService.findByDeptId(Long.valueOf(o.getDeptId()));
			req.getSession().setAttribute("loginSiteId", ao.getId());
		}
		return ResponseHelper.buildSuccessResp("用户登录成功！");
	}
	
	/**
	 * demo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db",com);
		mv.setViewName("/system/home");
		return mv;
	}
	
	
}
