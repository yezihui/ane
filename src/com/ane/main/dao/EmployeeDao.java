package com.ane.main.dao;

import java.util.List;

import com.ane.main.domain.Employee;

public interface EmployeeDao {

    Employee findById(Integer id);
    
    public Employee findByName(String code);

    public void insert(Employee record);
	
	List<Employee> getEmployeeByPage(Employee record);
	
	List<Employee> getAllEmployees();
	
	List<Employee> getEmpByDept(Employee deptId);
	
	int getCounts(Employee record);
	
	void delete(Integer id);
	
	int update(Employee record);
}