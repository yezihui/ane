/**
 * 
 */
package com.ane.claim.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.claim.dao.ClaimDetailMapper;
import com.ane.claim.domain.ClaimDetail;
import com.ane.claim.service.IClaimDetailService;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-3-3 下午4:12:26
 * @version 1.0
 */
@Service("claimDetailService")
public class CliamDetailServiceImpl implements IClaimDetailService {

	@Resource
	private ClaimDetailMapper claimDetailDao;
	
	public boolean insert(ClaimDetail record) {
		claimDetailDao.insert(record);
		return true;
	}

}
