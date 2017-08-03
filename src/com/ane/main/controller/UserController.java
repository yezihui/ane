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

import com.ane.main.domain.User;
import com.ane.main.service.IUserService;
import com.ane.util.CommonUtil;
import com.ane.util.PageBean;
import com.ane.util.PageCommon;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-12 下午3:11:30
 * @version 1.0
 */
@Controller
public class UserController extends ResourceResponseSupport {

	@Autowired
	private IUserService userService;
	
	
	/**
	 * 
	 * <p>方法描述：</p>
	 * @author : 叶嘉贤
	 * @date : 2017-1-12 下午3:12:23
	 * @param ao
	 * @returnUser 
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/save",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String userSave(User u,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		if(u.getUserId() == null || u.getUserId() ==0) {
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				u.setCreatedBy(cBy);
			}
			u.setCreatedTime(new Date());
			int n = userService.insert(u);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("用户新增成功！");
			else
				return ResponseHelper.buildErrorResp("用户新增失败！");
		}else{
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				u.setModifiedBy(cBy);
			}
			u.setModifiedTime(new Date());
			int n = userService.update(u);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("用户更改成功！");
			else
				return ResponseHelper.buildErrorResp("用户更改失败！");
		}
	}
	@RequestMapping(value = "/user/updatePsw",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String updatePsw(Integer id,HttpServletRequest req,HttpServletResponse resp) throws Exception {
			int n = userService.updatePsw(id);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("密码重置成功！");
			else
				return ResponseHelper.buildErrorResp("密码重置失败！");
		
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/user/getUsers", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getUsers(User u , int page) throws ParseException {
		CommonUtil c = new CommonUtil();
		u.setUserName(c.isNull(u.getUserName()));
		u.setEmployeeName(c.isNull(u.getEmployeeName()));
		u.setSiteName(c.isNull(u.getSiteName()));
		List<User> A = userService.getUserByPage(u,
				20, page);
		int allow = userService.getCounts();
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
	@RequestMapping(value = "/user/getUserName", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getUserName(Integer id ,String name) {
		if(id==null){
			User uname = userService.getUserName(name);
			return ResponseHelper.buildSuccessResp(uname==null?"0":"1");
		}else{
			User a = new User();
			a.setUserId(id);
			a.setUserName(name);
			User uname = userService.getUserNameAndId(a);
			User uuname = userService.getUserName(name);
			if(uname==null && uuname!=null){
				return ResponseHelper.buildSuccessResp("1");
			}
			return ResponseHelper.buildSuccessResp("0");
		}
	}
	
	@RequestMapping(value = "/user/getUserById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getUserById(Integer userId) {
		User ao = userService.selectUserById(userId);
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
	@RequestMapping(value = "/user/delUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delUser(Long id) {
		int ao = userService.delete(id);
		if(ao>0)
			return ResponseHelper.buildSuccessResp("用户删除成功！");
		else
			return ResponseHelper.buildSuccessResp("用户删除失败！");
	}
	
	/**
	 * demo
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/basic/userMgr")
	public ModelAndView userList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageCommon com = new PageCommon();
		mv.addObject("db", com);
		mv.setViewName("/basic/userMgr");
		return mv;
	}
}
