/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationOwnerTypeDao;
import com.ane.arbitration.domain.ArbitrationOwnerType;
import com.ane.arbitration.service.IArbOwnerTypeService;
import com.ane.util.PageBean;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 下午5:05:35
 * @version 1.0
 */
@Service("arbitrationOwnerTypeService")
public class ArbOwnerTypeServiceImpl implements IArbOwnerTypeService {
	@Resource
	private ArbitrationOwnerTypeDao arbitrationOwnerTypeRepository;
	
	
	
	//查询所有的责任类型
	public List<ArbitrationOwnerType> findAll(){
		List<ArbitrationOwnerType> list = arbitrationOwnerTypeRepository.findAll();
		return list;
	}
	
	//通过id查找责任类型具体信息
	public ArbitrationOwnerType findById(Long id){
		ArbitrationOwnerType arbitrationOwnerType = arbitrationOwnerTypeRepository.findById(id);
		return arbitrationOwnerType;
	}
	
	//通过条件查找部分责任类型 
	public List<ArbitrationOwnerType> getOwnerTypes(String applyType){
		List<ArbitrationOwnerType> list = arbitrationOwnerTypeRepository.getOwnerTypes(applyType);
		return list;
	}
	
	public PageBean getArbOwnerByPage(ArbitrationOwnerType aot,int pageSize, int page){
		int offset = PageBean.countOffset(pageSize, page);
		aot.setPageSize(pageSize);
		aot.setOffset(offset);
		List<ArbitrationOwnerType> list = arbitrationOwnerTypeRepository.getArbOwnerByPage(aot);
		int allRow = this.arbitrationOwnerTypeRepository.getArbitrationNum();
		PageBean pageBean = new PageBean(list,allRow,pageSize,page);
		return pageBean;
	}
	
	//通过id查找责任类型具体信息
	public boolean delete(Long id){
		 arbitrationOwnerTypeRepository.delete(id);
		 return true;
	}
	
	//添加责任类型
	public boolean add(ArbitrationOwnerType aot){
		arbitrationOwnerTypeRepository.add(aot);
		 return true;
	}
	
	//更改责任Lexi
	public boolean update(ArbitrationOwnerType aot){
		arbitrationOwnerTypeRepository.update(aot);
		 return true;
	}
	
	public boolean findByOwnName(String name){
		Long l =  arbitrationOwnerTypeRepository.findByOwnName(name);
		if(l!=null){
			System.out.println("true");
			return true;
		}
		else{
			System.out.println("false");
			return false;
		}
	}
	
	public boolean findByOwnCode(String code){
		Long lcode =  arbitrationOwnerTypeRepository.findByOwnCode(code);
		if(lcode!=null)
			return true;
		else
			return false;
	}
	
	public boolean findByOwnOrder(String order){
		Long lorder =  arbitrationOwnerTypeRepository.findByOwnOrder(order);
		if(lorder!=null)
			return true;
		else
			return false;
	}
	
	public ArbitrationOwnerType getMaxOrder(){
		return arbitrationOwnerTypeRepository.getMaxOrder();
	}

	

	
}
