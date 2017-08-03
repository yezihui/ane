/**
 * 
 */
package com.ane.order.service;

import java.util.List;

import com.ane.order.domain.OptRetreat;

/**
 * <p>
 * 功能描述：
 * </p>
 * 
 * @author : 叶嘉贤
 * @date : 2017-3-13 上午10:16:21
 * @version 1.0
 */
public interface IOptRetreatService {

	boolean delete(Long id);

	int insert(OptRetreat record);

	OptRetreat findByEwb(String id);

	boolean update(OptRetreat record);
	
	List<OptRetreat> findAll(OptRetreat record);
}
