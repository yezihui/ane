package com.ane.main.dao;

import java.util.List;

import com.ane.main.domain.User;

public interface UserDao{
	public User selectUserById(Integer userId);
	
	public User login(User user);
	
	public void insert(User user);
	
	public User getUserName(String name);
	
	public User getUserNameAndId(User u);
	
	public void delete(Long id);
	
	public User findById(Long id);
	
	List<User> getUserByPage(User record);
	
	int getCounts();
	
	void update(User record);
	
	void updatePsw(Integer id);
}
