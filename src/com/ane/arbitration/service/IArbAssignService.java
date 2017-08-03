/**
 * 
 */
package com.ane.arbitration.service;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationAssignment;
import com.ane.util.PageBean;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-10 下午4:32:38
 * @version 1.0
 */
public interface IArbAssignService {


	boolean add(ArbitrationAssignment arbitrationAssignment,int gender);

	boolean update(ArbitrationAssignment arbitrationAssignment);

	boolean delete(Long id);

	ArbitrationAssignment findById(long id);
	
	Long  findByName(String name);
	
	List<ArbitrationAssignment> search();
	
	List<ArbitrationAssignment> findAll();
	
	//分页获取所有分配信息
	PageBean getAssignInfo(ArbitrationAssignment aa ,int pageSize,int offset);
}
