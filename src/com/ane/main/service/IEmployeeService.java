package com.ane.main.service;

import java.util.List;

import com.ane.main.domain.Employee;

public interface IEmployeeService {
	
	public Employee findById(Integer code); 
	public Employee findByName(String code); 

	public int insert(Employee record);
	
	List<Employee> getEmployeeByPage(Employee record,Integer pageSize,Integer offset);
	
	List<Employee> getAllEmployees();
	
	List<Employee> getEmpByDept(int deptId);
	
	int getCounts(Employee record,Integer pageSize,Integer offset);
	
	int delete(Integer id);
	
	int update(Employee record);
}
