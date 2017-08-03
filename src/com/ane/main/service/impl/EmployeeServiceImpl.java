package com.ane.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.main.dao.EmployeeDao;
import com.ane.main.domain.Employee;
import com.ane.main.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Resource
	private EmployeeDao employeeDao;
	
	public Employee findById(Integer code) {
		return employeeDao.findById(code);
	}

	public int insert(Employee record) {
		employeeDao.insert(record);
		return 1;
	}

	public List<Employee> getEmployeeByPage(Employee record, Integer pageSize, Integer page) {
		record.setOffset((page-1)*pageSize);
		record.setPageSize(pageSize);
		return employeeDao.getEmployeeByPage(record);
	}

	public int getCounts(Employee record, Integer pageSize, Integer page) {
		record.setOffset((page-1)*pageSize);
		record.setPageSize(pageSize);
		return employeeDao.getCounts(record);
	}

	public int update(Employee record) {
		return employeeDao.update(record);
	}

	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	public int delete(Integer id) {
		employeeDao.delete(id);
		return 1;
	}
	
	public List<Employee> getEmpByDept(int deptId) {
		Employee e = new Employee();
		e.setDeptId(Long.valueOf(deptId));
		return employeeDao.getEmpByDept(e);
	}

	public Employee findByName(String code) {
		return employeeDao.findByName(code);
	}

}
