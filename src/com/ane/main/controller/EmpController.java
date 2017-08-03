/**
 * 
 */
package com.ane.main.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.ane.main.domain.Employee;
import com.ane.main.service.IEmployeeService;
import com.ane.util.PageBean;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-12 下午4:04:30
 * @version 1.0
 */
@Controller
public class EmpController extends ResourceResponseSupport {
	
	@Autowired
	private IEmployeeService employeeService;
	/**
	 * 
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:12:23
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/emp/save",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String save(Employee e,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = null;
		try {
			date = sdf.parse(e.geteTime());
		} catch (ParseException ee) {
			ee.printStackTrace();
		}
		if(date != null){
			e.setEntryTime(date);
		}
		if(e.getId() == null || e.getId() ==0) {
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				e.setCreater(Long.valueOf(cBy));
			}
			e.setCreated(new Date());
			int n = employeeService.insert(e);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("员工新增成功！");
			else
				return ResponseHelper.buildErrorResp("员工新增失败！");
		}else{
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				e.setUpdater(Long.valueOf(cBy));
			}
			e.setUpdated(new Date());
			int n = employeeService.update(e);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("员工更改成功！");
			else
				return ResponseHelper.buildErrorResp("员工更改失败！");
		}
	}
	
	@RequestMapping(value = "/emp/getEmpByDept", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getUserByDept( int deptId) throws ParseException {
		List<Employee> A = employeeService.getEmpByDept(deptId);
		return ResponseHelper.getJson(A);
	}
	/**
	 * 
	 * <p>方法描述：按条件查询用户数据</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:16:31
	 * @param id
	 * @param name
	 * @param orderId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/emp/getAllEmps", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getAllEmps() {
		List<Employee> E = employeeService.getAllEmployees();
		return ResponseHelper.getJson(E);
	}
	
	@RequestMapping(value = "/emp/getEmps", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getEmps(Employee u , int page) throws ParseException {
		if(u.getName()=="")
			u.setName(null);
		if(u.getSiteId()=="")
			u.setSiteId(null);
		List<Employee> A = employeeService.getEmployeeByPage(u,
				20, page);
		int allow = employeeService.getCounts(u,20, page);
		PageBean pageBean = new PageBean(A,allow,20,page);
		return ResponseHelper.getJson(pageBean);
	}
	
	/**
	 * 
	 * <p>方法描述：双击用户数据 查询用户详情</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:18:52
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value = "/emp/getEmpById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getSiteById(Integer userId) {
		Employee ao = employeeService.findById(userId);
		return ResponseHelper.getJson(ao);
	}
	
	/**
	 * 
	 * <p>方法描述：删除某个用户</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:20:34
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/delEmp", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delUser(Integer id) {
		int ao = employeeService.delete(id);
		if(ao>0)
			return ResponseHelper.buildSuccessResp("员工删除成功！");
		else
			return ResponseHelper.buildSuccessResp("员工删除失败！");
	}
	
	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/basic/employeeMgr")
	public ModelAndView userList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/basic/employeeMgr");
		return mv;
	}
}
