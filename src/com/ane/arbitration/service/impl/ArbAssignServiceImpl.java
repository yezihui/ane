/**
 * 
 */
package com.ane.arbitration.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.arbitration.dao.ArbitrationAssignDao;
import com.ane.arbitration.dao.ArbitrationTypeDao;
import com.ane.arbitration.domain.ArbitrationAssignment;
import com.ane.arbitration.service.IArbAssignService;
import com.ane.basic.dao.ProvincesDao;
import com.ane.util.PageBean;

 /**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author : 叶嘉贤
 * @date : 2017-2-10 下午4:33:17
 * @version 1.0
 */
@Service("arbitrationAssignmentService")
public class ArbAssignServiceImpl implements IArbAssignService {
	@Resource
	private ArbitrationAssignDao arbitrationAssignmentRepository;

	@Resource
	private ProvincesDao regionRepository;

	@Resource
	private ArbitrationTypeDao arbitrationTypeRepository;

	
	/**
	 * 添加一个新的人员分配比率
	 * @param assign
	 */
	public boolean add(ArbitrationAssignment assign,int gender) {
		List<ArbitrationAssignment> list = arbitrationAssignmentRepository.findAll();
		if(assign.getApplyAddresses().charAt(0)=='0'){
			assign.setApplyAddresses("0");
		}
		if(assign.getTypes().charAt(0)=='0'){
			assign.setTypes("0");
		}
		if(list.size()==0){
			if (gender == 1 ) {
				assign.setApplyAddresses(assign.getApplyAddresses());
			} else {
				assign.setOwnerAddresses(assign.getApplyAddresses());
			}
		}else{
			if(list.get(0).getApplyAddresses()!=null){
				assign.setApplyAddresses(assign.getApplyAddresses());
			}else{
				assign.setOwnerAddresses(assign.getApplyAddresses());
			}
		}
		// 创建人 a 保存的是员工表的code字段
		assign.setCreater(1L);
		 arbitrationAssignmentRepository.add(assign);
		 return true;
	}

	
	
	public PageBean getAssignInfo(ArbitrationAssignment aa ,int pageSize, int page){
		int offset = PageBean.countOffset( pageSize, page);
		aa.setPageSize(pageSize);
		aa.setOffset(offset);
		List<ArbitrationAssignment> list = arbitrationAssignmentRepository.getAssignInfo(aa);
		List<ArbitrationAssignment> list1 = screen(list,aa);
		int allRow = this.arbitrationAssignmentRepository.getArbitrationNum();
		PageBean pageBean = new PageBean(list1,allRow,pageSize,page);
		return pageBean;
	}
	
	public ArbitrationAssignment findById(long id){
		ArbitrationAssignment list = arbitrationAssignmentRepository.findById(id);
		return list;
	}
	
	
	public List<ArbitrationAssignment> findAll(){
		List<ArbitrationAssignment> list = arbitrationAssignmentRepository.findAll();
		return list;
	}
	
