package com.ane.arbitration.dao;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationAssignment;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-10 下午4:31:43
 * @version 1.0
 */
public interface ArbitrationAssignDao {

	int add(ArbitrationAssignment arbitrationAssignment);

	void update(ArbitrationAssignment arbitrationAssignment);

	int delete(long id);

	ArbitrationAssignment findById(long id);
	
	Long  findByName(String name);
	
	List<ArbitrationAssignment> search();
	
	List<ArbitrationAssignment> findAll();
	
	List<ArbitrationAssignment> getAssignInfo(ArbitrationAssignment aa );
	
	int getArbitrationNum();
}
