package com.ane.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ane.main.dao.UserDao;
import com.ane.main.domain.User;
import com.ane.main.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource
	private UserDao userDao;
	
	public User selectUserById(Integer userId) {
		return userDao.selectUserById(userId);
	}

	public User login(User user) {
		return userDao.login(user);
	}

	/**
	 * 
	 */
	public int insert(User user) {
		userDao.insert(user);
		return 1;
	}


	public List<User> getUserByPage(User record, int pageSize, int page) {
		record.setOffset((page-1)*pageSize);
		record.setPageSize(pageSize);
		return userDao.getUserByPage(record);
	}

	public int getCounts() {
		return userDao.getCounts();
	}

	public int update(User record) {
		userDao.update(record);
		return 1;
	}

	public int delete(Long user) {
		userDao.delete(user);
		return 1;
	}

	public User getUserName(String name) {
		return userDao.getUserName(name);
	}
	
	public User getUserNameAndId(User u) {
		return userDao.getUserNameAndId(u);
	}

	public int updatePsw(Integer id) {
		userDao.updatePsw(id);
		return 1;
	}

}
