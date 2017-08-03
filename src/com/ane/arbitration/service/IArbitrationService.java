package com.ane.arbitration.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.ane.arbitration.domain.Arbitration;
import com.ane.util.PageBean;


/** 
 * 说明： 仲裁主表接口
 * 创建人：View
 * 创建时间：2016-09-02
 * @version
 */
public interface IArbitrationService{

	/**新增
	 * @param arbitration
	 */
	public boolean add(int i,Arbitration arbitration,HttpServletRequest req,
			HttpServletResponse resp);
	
	/**删除
	 * @param id
	 */
	public boolean delete(long id);
	
	/**修改
	 * @param arbitration
	 */
	public boolean update(Arbitration arbitration);
	
    /**通过id获取数据
	 * @param arbitration
	 */
	public Arbitration findById(long id);
	
	/**
	 * 通过id获取主表信息
	 * @param id
	 * @return
	 */
	Arbitration getMain(long id);
	
	/**
	 * 更改仲裁主表状态
	 * @param condition
	 */
	public boolean updateArbInfo(Arbitration arbitration);
	
	//分页获取所有网点信息
	PageBean getArbitrationByPage(@Param("at")Arbitration at,@Param("pageSize")int pageSize,@Param("offset")int offset);
	
	//分页获取所有网点信息
	PageBean searchByEwbNo(@Param("at")Arbitration at,@Param("pageSize")int pageSize,@Param("offset")int offset);
	
	Arbitration findByEwbNo(String id);
	
	Arbitration findByClaim(String id);
}

