/**
 * 
 */
package com.ane.order.service;

import com.ane.order.domain.OptEwb;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-1-21 下午5:23:10
 * @version 1.0
 */
public interface IOptEwbService {

	int delete(String ewbNo);

    int insert(OptEwb record);

    OptEwb findById(String ewbNo);

    int update(OptEwb record);
    
    OptEwb ewbInfo(String ewbNo);
}
