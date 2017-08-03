package com.ane.basic.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ane.basic.domain.Dictionary;
import com.ane.basic.domain.DictionaryType;
import com.ane.basic.service.IDictionaryService;
import com.ane.util.ResourceResponseSupport;
import com.ane.util.ResponseHelper;

@Controller
public class DictionaryController extends ResourceResponseSupport {

	@Resource
	private IDictionaryService dictionaryService;
	
	@RequestMapping(value="/dict/findByTypeId", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String findByTypeId(Long dateType){
		List<Dictionary> list = dictionaryService.findByTypeId(dateType);
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "/basic/dictMgr")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/basic/dictMgr");
		return mv;
	}
	
	@RequestMapping(value = "basic/getDictTypes", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getDictTypes(){
		List<DictionaryType> list = dictionaryService.findAllType();
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "basic/getDictById", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getDictById(Long id){
		List<Dictionary> list= dictionaryService.findByTypeId(id);
		return ResponseHelper.getJson(list);
	}
	
	@RequestMapping(value = "/basic/getInfoById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getInfoById(Long userId) {
		Dictionary ao = dictionaryService.getInfoById(userId);
		return ResponseHelper.getJson(ao);
	}
	
	@RequestMapping(value = "/basic/saveDict",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String saveDict(Dictionary d,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		if(d.getId() == null || d.getId() ==0) {
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				d.setCreater(Long.valueOf(cBy));
			}
			d.setCreated(new Date());
			int n = dictionaryService.insert(d);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("数据字典新增成功！");
			else
				return ResponseHelper.buildErrorResp("数据字典新增失败！");
		}else{
			if(null!=req.getSession().getAttribute("loginUserId")){
				Integer cBy = (Integer) req.getSession().getAttribute("loginUserId");
				d.setUpdater(Long.valueOf(cBy));
			}
			d.setUpdated(new Date());
			int n = dictionaryService.update(d);
			if (n > 0)
				return ResponseHelper.buildSuccessResp("数据字典更改成功！");
			else
				return ResponseHelper.buildErrorResp("数据字典更改失败！");
		}
	}
	
	@RequestMapping(value = "/basic/delDict", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delUser(Long id) {
		int ao = dictionaryService.delete(id);
		if(ao>0)
			return ResponseHelper.buildSuccessResp("数据字典删除成功！");
		else
			return ResponseHelper.buildSuccessResp("数据字典删除失败！");
	}
}
