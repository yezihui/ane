/**
 * 
 */
package com.ane.main.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.main.domain.Dept;
import com.ane.main.service.IDeptService;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;



 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-16 下午4:00:40
 * @version 1.0
 */
@Controller
public class DeptController extends ResourceResponseSupport {

	@Autowired
	private IDeptService deptService;
	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/basic/deptMgr")
	public ModelAndView deptList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/basic/deptMgr");
		return mv;
	}
	
	@RequestMapping(value = "/dept/getDepts", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getDepts() throws ParseException {
		List<Dept> A = deptService.getAllDepts();
		return ResponseHelper.getJson(A);
	}
	
	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dept/saveDept", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String save(Dept de,HttpServletRequest req,HttpServletResponse resp) throws ParseException {
		if(de.getDeptId() == null || de.getDeptId() ==0) {
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				de.setCreateBy(cBy);
			}
			de.setCreateTime(new Date());
			int n = deptService.insert(de);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("部门新增成功！");
			else
				return ResponseHelper.buildErrorResp("部门新增失败！");
		}else{
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				de.setUpdateBy(cBy);
			}
			de.setUpdateTime(new Date());
			int n = deptService.update(de);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("部门更改成功！");
			else
				return ResponseHelper.buildErrorResp("部门更改失败！");
		}
	}
	
	@RequestMapping(value = "/basic/getDeptById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getDeptById(int id) {
		Dept de = deptService.findById(id);
		return ResponseHelper.getJson(de);
	}
	
	@RequestMapping(value = "/dept/getDeptOrder", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getSiteOrder() {
		int ao = deptService.getMaxOrder();
		return ResponseHelper.getJson(ao+1);
	}
	
	@RequestMapping(value = "/dept/delDept", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delDept(int id) {
		int n = deptService.delete(id);
		if (n > 0)
			return ResponseHelper.buildSuccessResp("部门删除成功！");
		else
			return ResponseHelper.buildErrorResp("部门删除失败！");
	}
}
