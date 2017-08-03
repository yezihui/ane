/**
 * 
 */
package com.ane.claim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.claim.dao.ClaimMapper;
import com.ane.claim.domain.Claim;
import com.ane.claim.service.IClaimService;
import com.ane.util.PageBean;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-3-1 下午6:26:40
 * @version 1.0
 */
@Service("claimService")
public class ClaimServiceImpl implements IClaimService {

	@Resource
	private ClaimMapper ClaimDao;
	
	public boolean delete(Long id) {
		 ClaimDao.delete(id);
		 return true;
	}

	public boolean insert(Claim record) {
		ClaimDao.insert(record);
		return true;
	}

	public Claim findById(Long id) {
		return ClaimDao.findById(id);
	}

	public boolean update(Claim record) {
		ClaimDao.update(record);
		return true;
	}

	public PageBean searchByEwbNo(Claim at, int pageSize, int page) {
		int offset = PageBean.countOffset(pageSize, page);
		at.setPageSize(pageSize);
		at.setOffset(offset);
		List<Claim> list = this.ClaimDao.searchByEwb(at);
		int allRow = this.ClaimDao.getArbitrationNum();
		PageBean pageBean = new PageBean(list,allRow,pageSize,page);
		return pageBean;
	}

	public PageBean getArbitrationByPage(Claim at, int pageSize,
			int page) {
		int offset = PageBean.countOffset( pageSize, page);
		at.setPageSize(pageSize);
		at.setOffset(offset);
		List<Claim> list = this.ClaimDao.getArbitrationByPage(at);
		int allRow = this.ClaimDao.getArbitrationNum();
		PageBean pageBean = new PageBean(list,allRow,pageSize,page);
		return pageBean;
	}

}
