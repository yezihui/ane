package com.ane.claim.dao;

import java.util.List;

import com.ane.claim.domain.Claim;

public interface ClaimMapper {
	int delete(Long id);

	int insert(Claim record);

	Claim findById(Long id);

	int update(Claim record);
	
	List<Claim> searchByEwb(Claim at);
	
	List<Claim> getArbitrationByPage(Claim at);
	
	int getArbitrationNum();
}