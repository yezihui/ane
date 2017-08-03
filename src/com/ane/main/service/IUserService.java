package com.ane.main.service;

import java.util.List;

import com.ane.main.domain.User;

public interface IUserService {
	public User selectUserById(Integer userId);
	
	public User login(User user);
	
	public int insert(User user);
	
	public User getUserName(String name);
	
	public User getUserNameAndId(User u);
	
	public int delete(Long id);
	
	List<User> getUserByPage(User record,int pageSize,int offset);
	
	int getCounts();
	
	int update(User record);
	
	int updatePsw(Integer id);
}
