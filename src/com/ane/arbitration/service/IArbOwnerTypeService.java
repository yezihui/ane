/**
 * 
 */
package com.ane.arbitration.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ane.arbitration.domain.ArbitrationOwnerType;
import com.ane.util.PageBean;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-9 下午5:05:18
 * @version 1.0
 */
public interface IArbOwnerTypeService {

	boolean add(ArbitrationOwnerType owner);
	
	boolean update(ArbitrationOwnerType owner);
	
	boolean delete(Long id);
	
	ArbitrationOwnerType findById(Long id);
	
	List<ArbitrationOwnerType> findAll();
	
	List<ArbitrationOwnerType> getOwnerTypes(String applyType);
	
	boolean findByOwnName(String code);
	
	boolean findByOwnCode(String order);
	
	boolean findByOwnOrder(String order);
	
	ArbitrationOwnerType getMaxOrder();
	
	//分页获取所有责任信息
	PageBean getArbOwnerByPage(@Param("aot")ArbitrationOwnerType aot ,@Param("pageSize")int pageSize,@Param("offset")int offset);
	
}
