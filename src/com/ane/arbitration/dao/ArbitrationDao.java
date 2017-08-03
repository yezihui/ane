package com.ane.arbitration.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ane.arbitration.domain.Arbitration;


/** 
 * 说明： 仲裁主表接口
 * 创建人：View
 * 创建时间：2016-09-02
 * @version
 */
public interface ArbitrationDao{
	
	Arbitration findByEwbNo(String id);

	/**新增
	 * @param arbitration
	 */
	public int add(Arbitration arbitration);
	
	/**删除
	 * @param id
	 */
	public int delete(long id);
	
	/**修改
	 * @param arbitration
	 */
	public int update(Arbitration arbitration);
	
    /**通过id获取数据
	 * @param arbitration
	 */
	public Arbitration findById(long id);
	
	/**
	 * 通过运单号查询数据
	 * @return
	 */
	List<Arbitration> searchByEwb(@Param(value = "ewb_no") String ewb_no);
	
	/**
	 * 通过运单号查询数据 但是状态必须为已分中心
	 * @return
	 */
	List<Arbitration> searchByEwbOnStatus(@Param(value = "ewb_no") String ewb_no);
	
	/**
	 * 通过条件查询数据
	 * @return
	 */
	List<Arbitration> searchByCondition(@Param(value = "condition") Map<String,Object> condition);
	
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
	public void updateArb(Arbitration arbitration);
	
	public int updateArbInfo(Arbitration arbitration);
	
	//分页获取所有网点信息
	List<Arbitration> getArbitrationByPage(Arbitration at);
	
	//分页获取所有网点信息
	List<Arbitration> searchByEwbNo(Arbitration at);
	//获取总页数
	int getArbitrationNum();
	
	Arbitration findByClaim(String id);
}

