/**
 * 
 */
package com.ane.main.service;

import java.util.List;

import com.ane.main.domain.Dept;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-16 下午3:50:34
 * @version 1.0
 */
public interface IDeptService {

	Dept findById(Integer id);

    public int insert(Dept record);
	
	List<Dept> getDeptByPage(Dept record,Integer pageSize,Integer offset);
	
	List<Dept> getAllDepts();
	
	int getCounts();
	
	int getMaxOrder();
	
	int delete(int id);
	
	int update(Dept record);
}
