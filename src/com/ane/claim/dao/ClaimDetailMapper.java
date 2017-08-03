package com.ane.claim.dao;

import com.ane.claim.domain.ClaimDetail;

public interface ClaimDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimDetail record);

    int insertSelective(ClaimDetail record);

    ClaimDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimDetail record);

    int updateByPrimaryKey(ClaimDetail record);
}