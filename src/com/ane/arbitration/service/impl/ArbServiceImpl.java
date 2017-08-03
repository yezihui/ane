/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationAssignDao;
import com.ane.arbitration.dao.ArbitrationDao;
import com.ane.arbitration.dao.ArbitrationTypeDao;
import com.ane.arbitration.domain.Arbitration;
import com.ane.arbitration.domain.ArbitrationAssignment;
import com.ane.arbitration.domain.ArbitrationType;
import com.ane.arbitration.service.IArbitrationService;
import com.ane.basic.dao.AuthOrganizationDao;
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
 * @date : 2017-2-16 下午5:50:19
 * @version 1.0
 */
@Service("arbService")
public class ArbServiceImpl implements IArbitrationService {

	@Resource
	private ArbitrationDao arbitrationRepository;
	@Resource
	private AuthOrganizationDao companyRepository;
	@Resource
	private ArbitrationTypeDao arbitrationTypeRepository;
	@Resource
	private ArbitrationAssignDao arbitrationAssignmentRepository;

	public boolean add(int i, Arbitration arb, HttpServletRequest req,
			HttpServletResponse resp) {
		// 从前台获取的是名字 但是要保存到数据库的是id
		if ((String) req.getSession()
				.getAttribute("loginSiteName") != null && (String) req.getSession()
						.getAttribute("loginSiteName") != "") {
			Long applySiteId = companyRepository.findByName(
					(String) req.getSession()
					.getAttribute("loginSiteName")).getId();
			arb.setApplySiteId(applySiteId);
		}
		// // 人员分配
		if (arb.getApplyId() != null) {
			ArbitrationType type = arbitrationTypeRepository.findById(arb
					.getApplyId());
			arb.setApplyType(type.getName());
			Long handleId = assignment(i, arb.getApplyId(), arb.getApplySiteId(),
					arb.getOwnerSiteId());
			// 如果handleId 为空 说明 没人可以处理此单
			arb.setHandler(handleId);
		}
		// 网点申报之后状态就是已分中心
		arb.setStatus("1");
		this.arbitrationRepository.add(arb);
		return true;
	}

	private Long assignment(int i, Long typeId, Long applySiteId,
			Long ownerSiteId) {
		// 首先根据 申报网点 责任网点 查询 相关 网点 所属省份 获取省份 id
		// 如果 i 为 1 说明时按申办方进行查询
		Map<String, Object> condition = new HashMap<String, Object>();
		if (i == 1) {
			Long applyProvince = Long.valueOf(companyRepository.findById(
					applySiteId).getProvince());
			condition.put("applyProvince", applyProvince);
			condition.put("typeId", typeId);
			List<ArbitrationAssignment> list = find(condition);
			int a = (int) (Math.random() * 100);
			BigDecimal big = new BigDecimal(a);
			BigDecimal b = new BigDecimal(0);
			for (int j = 0; j < list.size(); j++) {
				b = b.add(list.get(j).getRate());
				if (big.compareTo(b) == -1) {
					return list.get(j).getOwnerId();
				}
			}
			if(list.size()>=1)
				return list.get(list.size() - 1).getOwnerId();
		}
		if (i == 2) {
			Long ownerProvince = Long.valueOf(companyRepository.findById(
					ownerSiteId).getProvince());
			;
			condition.put("ownerProvince", ownerProvince);
			condition.put("typeId", typeId);
			List<ArbitrationAssignment> list = find(condition);
			int a = (int) (Math.random() * 100);
			BigDecimal big = new BigDecimal(a);
			BigDecimal b = new BigDecimal(0);
			for (int j = 0; j < list.size(); j++) {
				b = b.add(list.get(j).getRate());
				if (big.compareTo(b) == -1) {
					return list.get(j).getOwnerId();
				}
			}
			if(list.size()>=1)
				return list.get(list.size() - 1).getOwnerId();
		}
		return null;
	}