	/**
	 * 查询人员分配信息
	 * 
	 * @param find
	 * @return
	 */
	public List<ArbitrationAssignment> screen(List<ArbitrationAssignment> list,ArbitrationAssignment as) {
		// 进行处理
		for (ArbitrationAssignment aa : list) {
			// 获取的信息 进行处理 -- 省份处理
			String applyName = "";
			String str = aa.getApplyAddresses();
			String s = "";
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != ',') {
					s += str.charAt(i);
				} else {
					Long id;
					if (s != null) {
						id = Long.parseLong(s);
						applyName = applyName
								+ regionRepository.getProvinceName(id+"") + ",";
					}
					s = "";
				}
			}
			if (s != null && s != "") {
				Long id;
				id = Long.parseLong(s);
				if (regionRepository.getProvinceName(id+"") != null) {
					applyName = applyName
							+ regionRepository.getProvinceName(id+"");
				}
			}
			aa.setApplyProvince(applyName);
			// 获取的信息 进行处理 -- 省份处理
			String ownerName = "";
			str = aa.getOwnerAddresses();
			s = "";
			if(str!=null){
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						Long id;
						if (s != null) {
							id = Long.parseLong(s);
							ownerName = ownerName
									+ regionRepository.getProvinceName(id+"")+ ",";
						}
						s = "";
					}
				}
				if (s != null && s != "") {
					Long id;
					id = Long.parseLong(s);
					if (regionRepository.getProvinceName(id+"") != null) {
						ownerName = ownerName
								+ regionRepository.getProvinceName(id+"");
					}
				}
				aa.setOwnerProvince(ownerName);
			}
			// 获取的信息 进行处理 -- 类型处理
			String typeName = "";
			str = aa.getTypes();
			s = "";
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != ',') {
					s += str.charAt(i);
				} else {
					Long id1;
					if (s != null) {
						id1 = Long.parseLong(s);
						
						typeName = typeName
								+ arbitrationTypeRepository.findById(id1)
										.getName() + ",";
					}
					s = "";
				}
			}
			if (s != null && s != "") {
				Long id1;
				id1 = Long.parseLong(s);
				typeName = typeName
						+ arbitrationTypeRepository.findById(id1).getName();
			}
			aa.setTypeName(typeName);
		}
		// 通过查询条件 对已经进行了处理的结果 进行甄选 -- 类型
		if (("0".equals(as.getTypes())) != true
				&& as.getTypes()!= null) {
			String tn = arbitrationTypeRepository.findById(
					Long.parseLong((String) as.getTypes())).getName();
			for (int j = 0; j < list.size(); j++) {
				ArbitrationAssignment aa = list.get(j);
				String str = aa.getTypeName();
				String s = "";
				boolean b = true;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						if (s.equals(tn)) {
							b = false;
							break;
						}
						s = "";
					}
				}
				if (s != null && s.equals(tn)) {
					b = false;
				}
				if (b) {
					list.remove(j);
				}
			}
		}
		// 通过查询条件 对已经进行了处理的结果 进行甄选 -- 省份
		if (("0".equals(as.getApplyAddresses())) != true
				&& as.getApplyAddresses() != null && as.getApplyAddresses() !="") {
			String tn = regionRepository.getProvinceName((String) as.getApplyAddresses()+"");
			for (int j = 0; j < list.size(); j++) {
				ArbitrationAssignment aa = list.get(j);
				String str = aa.getApplyProvince();
				String s = "";
				boolean b = true;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						if (s.equals(tn)) {
							b = false;
							break;
						}
						s = "";
					}
				}
				if (s != null && s.equals(tn)) {
					b = false;
				}
				if (b) {
					list.remove(j);
				}
			}
		}
		// 通过查询条件 对已经进行了处理的结果 进行甄选 -- 省份
		if (("0".equals((String) as.getOwnerAddresses())) != true
				&& as.getOwnerAddresses() != null && as.getOwnerAddresses() != "") {
			String tn = regionRepository.getProvinceName((String) as.getOwnerAddresses()+"");
			for (int j = 0; j < list.size(); j++) {
				ArbitrationAssignment aa = list.get(j);
				String str = aa.getOwnerProvince();
				String s = "";
				boolean b = true;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != ',') {
						s += str.charAt(i);
					} else {
						if (s.equals(tn)) {
							b = false;
							break;
						}
						s = "";
					}
				}
				if (s != null && s.equals(tn)) {
					b = false;
				}
				if (b) {
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	public boolean delete(Long id){
		 this.arbitrationAssignmentRepository.delete(id);
		 return true;
	}

	public boolean update(ArbitrationAssignment arbitrationAssignment) {
		if(arbitrationAssignment.getApplyAddresses().charAt(0)=='0'){
			arbitrationAssignment.setApplyAddresses("0");
		}
		if(arbitrationAssignment.getTypes().charAt(0)=='0'){
			arbitrationAssignment.setTypes("0");
		}
		 this.arbitrationAssignmentRepository.update(arbitrationAssignment);
		 return true;
	}


	public Long findByName(String name) {
		return null;
	}
	public List<ArbitrationAssignment> search() {
		return null;
	}

}
