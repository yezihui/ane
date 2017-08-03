package com.ane.arbitration.dao;

import java.util.List;

import com.ane.arbitration.domain.ArbitrationHandler;

/** 
 * 说明： 仲裁模块接口
 * 创建人：View
 * 创建时间：2016-09-02
 * @version
 */
public interface ArbitrationHandlerDao{

	/**新增
	 * @param arbitrationHandler
	 */
	public int add(ArbitrationHandler arbitrationHandler);
	
	/**删除
	 * @param id
	 */
	public void delete(long id);
	
    /**通过id获取数据
	 * @param arbitrationHandler
	 */
	public ArbitrationHandler findById(ArbitrationHandler arbitrationHandler);
	
	public List<ArbitrationHandler> getInfo(Long id);
	
}

