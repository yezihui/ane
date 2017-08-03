/**
 * 
 */
package com.ane.order.service;

import java.util.List;

import com.ane.order.domain.OptTrail;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-21 下午5:58:10
 * @version 1.0
 */
public interface IOptTrailService {

	int delete(Integer id);

    int insert(OptTrail record);

    OptTrail findById(Integer id);
    
    List<OptTrail> getTrailByEwbNo(String ewbNo);

    int update(OptTrail record);
}