	private List<ArbitrationAssignment> find(Map<String, Object> condition) {
		List<ArbitrationAssignment> list = arbitrationAssignmentRepository
				.findAll();
		// 按仲裁申报方查询
		int size = list.size();
		int arr[] = new int[size];
		if (condition.get("applyProvince") != null) {
			/* 省份 */
			for (int j = 0; j < size; j++) {
				ArbitrationAssignment aa = list.get(j);
				String str = aa.getApplyAddresses();
				if(str.equals("0")){
					break;
				}
				String s = "";
				boolean b = true;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						if (s != null) {
							if (s.equals(condition.get("applyProvince"))) {
								b = false;
								break;
							}
						}
						s = "";
					}
				}
				if (s != null && s != "") {
					if (s.equals(condition.get("applyProvince") + "")) {
						b = false;
					}
				}
				if (b) {
					// 吧所有的都列入删除了
					arr[j] = 1;
				}
			}
			int f = 0;
			// 在这里 只能先找出要remove的下标 在进行移除
			for (int i = 0; i < list.size(); i++) {
				if (arr[i] == 1 && f == 0) {
					// 删除了 第一个之后 其他的位置就变了
					f = f + 1;
					list.remove(i);
				} else if (arr[i] == 1 && f != 0) {
					list.remove(i - f);
					f = f + 1;
				}
			}
		}

		// 按仲裁申报方查询
		if (condition.get("ownerProvince") != null) {
			/* 省份 */
			size = list.size();
			arr = new int[size];
			for (int j = 0; j < size; j++) {
				ArbitrationAssignment aa = list.get(j);
				String str = aa.getApplyAddresses();
				if(str.equals("0")){
					break;
				}
				String s = "";
				boolean b = true;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						if (s != null) {
							if (s.equals(condition.get("ownerProvince") + "")) {
								b = false;
								break;
							}
						}
						s = "";
					}
				}
				if (s != null && s != "") {
					if (s.equals(condition.get("ownerProvince") + "")) {
						b = false;
					}
				}
				if (b) {
					arr[j] = 1;
				}
			}
			int f = 0;
			// 在这里 只能先找出要remove的下标 在进行移除
			for (int i = 0; i < list.size(); i++) {
				if (arr[i] == 1 && f == 0) {
					// 删除了 第一个之后 其他的位置就变了
					f = f + 1;
					list.remove(i);
				} else if (arr[i] == 1 && f != 0) {
					list.remove(i - f);
					f = f + 1;
				}
			}
		}
		/* 仲裁类型 */
		size = list.size();
		arr = new int[size];
		for (int j = 0; j < size; j++) {
			ArbitrationAssignment aa = list.get(j);
			String str = aa.getTypes();
			if(str.equals("0"))
				break;
			String s = "";
			boolean b = true;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != ',') {
					s += str.charAt(i);
				} else {
					if (s != null) {
						if (s.equals(condition.get("typeId") + "")) {
							b = false;
							break;
						}
					}
					s = "";
				}
			}
			if (s != null && s != "") {
				if (s.equals(condition.get("typeId") + "")) {
					b = false;
				}
			}
			if (b) {
				arr[j] = 1;
			}
		}
		int f = 0;
		// 在这里 只能先找出要remove的下标 在进行移除
		for (int i = 0; i < list.size(); i++) {
			if (arr[i] == 1 && f == 0) {
				// 删除了 第一个之后 其他的位置就变了
				f = f + 1;
				list.remove(i);
			} else if (arr[i] == 1 && f != 0) {
				list.remove(i - f);
				f = f + 1;
			}
		}
		return list;
	}

	public boolean delete(long id) {
		this.arbitrationRepository.delete(id);
		return true;
	}

	public boolean update(Arbitration arb) {
		ArbitrationType type = arbitrationTypeRepository.findById(arb
				.getApplyId());
		arb.setApplyType(type.getName());
		// 网点申报之后状态就是已分中心
		this.arbitrationRepository.update(arb);
		return true;
	}

	public Arbitration findById(long id) {
		return this.arbitrationRepository.findById(id);
	}

	public List<Arbitration> searchByEwb(String ewb_no) {
		List<Arbitration> list = this.arbitrationRepository.searchByEwb(
				ewb_no);
		return list;
	}

	public List<Arbitration> searchByEwbOnStatus(String ewb_no) {
		List<Arbitration> list = this.arbitrationRepository
				.searchByEwbOnStatus(ewb_no);
		return list;
	}

	public List<Arbitration> searchByCondition(Map<String, Object> condition) {
		if(condition.get("start") != null && condition.get("end") != null){
			String start = (String) condition.get("start");
			String end = (String) condition.get("end");
			System.out.println(start);
			System.out.println(end);
			start += " 00:00:00";
			end += "23:59:59";
			condition.remove("start");
			condition.remove("end");
			condition.put("start", start);
			condition.put("end", end);
		}
		List<Arbitration> list = this.arbitrationRepository
				.searchByCondition(condition);
		return list;
	}

	public Arbitration getMain(long id) {
		Arbitration list = this.arbitrationRepository.getMain(id);
		return list;
	}


	public boolean updateArbInfo(Arbitration arbitration) {
		 arbitrationRepository.updateArbInfo(arbitration);
		 return true;
	}

	
	public PageBean getArbitrationByPage(Arbitration at, int pageSize,
			int page) {
		int offset = PageBean.countOffset( pageSize, page);
		if("全部".equals(at.getApplyType())){
			at.setApplyType(null);
		}
		at.setPageSize(pageSize);
		at.setOffset(offset);
		List<Arbitration> list = this.arbitrationRepository.getArbitrationByPage(at);
		int allRow = this.arbitrationRepository.getArbitrationNum();
		PageBean pageBean = new PageBean(list,allRow,pageSize,page);
		return pageBean;
	}

	public PageBean searchByEwbNo(Arbitration at, int pageSize,
			int page) {
		int offset = PageBean.countOffset(pageSize, page);
		at.setPageSize(pageSize);
		at.setOffset(offset);
		List<Arbitration> list = this.arbitrationRepository.searchByEwbNo(at);
		int allRow = this.arbitrationRepository.getArbitrationNum();
		PageBean pageBean = new PageBean(list,allRow,pageSize,page);
		return pageBean;
	}


	public Arbitration findByEwbNo(String id) {
		return arbitrationRepository.findByEwbNo(id);
	}

	public Arbitration findByClaim(String id) {
		return arbitrationRepository.findByClaim(id);
	}

}
