/**
 * 
 */
package com.ane.claim.service;

import com.ane.claim.domain.Claim;
import com.ane.util.PageBean;

/**
 * <p>
 * 功能描述：
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author : 叶嘉贤
 * @date : 2017-3-1 下午6:26:18
 * @version 1.0
 */
public interface IClaimService {

	boolean delete(Long id);

	boolean insert(Claim record);

	Claim findById(Long id);

	boolean update(Claim record);
	
	PageBean searchByEwbNo(Claim at,int pageSize,int offset);
	
	PageBean getArbitrationByPage(Claim at,int pageSize,int offset);
}
